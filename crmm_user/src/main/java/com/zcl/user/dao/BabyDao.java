package com.zcl.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zcl.user.pojo.Baby;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface BabyDao extends JpaRepository<Baby,String>,JpaSpecificationExecutor<Baby>{
	
}
