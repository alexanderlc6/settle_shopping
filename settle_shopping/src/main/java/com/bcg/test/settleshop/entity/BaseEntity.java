package com.bcg.test.settleshop.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity base class created on 2019/6/13.
 */
@Data
public class BaseEntity<T extends Model> extends Model<T> {
    /**
     * Increasing ID(primary key)
     */
    @TableId("id")
    private Long id;

    /**
     * Optimistic lock flag
     */
    @Version
    @TableField("version")
    @JsonIgnore
    protected Long version;

    /**
     * Valid flag(0-valid(default),1-invalid)
     */
    @JsonIgnore
    @TableField("is_delete")
    protected Boolean delete;

    /**
     * creator user Id
     */
    @TableField("creator_id")
    @JsonIgnore
    private Long creatorId;

    /**
     * Record create time
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @TableField("created_at")
    @JsonIgnore
    private Date createdAt;

    /**
     * Record modify user Id
     */
    @TableField("modifier_id")
    @JsonIgnore
    private Long modifierId;

    /**
     * Record modify time
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @TableField("modified_at")
    @JsonIgnore
    private Date modifiedAt;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
