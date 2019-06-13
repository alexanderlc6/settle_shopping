package com.bcg.test.settleshop.enums;

/**
 * System error definitions
 */
public enum SystemErrorCodeEnum {
    /**
     * Common SUCCESS code
     */
    SUCCESS(20000, "Succeed!"),

    /**
     * System error code
     */
    ILLEGAL_PARAMETER(40001,"Parameter error!"),
    INTERFACE_ERROR(50001,"Interface invoke failed!"),
    DATABASE_ERROR(50002,"DB connection failed!"),
    IO_ERROR(50003,"IO error occurred!"),
    DATEBASE_ERROR(50004,"DB operation failed!"),
    API_ERROR(50006,"Api invoke failed!"),
    SYSTEM_ERROR(50000, "System unexpected error!"),
    GET_INSTANCE_ERROR(40008, "Get instance failed!"),
    ;

    /**
     * Error code
     */
    private int code;

    /**
     * Error message
     */
    private String message;


    /**
     * Constructor
     *
     * @param code    Error code
     * @param message Error message
     */
    SystemErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message != null ? message : "";
    }

    /**
     * Get error message
     *
     * @return int
     */
    public int getCode() {
        return code;
    }

    /**
     * Get error message
     *
     * @return String
     */
    public String getMessage() {
        return message;
    }
}
