package edu.whpu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Hello world!
 *
 */
@EnableZuulProxy
@SpringBootApplication
// 开启数据监控注解
@EnableHystrixDashboard
public class ZuulServerApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}
