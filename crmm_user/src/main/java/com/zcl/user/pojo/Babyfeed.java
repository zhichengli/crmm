package com.zcl.user.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_babyfeed")
public class Babyfeed implements Serializable{

	@Id
	private String id;//ID


	
	private String babyid;//宝宝id
	private String userid;//创建者id
	private Integer type;//类型
	private java.util.Date starttime;//记录时间
	private Double value;//喂养数据
	private String description;//喂养描述
	private java.util.Date createtime;//创建时间
	private java.util.Date updatetime;//更新时间
	private String updateuserid;//更新者id
	private Integer version;//版本
	private String remark;//备注

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getBabyid() {		
		return babyid;
	}
	public void setBabyid(String babyid) {
		this.babyid = babyid;
	}

	public String getUserid() {		
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getType() {		
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public java.util.Date getStarttime() {		
		return starttime;
	}
	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}

	public Double getValue() {		
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}

	public String getDescription() {		
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public java.util.Date getCreatetime() {		
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	public java.util.Date getUpdatetime() {		
		return updatetime;
	}
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdateuserid() {		
		return updateuserid;
	}
	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}

	public Integer getVersion() {		
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getRemark() {		
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	
}
