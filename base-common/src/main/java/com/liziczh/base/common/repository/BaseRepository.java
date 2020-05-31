package com.liziczh.base.common.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<E, PK, C> {
	/**
	 * 条件查询
	 * @return 实体
	 */
	List<E> selectByCondition(C condition);
	/**
	 * 查询全部
	 * @return 实体
	 */
	List<E> getAll();
	/**
	 * 查询 By ID
	 * @param pk 主键
	 * @return 实体
	 */
	E get(PK pk);
	/**
	 * 新增
	 * @param entity 实体
	 * @return id
	 */
	int insert(E entity);
	/**
	 * 更新
	 * @param entity 实体
	 * @return id
	 */
	int update(E entity);
	/**
	 * 删除
	 * @param pk 主键
	 * @return
	 */
	int delete(PK pk);
}
