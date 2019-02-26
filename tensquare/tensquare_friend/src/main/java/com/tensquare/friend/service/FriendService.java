package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 43967 on 2019/2/25.
 */
@Service
@Transactional
public class FriendService {


    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userId, String friendid) {
        Friend friend = friendDao.findByUseridAndFrienid(userId, friendid);
        if(friend!=null){
            return 0;
        }
        friend=new Friend();
        friend.setUserid(userId);
        friend.setFrienid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        if(friendDao.findByUseridAndFrienid(friendid,userId)!=null){
            friendDao.updateIslike("1",userId,friendid);
            friendDao.updateIslike("1",friendid,userId);
        }
        return  1;
    }

    public int addNoFrriend(String userId, String friendid) {
        NoFriend noFriend = noFriendDao.findByUseridAndFrienid(userId, friendid);
        if(noFriend!=null){
            return  0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFrienid(friendid);
        noFriendDao.save(noFriend);
        return 1;

    }

    public void deleteFriend(String userId, String friendid) {
        //删除userid到firendid的数据
        friendDao.deleteFrriend(userId,friendid);
        //更新friendid到userid 的islike为0
        friendDao.updateIslike("0",friendid,userId);
        //非好友中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setFrienid(friendid);
        noFriend.setUserid(userId);
        noFriendDao.save(noFriend);

    }
}
