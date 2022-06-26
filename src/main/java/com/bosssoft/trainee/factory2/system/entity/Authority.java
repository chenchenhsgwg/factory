package com.bosssoft.trainee.factory2.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("authority")
public class Authority {

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
    @TableField("resource")
    private String resource;

    @TableField("path")
    private String path;

    @TableField("component")
    private String component;

    @TableField("icon")
    private String icon;

    @TableField("parentId")
    private int parentId;

    @TableField(exist = false)
    private String parentName;


}
