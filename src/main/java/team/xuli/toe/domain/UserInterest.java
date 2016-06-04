package team.xuli.toe.domain;

import java.util.List;

/**
 * @author: 徐清锋
 * 创建时间：2016/6/4
 * 创建原因：
 */
public class UserInterest {
    private int interestMainId;
    private String name;
    private int userId;
    private double changeRate;
    List<InterestFeature> features;

    public int getInterestMainId() {
        return interestMainId;
    }

    public void setInterestMainId(int interestMainId) {
        this.interestMainId = interestMainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(double changeRate) {
        this.changeRate = changeRate;
    }

    public List<InterestFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<InterestFeature> features) {
        this.features = features;
    }
}
