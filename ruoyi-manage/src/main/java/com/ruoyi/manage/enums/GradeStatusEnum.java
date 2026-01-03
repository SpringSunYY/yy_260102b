package com.ruoyi.manage.enums;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 成绩审核状态 枚举
 */
public enum GradeStatusEnum {

    /**
     * 待审核
     */
    GRADE_STATUS_0("0", "待审核"),

    /**
     * 同意
     */
    GRADE_STATUS_1("1", "同意"),

    /**
     * 驳回
     */
    GRADE_STATUS_2("2", "驳回");

    private static final Map<String, GradeStatusEnum> VALUE_TO_ENUM = new HashMap<>();

    static {
        for (GradeStatusEnum item : values()) {
            VALUE_TO_ENUM.put(item.value, item);
        }
    }

    private final String value;
    private final String label;

    GradeStatusEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 数据库存储值
     * @return 对应枚举（Optional）
     */
    public static Optional<GradeStatusEnum> getEnumByValue(String value) {
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
