package com.example.demo.controller.log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FaviconController {

    @RequestMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
        // 아무 동작도 하지 않음
    }
}
