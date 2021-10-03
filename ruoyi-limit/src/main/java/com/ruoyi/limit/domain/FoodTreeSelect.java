package com.ruoyi.limit.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class FoodTreeSelect implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FoodTreeSelect> children;
    public FoodTreeSelect()
    {

    }
    public FoodTreeSelect(FoodType foodType)
    {
        this.id = foodType.getFoodTypeID();
        this.label = foodType.getFoodTypeName();
        this.children = foodType.getChildren().stream().map(FoodTreeSelect::new).collect(Collectors.toList());
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<FoodTreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<FoodTreeSelect> children)
    {
        this.children = children;
    }
}






