package com.ruoyi.detection.constant;

/**
 * @author zjccc
 * @date 2021/8/10
 * @Email:648629522@qq.com
 */
public enum SamplePriorityEnums {
    one(1, "正常"),
    two(2, "急"),
    three(3, "加急"),
    four(4, "特急");

    public static SamplePriorityEnums codeOf(int code) {
        for (SamplePriorityEnums sampleStatusEnums : values()) {
            if (sampleStatusEnums.getValue() == code) {
                return sampleStatusEnums;
            }
        }
        return null;
    }

    private final Integer value;
    private final String filed;

    SamplePriorityEnums(Integer value, String filed) {
        this.value = value;
        this.filed = filed;
    }

    public Integer getValue() {
        return value;
    }

    public String getFiled() {
        return filed;
    }

//    public static void main(String[] args) {
//        System.out.println(SamplePriorityEnums.codeOf(1).getFiled());
//    }
}
