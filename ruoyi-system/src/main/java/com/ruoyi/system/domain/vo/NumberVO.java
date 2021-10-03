package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;
import java.util.List;

/**
 * @author liukun
 * @date 2021/8/22
 */
public class NumberVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 编号字段ID */
    private Long numberID;

    /** 编号种类ID */
    @Excel(name = "编号种类ID")
    private Integer numberTypeID;

    /** 编号字段内容 */
    @Excel(name = "编号字段内容")
    private String content;

    /** 编号字段长度 */
    @Excel(name = "编号字段长度")
    private Integer contentLength;

    /** 编号字段描述 */
    @Excel(name = "编号字段描述")
    private String description;

    /** 顺序 */
    @Excel(name = "顺序")
    private Integer order;

    List<NumberVO> children;


    public NumberVO() {
    }

    public NumberVO(Long numberID, Integer numberTypeID, String content, Integer contentLength, String description, Integer order, List<NumberVO> children) {
        this.numberID = numberID;
        this.numberTypeID = numberTypeID;
        this.content = content;
        this.contentLength = contentLength;
        this.description = description;
        this.order = order;
        this.children = children;
    }

    public Long getNumberID() {
        return numberID;
    }

    public void setNumberID(Long numberID) {
        this.numberID = numberID;
    }

    public Integer getNumberTypeID() {
        return numberTypeID;
    }

    public void setNumberTypeID(Integer numberTypeID) {
        this.numberTypeID = numberTypeID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentLength() {
        return contentLength;
    }

    public void setContentLength(Integer contentLength) {
        this.contentLength = contentLength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<NumberVO> getChildren() {
        return children;
    }

    public void setChildren(List<NumberVO> children) {
        this.children = children;
    }
}
