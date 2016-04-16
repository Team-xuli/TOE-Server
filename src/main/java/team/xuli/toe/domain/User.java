package team.xuli.toe.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.internal.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.xuli.toe.util.AppConst;

import java.util.ArrayList;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/4
 * 创建原因：
 */
public class User implements UserDetails {
    private int userId = 0;
    @NotNull
    private String username;
    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private int credit;
    private int money;
    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;

    //获取用户角色，暂时只有一个角色，返回authorities的第一个元素
    public String getRole() {
        if(authorities.size() > 0){
            return authorities.get(0).toString();
        } else {
            return AppConst.ROLE_NONE_SENSE;
        }
    }

    private ArrayList<SimpleGrantedAuthority> authorities;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public ArrayList<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(ArrayList<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
