package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by 43967 on 2019/2/25.
 */
public interface FriendDao extends JpaRepository<Friend,String> {
    public Friend findByUseridAndFrienid(String userid,String frienid);

    @Query(value = "UPDATE tb_friend SET islike = ? WHERE userid =? AND friendid =?",nativeQuery = true)
    public void updateIslike(String islike,String userid,String frienid);

    @Query(value = "DELETE FROM tb_friend  WHERE userid =? AND friendid =?",nativeQuery = true)
    public void deleteFrriend(String userId, String friendid);
}
