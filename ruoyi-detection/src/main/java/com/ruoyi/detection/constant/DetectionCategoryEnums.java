package com.ruoyi.detection.constant;

/**
 * @author zjccc
 * @date 2021/8/10
 * @Email:648629522@qq.com
 */
public enum DetectionCategoryEnums {
    one(1L, "委托"),
    two(2L, "仲裁"),
    three(3L, "其他");

    public static DetectionCategoryEnums codeOf(Long code) {
        for (DetectionCategoryEnums detectionCategoryEnums : values()) {
            if (detectionCategoryEnums.getValue() == code) {
                return detectionCategoryEnums;
            }
        }
        return null;
    }

    private final Long value;
    private final String filed;

    DetectionCategoryEnums(Long value, String filed) {
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
