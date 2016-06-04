package team.xuli.toe.dao;

import org.apache.ibatis.annotations.Param;
import team.xuli.toe.domain.InterestFeature;
import team.xuli.toe.domain.UserInterest;

import java.util.List;

/**
 * @author: 徐清锋
 * 创建时间：2016/6/4
 * 创建原因：
 */
public interface IUserProfileDao {
    List<UserInterest> getUserInterests(@Param("userId")int userId);
    UserInterest getUserInterest(@Param("userId")int userId, @Param("interestName")String interestName);
    int updateInterest(UserInterest interest);
    int updateFeature(InterestFeature feature);
    int insertInterest(UserInterest interest);
    int insertFeature(InterestFeature feature);
}
