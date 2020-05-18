package com.zcl.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zcl.user.pojo.Babygrow;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface BabygrowDao extends JpaRepository<Babygrow,String>,JpaSpecificationExecutor<Babygrow>{
	
}
