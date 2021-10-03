package com.ruoyi.detection.domain.vo;

import java.io.Serializable;

/**
 * @author liukun
 * @date 2021/8/6
 */
public class TaskAssignAndDetectionItemListVO implements Serializable {
    /**
     * 检品ID
     */
    private Long sampleID;
    /**
     * 检品编号
     */
    private String sampleNumber;
    /**
     * 检品学名
     */
    private String sampleAcademicName;
    /**
     * 检品检测标准ID
     */
    private String detectionStandardID;
    /**
     * 检品检测标准
     */
    private String detectionStandardName;
}
