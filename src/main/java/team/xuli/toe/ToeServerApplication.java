package team.xuli.toe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/4
 * 创建原因：
 */
@SpringBootApplication
@MapperScan("team.xuli.toe.dao")
public class ToeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToeServerApplication.class, args);}
}
