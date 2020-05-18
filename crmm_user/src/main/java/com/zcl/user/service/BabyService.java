package com.zcl.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import util.IdWorker;

import com.zcl.user.dao.BabyDao;
import com.zcl.user.pojo.Baby;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class BabyService {

	@Autowired
	private BabyDao babyDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Baby> findAll() {
		return babyDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Baby> findSearch(Map whereMap, int page, int size) {
		Specification<Baby> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return babyDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Baby> findSearch(Map whereMap) {
		Specification<Baby> specification = createSpecification(whereMap);
		return babyDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Baby findById(String id) {
		return babyDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param baby
	 */
	public void add(Baby baby) {
		baby.setId( idWorker.nextId()+"" );
		babyDao.save(baby);
	}

	/**
	 * 修改
	 * @param baby
	 */
	public void update(Baby baby) {
		babyDao.save(baby);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		babyDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Baby> createSpecification(Map searchMap) {

		return new Specification<Baby>() {

			@Override
			public Predicate toPredicate(Root<Baby> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 创建人id
                if (searchMap.get("userid")!=null && !"".equals(searchMap.get("userid"))) {
                	predicateList.add(cb.like(root.get("userid").as(String.class), "%"+(String)searchMap.get("userid")+"%"));
                }
                // 姓名
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 昵称
                if (searchMap.get("nickname")!=null && !"".equals(searchMap.get("nickname"))) {
                	predicateList.add(cb.like(root.get("nickname").as(String.class), "%"+(String)searchMap.get("nickname")+"%"));
                }
                // 性别
                if (searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))) {
                	predicateList.add(cb.like(root.get("sex").as(String.class), "%"+(String)searchMap.get("sex")+"%"));
                }
                // 头像
                if (searchMap.get("avatar")!=null && !"".equals(searchMap.get("avatar"))) {
                	predicateList.add(cb.like(root.get("avatar").as(String.class), "%"+(String)searchMap.get("avatar")+"%"));
                }
                // 出生地
                if (searchMap.get("address")!=null && !"".equals(searchMap.get("address"))) {
                	predicateList.add(cb.like(root.get("address").as(String.class), "%"+(String)searchMap.get("address")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

	public List<Baby> findAllByUserId(String userid) {
		return babyDao.findAllByUserid(userid);
	}
}
