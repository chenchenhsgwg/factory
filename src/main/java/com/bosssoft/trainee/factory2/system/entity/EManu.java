package com.bosssoft.trainee.factory2.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("e_manu")
public class EManu {

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField("productId")
    private Integer productId;

    /**
     *
     */
    @TableField("quantity")
    private Integer quantity;

}
