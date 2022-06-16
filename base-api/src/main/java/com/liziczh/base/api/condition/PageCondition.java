package com.liziczh.base.api.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageCondition {
    private Integer pageNum = 1;
    private Integer pageSize = 20;
}
