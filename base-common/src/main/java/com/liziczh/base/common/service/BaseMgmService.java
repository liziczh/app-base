package com.liziczh.base.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface BaseMgmService<T, PK, C> {
	/**
	 * 条件查询
	 * @param condition 条件
	 * @return 数据
	 */
	List<T> selectByCondition(C condition);
	/**
	 * 查询全部数据
	 * @return 数据集合
	 */
	List<T> getAll();
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
	T get(PK id);
	/**
	 * 删除数据ByID
	 * @param id 主键
	 */
	void delete(PK id);
}
