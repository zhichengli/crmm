package com.crmm.spit.service;

import com.crmm.spit.dao.SpitDao;
import com.crmm.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import java.util.List;

@Service
@Transactional
public class SpitService {
    @Autowired
    SpitDao spitDao;

    @Autowired
    IdWorker idWorker;

    @Autowired
    MongoTemplate mongoTemplate;


    /**
     * /spit
     * 增加吐槽
     */
    public void save(Spit spit){
        if(!StringUtils.isEmpty(spit.getParentid())){
            Query query  = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));

            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }

        spit.set_id(idWorker.nextId()+"");
        spitDao.save(spit);
    }

    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    public void update(Spit spit){
        spitDao.save(spit);
    }

    public void deleteById(String id){
        spitDao.deleteById(id);
    }


    public Page<Spit> findByParentId(String parentId, int page, int size){
        PageRequest pr = PageRequest.of(page-1, size);

        return spitDao.findByParentid(parentId,pr);
    }

   /* public void thumbup(String spitId) {
        Spit spit = spitDao.findById(spitId).get();
        spit.setThumbup(spit.getThumbup()==null? 1: spit.getThumbup()+1);
        spitDao.save(spit);
    }*/

    public void thumbup(String spitId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));

        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
}































