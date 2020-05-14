package com.zcl.friend.dao;

import com.zcl.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {
    public Friend findByUseridAndFriendid(String userid,String friendid);

    @Modifying
    @Query(value = "UPDATE tb_friend SET islike= ? WHERE userid= ? AND friendid= ?",nativeQuery = true)
    public void updateIslike(String islike,String userid,String friendid);

    @Modifying
    @Query(value = "delete from tb_friend where userid= ? AND friendid= ?",nativeQuery = true)
    public void deletefriend(String userid, String friendid);
}

