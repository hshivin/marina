package com.hivin.enums;

/**
 * 错误日志类型
 */
public enum ErrorType {
    JENKINS_ERROR(1, "jenkins报错-"),
    JOB_ERROR(2, "job错误-"),
    BUILD_ERROR(3, "build报错-");

    private int id;
    private String name;


    ErrorType(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ErrorType selectById(int id) {
        for (ErrorType errorType : ErrorType.values()) {
            if (errorType.getId() == id) {
                return errorType;
            }
        }
        return null;
    }
}
