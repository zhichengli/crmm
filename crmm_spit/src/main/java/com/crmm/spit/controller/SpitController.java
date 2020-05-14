package com.crmm.spit.controller;

import com.crmm.spit.pojo.Spit;
import com.crmm.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    //按类型注入，如果找不到，按名称注入，如果直接按照名称注入
    //@Qualifier()
    //@Resource(name = "SpitService")按名称找,如果不设置name，找不到就按类型找。
    @Autowired
    SpitService spitService;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findById(spitId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.DELETE)
    public Result update(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * /spit/thumbup/{spitId}
     * 吐槽点赞
     */
    @RequestMapping(value = "/thumbup/{spitId}",method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId){
        String userid = "111";
        if(redisTemplate.opsForValue().get("thumbup_"+userid)!=null){
            return new Result(false,StatusCode.REPERROR,"不能重复提交");
        }
        redisTemplate.opsForValue().set("thumbup_"+userid,1);
        spitService.thumbup(spitId);
        return new Result(true,StatusCode.OK,"点赞成功");
    }


    /**
     * /spit/comment/{parentid}/{page}/{size}
     * 根据上级ID查询吐槽数据（分页）
     */


    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public Result findByParentId(@PathVariable String parentid,
                                 @PathVariable int page,
                                 @PathVariable int size){
        Page<Spit> pageSpit = spitService.findByParentId(parentid, page, size);
        PageResult<Spit> pageResult = new PageResult<>(pageSpit.getTotalElements(),pageSpit.getContent());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }


































}
