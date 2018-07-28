package com.example.space.api.web;

import com.example.space.framework.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.example.space.framework.exception.MsgEnum.JSON_CONVERT_ERROR;

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

    @RequestMapping("/testErrorHandler")
    public String testErrorHandler(ModelMap modelMap) throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/testMyException")
    public String testMyException(ModelMap modelMap) throws Exception {
        throw new MyException(JSON_CONVERT_ERROR);
    }
}
