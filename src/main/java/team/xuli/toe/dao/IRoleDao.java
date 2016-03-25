package team.xuli.toe.dao;

import org.apache.ibatis.annotations.Param;
import team.xuli.toe.domain.RelationBtwUserAndRole;
import team.xuli.toe.domain.Role;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/18
 * 创建原因：
 */
public interface IRoleDao {
    boolean insertRelationShip(RelationBtwUserAndRole relationBtwUserAndRole);

    Role getRoleByRoleName(@Param("roleName")String roleName);
}
