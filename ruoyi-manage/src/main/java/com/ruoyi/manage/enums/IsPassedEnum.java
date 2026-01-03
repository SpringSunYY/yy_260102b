package com.ruoyi.manage.enums;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 是否及格 枚举
 */
public enum IsPassedEnum {

    /**
     * 及格
     */
    IS_PASSED_0("0", "及格"),

    /**
     * 不及格
     */
    IS_PASSED_1("1", "不及格");

    private static final Map<String, IsPassedEnum> VALUE_TO_ENUM = new HashMap<>();

    static {
        for (IsPassedEnum item : values()) {
            VALUE_TO_ENUM.put(item.value, item);
        }
    }

    private final String value;
    private final String label;

    IsPassedEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * 根据 value 获取枚举
     */
    public static Optional<IsPassedEnum> getEnumByValue(String value) {
        if (value == null || value.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(VALUE_TO_ENUM.get(value));
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
