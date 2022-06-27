package com.bosssoft.trainee.factory2.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(exist = false)
    private String userId;

    /**
     *
     */
    @TableField("username")
    private String username;

    /**
     *
     */
    @TableField("password")
    private String password;

    /**
     *
     */
    @TableField("realname")
    private String realname;

    /**
     *
     */
    @TableField("telephone")
    private String telephone;

    /**
     *
     */

    @TableField(exist = false)
    private int factoryId;

    @TableField(exist = false)
    private String factoryName;

    @TableField(exist = false)
    private String factoryDescription;

    @TableField(exist = false)
    private int roleId;

    @TableField(exist = false)
    private String roleName;

}
