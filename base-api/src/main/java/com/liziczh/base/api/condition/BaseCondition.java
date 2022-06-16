package com.liziczh.base.api.condition;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 基础查询条件
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:11 上午
 */
@Data
public abstract class BaseCondition {
    private PageCondition page;
    private List<SortCondition> sortList;
    private Date startTime;
    private Date endTime;
}
