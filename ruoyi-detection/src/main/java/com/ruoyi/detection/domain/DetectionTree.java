package com.ruoyi.detection.domain;

import java.util.List;

public class DetectionTree {

    private String label;

    private List<DetectionTree> children;

    public DetectionTree(String label) {
        this.label = label;
    }

    public DetectionTree(String label, List<DetectionTree> children) {
        this.label = label;
        this.children = children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<DetectionTree> getChildren() {
        return children;
    }

    public void setChildren(List<DetectionTree> children) {
        this.children = children;
    }
}
