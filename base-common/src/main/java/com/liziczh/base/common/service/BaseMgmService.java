package com.liziczh.base.common.service;

public interface BaseMgmService<T, K> {
	/**
	 * 查询数据ByID
	 * @param id 主键
	 * @return 实体
	 */
	T getById(K id);

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
	 * 删除数据ByID
	 * @param id 主键
	 */
	void deleteById(K id);
}
