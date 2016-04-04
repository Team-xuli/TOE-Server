package team.xuli.toe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：安全管理配置
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(new Md5PasswordEncoder());
        auth.userDetailsService(userDetailsService).passwordEncoder(new PlaintextPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic();
        http
                .formLogin()
                    .loginPage("/signin")
                    .defaultSuccessUrl("/user")
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/signout")
                    .clearAuthentication(true);
        http
                .authorizeRequests()
                //TestController
                //不登录也可以访问hello world
                .antMatchers("/hello").permitAll()
                //登录才可以访问hello world
                .antMatchers("/shello").hasAnyRole("OWNER", "DELIVERER", "ADMIN")

                //SignControllers
                //不登录也可以注册
                .antMatchers("/signup").permitAll()
                //任何角色都可以登录
                .antMatchers("/signin").permitAll()
                .antMatchers("/signout").hasAnyRole("OWNER", "DELIVERER")

                //UserController
                //任何角色都可以修改自己的信息
                .antMatchers("/user/address").hasAnyRole("OWNER", "DELIVERER")
                .antMatchers("/user/info").hasAnyRole("OWNER", "DELIVERER")


                //OrderController
                //只有owner可以新建订单
                .antMatchers("/order").hasRole("OWNER")
                //owner和deliverer都可以看自己的订单历史
                .antMatchers("/order/history").hasAnyRole("OWNER", "DELIVERER")
                //只有deliverer可以查看附近订单
                .antMatchers("/order/nearby").hasRole("DELIVERER")
                .antMatchers("/order/assignment").hasRole("DELIVERER")
                .antMatchers("/order/achievement").hasRole("DELIVERER")
                .anyRequest().authenticated();
    }
}
