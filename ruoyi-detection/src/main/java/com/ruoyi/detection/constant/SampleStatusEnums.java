package com.ruoyi.detection.constant;

/**
 * @author zjccc
 * @date 2021/8/10
 * @Email:648629522@qq.com
 */
public enum SampleStatusEnums {
    one(1L, "固体"),
    two(2L, "液体"),
    three(3L, "胶体"),
    four(4L, "气体"),
    five(5L,"其他");


    public static SampleStatusEnums codeOf(Long code) {
        for (SampleStatusEnums sampleStatusEnums : values()) {
            if (sampleStatusEnums.getValue() == code) {
                return sampleStatusEnums;
            }
        }
        return null;
    }

    private final Long value;
    private final String filed;

    SampleStatusEnums(Long value, String filed) {
        this.value = value;
        this.filed = filed;
    }

    public Long getValue() {
        return value;
    }

    public String getFiled() {
        return filed;
    }
}
