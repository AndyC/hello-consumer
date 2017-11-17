package person.andy.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import person.andy.bean.User;
import person.andy.common.ResponseObject;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: AndyCui
 * @Date: 2017/10/19 19:57
 * @Description:
 */
@Service
public class HelloService {
    /**
    * 对使用restTemplate进行http请求调用的理解：
    * 进行get请求时，请求的路径需要时带有参数的路径，分为两种情况：
    * （1）当路径是http://hello-service/hello/user?name=name&age=age 这种问号传参时，方法中的uri参数需要写成http://hello-service/hello/user?name={name}&age={age}这种形式
     * 而不是http://hello-service/hello/user
     * 参数放在后面的uriVariables参数中，map和object[] 都行，这种情况下接收方需要使用@RequestParam 注解来接收参数
     * （2）当路径是restful风格即http://hello-service/hello/user/{name}/{age}时，方法中的URI参数写成http://hello-service/hello/user/{name}/{age}这种形式，参数同样放在
     * uriVariables中 map和object[] 都行
     * 进行post请求时，如果请求的路径是上面的任意一种形式，参数传递与上面相同即可，body体里面的数据放在参数request里面，这里默认会以json形式传递过去，接收方使用@RequestBody注解接收即可
    */
    @Resource
    private RestTemplate restTemplate;
    //HystrixCommand注解指定断路器发生错误是调用哪个方法来进行错误回调
    //注意是在服务消费者的调用方法上面添加该注解
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        System.err.println("hello-consumer");
        return restTemplate.getForEntity("http://hello-service/hello/hello",String.class).getBody();
    }
    /**
     * @Author: AndyCui
     * @Date:   2017/10/19 20:07
     * @Description: 进行远程调用时发生错误调用的方法 由@HystrixCommand 注解来使用
     */
    private String helloFallback(){
        System.err.println("rpc call error");
        return "error";
    }

    public User getUser(String name, int age) {
        User user=new User();
        user.setAge(age*2);
        user.setName(name+name);
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        ResponseEntity<User> entity= restTemplate.postForEntity("http://hello-service/hello/user?name={name}&age={age}",user,User.class,name,age);
        return entity.getBody();
    }

    public ResponseObject<String> applyResponseTest(String userName) {

        ResponseEntity<ResponseObject> responseEntity= restTemplate.getForEntity("http://hello-service/hello/pay?name={name}",ResponseObject.class,userName);
        return responseEntity.getBody();
    }
}
