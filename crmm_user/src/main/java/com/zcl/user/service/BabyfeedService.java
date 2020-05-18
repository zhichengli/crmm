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

import com.zcl.user.dao.BabyfeedDao;
import com.zcl.user.pojo.Babyfeed;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class BabyfeedService {

	@Autowired
	private BabyfeedDao babyfeedDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Babyfeed> findAll() {
		return babyfeedDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Babyfeed> findSearch(Map whereMap, int page, int size) {
		Specification<Babyfeed> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return babyfeedDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Babyfeed> findSearch(Map whereMap) {
		Specification<Babyfeed> specification = createSpecification(whereMap);
		return babyfeedDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Babyfeed findById(String id) {
		return babyfeedDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param babyfeed
	 */
	public void add(Babyfeed babyfeed) {
		babyfeed.setId( idWorker.nextId()+"" );
		babyfeedDao.save(babyfeed);
	}

	/**
	 * 修改
	 * @param babyfeed
	 */
	public void update(Babyfeed babyfeed) {
		babyfeedDao.save(babyfeed);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		babyfeedDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Babyfeed> createSpecification(Map searchMap) {

		return new Specification<Babyfeed>() {

			@Override
			public Predicate toPredicate(Root<Babyfeed> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 宝宝id
                if (searchMap.get("babyid")!=null && !"".equals(searchMap.get("babyid"))) {
                	predicateList.add(cb.like(root.get("babyid").as(String.class), "%"+(String)searchMap.get("babyid")+"%"));
                }
                // 创建者id
                if (searchMap.get("userid")!=null && !"".equals(searchMap.get("userid"))) {
                	predicateList.add(cb.like(root.get("userid").as(String.class), "%"+(String)searchMap.get("userid")+"%"));
                }
                // 喂养描述
                if (searchMap.get("description")!=null && !"".equals(searchMap.get("description"))) {
                	predicateList.add(cb.like(root.get("description").as(String.class), "%"+(String)searchMap.get("description")+"%"));
                }
                // 更新者id
                if (searchMap.get("updateuserid")!=null && !"".equals(searchMap.get("updateuserid"))) {
                	predicateList.add(cb.like(root.get("updateuserid").as(String.class), "%"+(String)searchMap.get("updateuserid")+"%"));
                }
                // 备注
                if (searchMap.get("remark")!=null && !"".equals(searchMap.get("remark"))) {
                	predicateList.add(cb.like(root.get("remark").as(String.class), "%"+(String)searchMap.get("remark")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
