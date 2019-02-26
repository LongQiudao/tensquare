package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by 43967 on 2019/2/25.
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String> {
    public NoFriend findByUseridAndFrienid(String userid, String frienid);



}
