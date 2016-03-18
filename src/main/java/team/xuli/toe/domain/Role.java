package team.xuli.toe.domain;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/18
 * 创建原因：
 */
public class Role {
    private int roleId;
    private String roleName;
    private String description;
    private boolean status;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
