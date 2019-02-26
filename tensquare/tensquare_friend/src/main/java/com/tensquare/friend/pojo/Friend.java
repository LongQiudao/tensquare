package com.tensquare.friend.pojo;



import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by 43967 on 2019/2/25.
 */
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Friend implements Serializable{

    private String userid;
    private String frienid;
    private String islike;
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFrienid() {
        return frienid;
    }

    public void setFrienid(String frienid) {
        this.frienid = frienid;
    }

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }


}
