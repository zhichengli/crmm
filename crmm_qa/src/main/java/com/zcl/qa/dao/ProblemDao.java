package com.zcl.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zcl.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    /**
     * 最新问答列表
     */
    @Query(value = "SELECT * FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid=?) ORDER BY replytime DESC",nativeQuery = true)
    public Page<Problem> newList(String labelid, Pageable pageable);

    /**
     * 热门问答列表
     */
    @Query(value = "SELECT * FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid=?) ORDER BY reply DESC",nativeQuery = true)
    public Page<Problem> hotList(String labelid, Pageable pageable);
    /**
     * 等待回答列表
     */

    @Query(value = "SELECT * FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid=?) and reply=0  ORDER BY createtime DESC",nativeQuery = true)
    public Page<Problem> waitList(String labelid, Pageable pageable);

}