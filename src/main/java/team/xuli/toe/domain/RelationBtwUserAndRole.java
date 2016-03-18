package team.xuli.toe.domain;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/18
 * 创建原因：
 */
public class RelationBtwUserAndRole {
    private int id;
    private int userId;
    private int roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
