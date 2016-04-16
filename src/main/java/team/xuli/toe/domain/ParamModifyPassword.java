package team.xuli.toe.domain;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: 徐清锋
 * 创建时间：2016/4/10
 * 创建原因：
 */
public class ParamModifyPassword {
    @NotNull
    @NotBlank
    private String oldPassword;
    @NotNull
    @NotBlank
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
