package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 43967 on 2019/2/25.
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserClient userClient;
    /**
     * 删除好友
     * */
    @RequestMapping(value = "/{friendid}",method = RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable String friendid){
        //验证是否登录 拿到当前用户id
        Claims claims =(Claims)request.getAttribute("user_claims");
        if(claims==null ){
            return new Result(false,StatusCode.ERROR,"权限不足");
        }
        String userId = claims.getId();
        friendService.deleteFriend(userId,friendid);
        userClient.updateFanscountAndFollowcount(userId,friendid,-1);
        return new Result(true,StatusCode.OK,"添加成功");
    }
    /**
     * 添加好友
     * */
    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid,@PathVariable String type){
        //验证是否登录 拿到当前用户id
        Claims claims =(Claims)request.getAttribute("user_claims");
        if(claims==null ){
            return new Result(false,StatusCode.ERROR,"权限不足");
        }
        String userId = claims.getId();
        //p判断是添加好友还是非好友
        if(type!=null ){
            if(type.equals("1")){
                //添加好友
                int flag =friendService.addFriend(userId,friendid);
                if(flag==0){
                    return new Result(false, StatusCode.ERROR,"重复添加");
                }
                if(flag==1){
                    userClient.updateFanscountAndFollowcount(userId,friendid,1);
                    return new Result(true,StatusCode.OK,"添加成功");
                }
            }else if(type.equals("2")){
                //t添加非好友
                int flag= friendService.addNoFrriend(userId,friendid);
                if(flag==0){
                    return new Result(false, StatusCode.ERROR,"重复添加");
                }
                if(flag==1){
                    return new Result(true,StatusCode.OK,"添加成功");
                }
            }
            return new Result(false, StatusCode.ERROR,"参数异常");
        }else{
            return new Result(false, StatusCode.ERROR,"参数异常");
        }

    }
}
