package com.zcl.user.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zcl.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

    public User findByMobile(String mobile);

    @Modifying
    @Query(value = "update tb_user set fanscount=fanscount+? where id=?",nativeQuery = true)
    public void updatefanscount(int x, String friendid);

    @Modifying
    @Query(value = "update tb_user set followcount=followcount+? where id=?",nativeQuery = true)
    public void updatefollowcount(int x, String userid);



    @Query(value = "SELECT * FROM tb_user WHERE  id IN (SELECT targetuser FROM tb_follow WHERE tb_follow.userid=?)",nativeQuery = true)
    public Page<User> findFollows(String userid, Pageable pageable);



    @Query(value = "SELECT * FROM tb_user WHERE  id IN (SELECT userid FROM tb_follow WHERE tb_follow.targetuser=?)",nativeQuery = true)
    public Page<User> findFans(String userid, Pageable pageable);
}
