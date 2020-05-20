package com.liziczh.base.common.service;

import org.springframework.stereotype.Service;

@Service
public interface BaseMgmService<T, K> {
	/**
	 * 新增数据
	 * @param entity 实体
	 */
	void addItem(T entity);
	/**
	 * 更新数据
	 * @param entity 实体
	 */
	void updateItem(T entity);
	/**
	 * 查询数据ByID
	 * @param id 主键
	 * @return 实体
	 */
	T get(K id);
	/**
	 * 删除数据ByID
	 * @param id 主键
	 */
	void delete(K id);
}
