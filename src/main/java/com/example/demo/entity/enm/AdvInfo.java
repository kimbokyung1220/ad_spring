package com.example.demo.entity.enm;

public enum AdvInfo {
    HAVE_BALANCE("haveBalance", "정상"),
    NOHAVE_BALANCE("noHaveBalance","잔액 없음"),
    FREE("free", "무제한")
    ;

    private String key;
    private String value;

    AdvInfo(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }
    public String getValue() {
        return this.value;
    }
}
