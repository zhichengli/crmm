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
@Table(name="tb_baby")
public class Baby implements Serializable{

	@Id
	private String id;//ID


	
	private String userid;//创建人id
	private String name;//姓名
	private String nickname;//昵称
	private java.util.Date birthday;//出生年月日
	private String sex;//性别
	private String avatar;//头像
	private java.util.Date createdate;//创建日期
	private java.util.Date updatedate;//修改日期
	private String address;//出生地

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {		
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {		
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public java.util.Date getBirthday() {		
		return birthday;
	}
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {		
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAvatar() {		
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public java.util.Date getCreatedate() {		
		return createdate;
	}
	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}

	public java.util.Date getUpdatedate() {		
		return updatedate;
	}
	public void setUpdatedate(java.util.Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getAddress() {		
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	
}
