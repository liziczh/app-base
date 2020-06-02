package com.liziczh.base.common.condition;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class BaseCondition implements Serializable {
	private static final long serialVersionUID = 5582629689714183859L;
	private PageCondition pageCondition;
	private SortCondition sortCondition;
}
