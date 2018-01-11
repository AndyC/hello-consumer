package person.andy.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import person.andy.bean.User;
import person.andy.common.ResponseObject;
import person.andy.service.HelloService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: AndyCui
 * @Date: 2017/10/10 17:57
 * @Description:
 */
@RestController
public class HelloConsumerController {
    @Resource
    private HelloService helloService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        return helloService.helloService();
    }

    @RequestMapping(value = "/serviceHello", method = RequestMethod.GET)
    public String helloAsService() {
        return "hello as service";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@RequestParam String name, @RequestParam int age) {
        return helloService.getUser(name, age);
    }

    @RequestMapping(value = "/apply",method = RequestMethod.GET)
    public ResponseObject applyResponseTest(@RequestParam String userName) {
      return helloService.applyResponseTest(userName);
    }
}
