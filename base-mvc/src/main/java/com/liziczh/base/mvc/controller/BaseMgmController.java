package com.liziczh.base.mvc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liziczh.base.common.entity.BaseEntity;
import com.liziczh.base.common.result.Result;
import com.liziczh.base.common.result.ResultBuilder;
import com.liziczh.base.common.service.BaseMgmService;

@RestController
public abstract class BaseMgmController<T extends BaseEntity, K> extends BaseController {
	public abstract String getIndex() throws Exception;
	public abstract BaseMgmService<T, K> getService() throws Exception;
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() throws Exception {
		return getIndex();
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result<String> add(T entity) throws Exception {
		getService().addItem(entity);
		return new ResultBuilder<String>().complete("操作成功");
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Result<String> update(T entity) throws Exception {
		getService().updateItem(entity);
		return new ResultBuilder<String>().complete("操作成功");
	}
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Result<T> getById(@PathVariable K id) throws Exception {
		T entity = getService().get(id);
		return new ResultBuilder<T>().complete(entity);
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public Result<String> delete(@PathVariable K id) throws Exception {
		getService().delete(id);
		return new ResultBuilder<String>().complete("操作成功");
	}
}