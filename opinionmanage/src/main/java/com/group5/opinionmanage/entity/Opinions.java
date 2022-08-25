package com.group5.opinionmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author 10569
 * @version 1.0
 * @description 数据库实体类
 * @Date 2022/8/24 14:17
 */

@Getter
@Setter

@Entity
@SuppressWarnings("JpaQlInspection")
@Table(name = "opinions")
public class Opinions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;

    private String context;

    private String type;

    private Integer feature;

    private String keyword;

    public Opinions(){}
    public Opinions(Integer oid, String context, String type, int feature, String keyword) {
        this.oid = oid;
        this.context = context;
        this.type = type;
        this.feature = feature;
        this.keyword = keyword;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFeature() {
        return feature;
    }

    public void setFeature(Integer feature) {
        this.feature = feature;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }


    public Integer getOid() {
        return oid;
    }
}


