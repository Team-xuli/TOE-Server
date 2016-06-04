package team.xuli.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.xuli.toe.dao.IItemTypeDao;
import team.xuli.toe.dao.IUserProfileDao;
import team.xuli.toe.domain.*;
import team.xuli.toe.util.AppConst;
import team.xuli.toe.util.ProfileConst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: 徐清锋
 * 创建时间：2016/6/4
 * 创建原因：
 */
@Service
public class UserProfileService implements IUserProfileService {
    @Autowired
    private IUserProfileDao userProfileDao;
    @Autowired
    private IItemTypeDao itemTypeDao;

    @Transactional
    public int createUserProfile(User user, String role) {
        if(Objects.equals(role, AppConst.ROLE_DELIVERER)){
            List<UserInterest> interests = new ArrayList<>();
            interests.add(this.initItemTypeInterest(user));
            //TODO: add other interests
            for (UserInterest interest : interests){
                userProfileDao.insertInterest(interest);
                for (InterestFeature feature : interest.getFeatures()){
                    feature.setInterestMainId(interest.getInterestMainId());
                    userProfileDao.insertFeature(feature);
                }
            }
            return 0;
        }else{
            return 0;
        }
    }
    public int updateUserProfile(User user, Order order){
        //TODO: add other interests
        String interestName = ProfileConst.ITEM_TYPE_INTEREST;
        String chosenFeatureName = order.getItemType().getName();
        return updateUserInterest(user, interestName, chosenFeatureName);
    }

    public int sortNearByOrder(User user, List<Order> orders) {
        List<UserInterest> interests = userProfileDao.getUserInterests(user.getUserId());
        for (Order order : orders){
            order.setInterestScore(0.0);
            for (UserInterest interest : interests){
                if(interest.getName().equals(ProfileConst.ITEM_TYPE_INTEREST)){
                    for (InterestFeature feature : interest.getFeatures()){
                        if(order.getItemType().getName().equals(feature.getName())){
                            order.setInterestScore(order.getInterestScore() + feature.getWeight());
                        }
                    }
                } else {
                    //TODO: only itemType now, add other interests here
                }
            }
        }
        Collections.sort(orders);
        return 0;
    }

    @Transactional
    public int updateUserInterest(User user, String interestName, String chosenFeatureName) {
        UserInterest interest = userProfileDao.getUserInterest(user.getUserId(), interestName);
        InterestFeature chosenFeature = this.getChosenFeature(interest,chosenFeatureName);
        InterestFeature expectFeature = this.getExpectFeature(interest,chosenFeature);

        //update weight of features
        double otherWeightSum = ProfileConst.WHOLE_WEIGHT - chosenFeature.getWeight();
        chosenFeature.setWeight(chosenFeature.getWeight() + interest.getChangeRate());
        for (InterestFeature feature : interest.getFeatures()){
            if(!feature.equals(chosenFeature)){
                feature.setWeight(feature.getWeight() - interest.getChangeRate() * feature.getWeight() / otherWeightSum);
            }
        }

        //update change rate
        if(chosenFeature.getName().equals(expectFeature.getName())){
            //choose the expectFeature
            interest.setChangeRate((ProfileConst.WHOLE_WEIGHT-expectFeature.getWeight())/2);
        }else{
            // does not choose the expectFeature
            interest.setChangeRate(interest.getChangeRate() +
                                    (expectFeature.getWeight() - chosenFeature.getWeight())/2);
        }

        //update DB
        userProfileDao.updateInterest(interest);
        interest.getFeatures().forEach(userProfileDao::updateFeature);
        return 0;
    }

    private UserInterest initItemTypeInterest(User user){
        //init itemTypeInterest
        UserInterest itemTypeInterest = new UserInterest();
        itemTypeInterest.setUserId(user.getUserId());
        itemTypeInterest.setChangeRate(ProfileConst.INIT_CHANGE_RATE);
        itemTypeInterest.setName(ProfileConst.ITEM_TYPE_INTEREST);
        if(itemTypeInterest.getFeatures() == null){
            itemTypeInterest.setFeatures(new ArrayList<>());
        }
        
        List<ItemType> itemTypes = itemTypeDao.getItemTypes();
        double itemTypeInitWeight = ProfileConst.WHOLE_WEIGHT/itemTypes.size();
        for (ItemType itemType : itemTypes) {
            InterestFeature feature = new InterestFeature();
            feature.setName(itemType.getName());
            feature.setWeight(itemTypeInitWeight);

            itemTypeInterest.getFeatures().add(feature);
        }
        return itemTypeInterest;
    }

    private InterestFeature getExpectFeature(UserInterest interest, InterestFeature chosenFeature){
        double maxWeight = chosenFeature.getWeight();
        InterestFeature expectFeature = chosenFeature;
        for (InterestFeature feature : interest.getFeatures()){
            if(maxWeight < feature.getWeight()) {
                maxWeight = feature.getWeight();
                expectFeature = feature;
            }
        }
        return expectFeature;
    }

    private InterestFeature getChosenFeature(UserInterest interest, String featureName){
        for (InterestFeature feature : interest.getFeatures()){
            if(feature.getName().equals(featureName)){
                return feature;
            }
        }
        return new InterestFeature();
    }
}
