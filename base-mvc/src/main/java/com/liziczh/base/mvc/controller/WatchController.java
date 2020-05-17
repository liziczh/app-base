package com.liziczh.base.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liziczh.base.common.controller.BaseController;

@RequestMapping(value = "/")
@RestController
public class WatchController extends BaseController {
	/**
	 * 健康检测地址
	 * @return alive
	 */
	@RequestMapping(value = "alive", method = RequestMethod.GET)
	public String alive() {
		return "alive";
	}
}
