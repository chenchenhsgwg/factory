package com.bosssoft.trainee.factory2.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("equipment")
public class Equipment {

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField("typeId")
    private Integer typeId;

    @TableField(exist = false)
    private String typeName;

    /**
     *
     */
    @TableField("name")
    private String name;

    /**
     *
     */
    @TableField("state")
    private String state;

    @TableField("enabled")
    private Boolean enabled;

    /**
     *
     */
    @TableField("factoryId")
    private Integer factoryId;

    @TableField(exist = false)
    private String factoryName;

    @TableField(exist = false)
    private String rentFactoryId;

    @TableField(exist = false)
    private String rentFactoryName;

}
