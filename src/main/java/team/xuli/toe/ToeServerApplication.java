package team.xuli.toe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/4
 * 创建原因：
 */
@SpringBootApplication
@MapperScan("team.xuli.toe.dao")
@CrossOrigin
public class ToeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToeServerApplication.class, args);}
}
