package com.bosssoft.trainee.factory2.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("factory")
public class Factory {

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField("name")
    private String name;

    /**
     *
     */
    @TableField("description")
    private String description;

    /**
     *
     */
    @TableField("userId")
    private Integer userId;

    @TableField(exist = false)
    private String username;

    /**
     *
     */
    @TableField("enabled")
    private Boolean enabled;

}
