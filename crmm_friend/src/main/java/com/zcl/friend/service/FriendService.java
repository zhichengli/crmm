package com.zcl.friend.service;

import com.zcl.friend.dao.FriendDao;
import com.zcl.friend.dao.NoFriendDao;
import com.zcl.friend.pojo.Friend;
import com.zcl.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;
    public int addFriend(String userid, String friendid) {
        //先判断userid到friend是否有数据，有就是重复添加好友
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if(friend!= null){
            return 0;
        }
        //直接添加好友，让好友表中userid到friend方向的type为0
        friend=new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        Friend save = friendDao.save(friend);
        System.out.println(save);
        //friend到userid方向是否有数据，如果有，把双方都改为1
        if(friendDao.findByUseridAndFriendid(friendid,userid)!= null){
            friendDao.updateIslike("1",userid,friendid);
            friendDao.updateIslike("1",friendid,userid);
        }

        return 1;
    }

    public int addNoFriend(String userid, String friendid) {
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid,friendid);
        if(noFriend != null){
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userid, String friendid) {
        friendDao.deletefriend(userid,  friendid);
        friendDao.updateIslike("0",friendid,userid);
        noFriendDao.save(new NoFriend(userid,friendid));
    }
}
