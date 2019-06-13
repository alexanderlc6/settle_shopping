package com.bcg.test.settleshop.enums;

/**
 * System error definitions
 */
public enum BusinessErrorCodeEnum {
    CANNOT_FOUND_SKU_BASE_INFO(40001,"Cannot found sku base information,please check it!"),
    CANNOT_FOUND_PROMOTION_RULE(40002,"Cannot found promotion rule info!"),
    SKU_INVENTORY_NOT_ENOUGH(40003, "Sku inventory quantity not enough, buy process failed!"),
    PROCESS_SKU_PROMOTION_SETTLEMENT_FAILED(40004, "Process sku promotion settlement failed!")
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
    BusinessErrorCodeEnum(int code, String message) {
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
