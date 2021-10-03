package com.ruoyi.limit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 样品管理对象 xlk_sp
 * 
 * @author wlf
 * @date 2021-07-20
 */
public class Food extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 样品ID */
    private Long foodID;

    /** 样品类别ID */
//    @Excel(name = "样品类别ID")
    private Long foodTypeID;

    /**样品类别层级**/
    @Excel(name = "样品类别层级")
    private String foodTypeStr;

    /** 样品名称 */
    @Excel(name = "样品名称")
    private String foodNickName;

    /** 样品学名 */
    @Excel(name = "样品学名")
    private String foodName;

    /** 测定部位 */
    @Excel(name = "测定部位")
    private String detectionPart;

    /** 检测中心ID */
    private Long detectionCenterID;

    /** 数据有效性标识 */
    private Integer isValid;


    public void setFoodID(Long foodID) 
    {
        this.foodID = foodID;
    }

    public Long getFoodID() 
    {
        return foodID;
    }
    public void setFoodTypeID(Long foodTypeID) 
    {
        this.foodTypeID = foodTypeID;
    }

    public Long getFoodTypeID() 
    {
        return foodTypeID;
    }
    public void setFoodNickName(String foodNickName) 
    {
        this.foodNickName = foodNickName;
    }

    public String getFoodTypeStr() {
        return foodTypeStr;
    }
    public void setFoodTypeStr(String foodTypeStr) {
        this.foodTypeStr = foodTypeStr;
    }
    
    public String getFoodNickName() 
    {
        return foodNickName;
    }
    public void setFoodName(String foodName) 
    {
        this.foodName = foodName;
    }

    public String getFoodName() 
    {
        return foodName;
    }
    public void setDetectionPart(String detectionPart) 
    {
        this.detectionPart = detectionPart;
    }

    public String getDetectionPart() 
    {
        return detectionPart;
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

    public Food() {
    }

    public Food(Long foodTypeID, String foodName, String foodNickName,  String detectionPart) {
        this.foodTypeID = foodTypeID;
        this.foodNickName = foodNickName;
        this.foodName = foodName;
        this.detectionPart = detectionPart;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodID=" + foodID +
                ", foodTypeID=" + foodTypeID +
                ", foodTypeStr='" + foodTypeStr + '\'' +
                ", foodNickName='" + foodNickName + '\'' +
                ", foodName='" + foodName + '\'' +
                ", detectionPart='" + detectionPart + '\'' +
                ", detectionCenterID=" + detectionCenterID +
                ", isValid=" + isValid +
                '}';
    }
}
