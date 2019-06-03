package cn.net.liaowei.sc.product;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2Doc
public class ScProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScProductApplication.class, args);
    }

}
