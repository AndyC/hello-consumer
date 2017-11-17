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
    public String helloAsService(HttpServletRequest request) {
        String remoteIp = getClientIp(request);
        return "hello as service";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUser(@RequestParam String name, @RequestParam int age) {
        return helloService.getUser(name, age);
    }

    private static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();

            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        System.err.println("最终获得的客户端ip是{}" + ip);
        return ip;
    }
    @RequestMapping(value = "/apply",method = RequestMethod.GET)
    public ResponseObject applyResponseTest(@RequestParam String userName) {
      return helloService.applyResponseTest(userName);
    }
}
