package com.example.space.api.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liyu
 * @date 18-7-26
 */
@Controller
//@RestController包含@ResponseBody注解，不能解析返回的页面
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
       return "Hello World";
    }

    @RequestMapping("/")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("host", "http://www.baidu.com");
        return "index";
    }
}
