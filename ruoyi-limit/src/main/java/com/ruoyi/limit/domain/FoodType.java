package com.ruoyi.limit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 食品类别管理对象 xlk_splb
 * 
 * @author wlf
 * @date 2021-07-20
 */
public class FoodType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 食品类别ID */
    private Long foodTypeID;

    /** 食品类别父ID（0-表示根父类） */
//    @Excel(name = "食品类别父ID", readConverterExp = "0=-表示根父类")
    private Long foodTypeParentID;

    /** 食品类别父名称（0-表示根父类） */
    @Excel(name = "食品类别层级")
    private String foodTypeStr;

    /** 食品类别名称 */
    @Excel(name = "食品类别名称")
    private String foodTypeName;

    /** 检测中心ID */
    private Long detectionCenterID;

    /** 数据有效性标识 */
    private Integer isValid;

    /**食品子类*/
    private List<FoodType> children = new ArrayList<FoodType>();

    public void setFoodTypeID(Long foodTypeID) 
    {
        this.foodTypeID = foodTypeID;
    }

    public Long getFoodTypeID() 
    {
        return foodTypeID;
    }
    public void setFoodTypeParentID(Long foodTypeParentID) 
    {
        this.foodTypeParentID = foodTypeParentID;
    }

    public String getFoodTypeStr() {
        return foodTypeStr;
    }

    public void setFoodTypeStr(String foodTypeStr) {
        this.foodTypeStr = foodTypeStr;
    }

    public Long getFoodTypeParentID()
    {
        return foodTypeParentID;
    }
    public void setFoodTypeName(String foodTypeName) 
    {
        this.foodTypeName = foodTypeName;
    }

    public String getFoodTypeName() 
    {
        return foodTypeName;
    }

    public Long getDetectionCenterID() {
        return detectionCenterID;
    }

    public void setDetectionCenterID(Long detectionCenterID) {
        this.detectionCenterID = detectionCenterID;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public List<FoodType> getChildren() {
        return children;
    }

    public void setChildren(List<FoodType> children) {
        this.children = children;
    }

    public FoodType(Long foodTypeParentID, String foodTypeName) {
        this.foodTypeParentID = foodTypeParentID;
        this.foodTypeName = foodTypeName;
    }

    public FoodType() {
    }

    public FoodType(Long foodTypeID, Long foodTypeParentID, String foodTypeName) {
        this.foodTypeID = foodTypeID;
        this.foodTypeParentID = foodTypeParentID;
        this.foodTypeName = foodTypeName;
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "foodTypeID=" + foodTypeID +
                ", foodTypeParentID=" + foodTypeParentID +
                ", foodTypeStr='" + foodTypeStr + '\'' +
                ", foodTypeName='" + foodTypeName + '\'' +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                ", children=" + children +
                '}';
    }
}
