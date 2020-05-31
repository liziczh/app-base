package com.liziczh.base.common.condition;

import java.io.Serializable;

import lombok.Data;

@Data
public class PageCondition implements Serializable {
	private static final long serialVersionUID = 1487852855435923600L;
	private Integer pageNum = 1;
	private Integer pageSize = 20;
}
