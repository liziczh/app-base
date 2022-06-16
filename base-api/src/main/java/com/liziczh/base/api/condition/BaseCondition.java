package com.liziczh.base.api.condition;

import java.util.List;

import lombok.Data;

@Data
public abstract class BaseCondition {
    private PageCondition pageCondition;
    private List<SortCondition> sortConditionList;
}
