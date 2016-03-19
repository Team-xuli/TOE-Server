package team.xuli.toe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/5
 * 创建原因：解决跨域
 */
@Configuration
public class CORSConfig {
    @Bean
    WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("PUT", "DELETE","GET","POST");
            }
        };
    }

}
