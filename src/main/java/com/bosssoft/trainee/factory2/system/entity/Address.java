package com.bosssoft.trainee.factory2.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("address")
public class Address {

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField("userId")
    private Integer userId;

    /**
     *
     */
    @TableField("receiver")
    private String receiver;

    /**
     *
     */
    @TableField("telephone")
    private String telephone;

    /**
     *
     */
    @TableField("location")
    private String location;

}
