package team.xuli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/4
 * 创建原因：
 */
@SpringBootApplication
@MapperScan("team.xuli.dao")
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);}
}
