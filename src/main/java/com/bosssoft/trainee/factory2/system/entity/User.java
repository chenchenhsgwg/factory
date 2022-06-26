package com.bosssoft.trainee.factory2.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {

    public User() {
    }

    public User(Integer id, String username, String password, String realname, String telephone, int factoryId, String factoryName, String factoryDescription, int roleId, String roleName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.telephone = telephone;
        this.factoryId = factoryId;
        this.factoryName = factoryName;
        this.factoryDescription = factoryDescription;
        this.roleId = roleId;
        this.roleName = roleName;
    }

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
