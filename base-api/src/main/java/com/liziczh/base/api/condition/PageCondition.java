package com.liziczh.base.api.condition;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageCondition implements Serializable {
	private static final long serialVersionUID = 1487852855435923600L;
	private Integer pageNum = 1;
	private Integer pageSize = 20;
}
