package com.liziczh.base.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/")
@RestController
public class WatchController {
    /**
     * 健康检测
     *
     * @param
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 12:08 上午
     */
    @RequestMapping(value = "alive", method = RequestMethod.GET)
    public String alive() {
        return "alive";
    }
}
