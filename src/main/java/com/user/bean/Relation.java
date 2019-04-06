/**
 * 用户权限映射
 * 与数据库中表role_user_relation一一对应
 */
package com.user.bean;


import javax.persistence.*;

@Entity
@Table(name = "role_user_relation")
public class Relation {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int relation_id;
    private int role_id;
    private int user_id;

    public int getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(int relation_id) {
        this.relation_id = relation_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
