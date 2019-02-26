package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
	public User findByEmail(String email);

    @Query(value = "UPDATE tb_user SET fanscount = fanscount+? WHERE id=?",nativeQuery = true)
    public void updateFanscount(int x, String friendid);

    @Query(value = "UPDATE tb_user SET followcount=followcount+?WHERE id=?",nativeQuery = true)
    public void updateFollowcount(int x, String userid);
}
