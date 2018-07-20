package com.nowcoder.wenda.async;

public enum EventType {
    LOGIN(0),
    LiKE(1),
    DISLiKE(2),
    COMMENT(3);


    private int value;

    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
