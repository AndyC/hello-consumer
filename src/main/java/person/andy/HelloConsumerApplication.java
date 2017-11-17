package person.andy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/**
 * @author :AndyCui
 * spring boot启动类 注意为服务消费者添加断路器功能时需要在消费者的spring boot启动类上面添加@EnableCircuitBreaker注解
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
public class HelloConsumerApplication {
	@Bean
	@LoadBalanced
    RestTemplate restTemplate(){
    	return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(HelloConsumerApplication.class, args);
	}
}
