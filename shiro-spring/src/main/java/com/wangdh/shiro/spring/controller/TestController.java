package com.wangdh.shiro.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangdh
 * @Description
 * @date 2017-12-28
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return  "";
    }

}
