package com.gta.train.platform.saas.entity.system;

import javax.persistence.Column;
import javax.persistence.Table;

import com.gta.train.platform.persis.mybatis.model.Entity;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_system_account")
public class SystemAccount extends Entity {
	private static final long serialVersionUID = 1L;

	    /**账号id*/
	@Column(name = "account_id")
	private String accountId;

	    /**授课专业授课专业*/
	@Column(name = "instruction_profession")
	private String instructionProfession;

	    /**手机号码手机号码*/
	@Column(name = "phone")
	private String phone;

	    /**研究方向研究方向*/
	@Column(name = "research_direction")
	private String researchDirection;

	    /**邮箱邮箱*/
	@Column(name = "mail")
	private String mail;

	    /**专业专业*/
	@Column(name = "profession")
	private String profession;

	    /**职务/身份职务/身份,0:xxx1:xx...*/
	@Column(name = "job")
	private Integer job;

	    /**年级年级*/
	@Column(name = "grade")
	private String grade;

	    /**班级班级*/
	@Column(name = "class_name")
	private String className;

	    /**学号学号*/
	@Column(name = "sno")
	private String sno;

	    /**微信号微信号*/
	@Column(name = "wx_account")
	private String wxAccount;

	    /**账号状态账号状态*/
	@Column(name = "account_status")
	private Integer accountStatus;

	    /**系统内部识别码系统内部识别码*/
	@Column(name = "identification_code")
	private String identificationCode;

	    /**备注备注*/
	@Column(name = "remarks")
	private String remarks;

	    /**是否未认证是否未认证:0否，1是*/
	@Column(name = "not_accreditation")
	private Integer notAccreditation;

	    /**二级绑定单位二级绑定单位*/
	@Column(name = "sec_binding")
	private String secBinding;

	    /**创建时间创建时间*/
	@Column(name = "create_time")
	private java.util.Date createTime;

	    /**修改时间*/
	@Column(name = "update_time")
	private java.util.Date updateTime;

	    /**删除标识0 未删除 1已删除*/
	@Column(name = "is_del")
	private Integer isDel;

	    /**新建人id*/
	@Column(name = "create_user_id")
	private String createUserId;

	    /**修改人id*/
	@Column(name = "update_user_id")
	private String updateUserId;

	

	    /**账号id*/
	public String getAccountId(){

		return accountId;
	}

	    /**授课专业授课专业*/
	public String getInstructionProfession(){

		return instructionProfession;
	}

	    /**手机号码手机号码*/
	public String getPhone(){

		return phone;
	}

	    /**研究方向研究方向*/
	public String getResearchDirection(){

		return researchDirection;
	}

	    /**邮箱邮箱*/
	public String getMail(){

		return mail;
	}

	    /**专业专业*/
	public String getProfession(){

		return profession;
	}

	    /**职务/身份职务/身份,0:xxx1:xx...*/
	public Integer getJob(){

		return job;
	}

	    /**年级年级*/
	public String getGrade(){

		return grade;
	}

	    /**班级班级*/
	public String getClassName(){

		return className;
	}

	    /**学号学号*/
	public String getSno(){

		return sno;
	}

	    /**微信号微信号*/
	public String getWxAccount(){

		return wxAccount;
	}

	    /**账号状态账号状态*/
	public Integer getAccountStatus(){

		return accountStatus;
	}

	    /**系统内部识别码系统内部识别码*/
	public String getIdentificationCode(){

		return identificationCode;
	}

	    /**备注备注*/
	public String getRemarks(){

		return remarks;
	}

	    /**是否未认证是否未认证:0否，1是*/
	public Integer getNotAccreditation(){

		return notAccreditation;
	}

	    /**二级绑定单位二级绑定单位*/
	public String getSecBinding(){

		return secBinding;
	}

	    /**创建时间创建时间*/
	public java.util.Date getCreateTime(){

		return createTime;
	}

	    /**修改时间*/
	public java.util.Date getUpdateTime(){

		return updateTime;
	}

	    /**删除标识0 未删除 1已删除*/
	public Integer getIsDel(){

		return isDel;
	}

	    /**新建人id*/
	public String getCreateUserId(){

		return createUserId;
	}

	    /**修改人id*/
	public String getUpdateUserId(){

		return updateUserId;
	}

	
	    /**账号id*/
	public void setAccountId (String accountId){

		this.accountId = accountId;
	}

	    /**授课专业授课专业*/
	public void setInstructionProfession (String instructionProfession){

		this.instructionProfession = instructionProfession;
	}

	    /**手机号码手机号码*/
	public void setPhone (String phone){

		this.phone = phone;
	}

	    /**研究方向研究方向*/
	public void setResearchDirection (String researchDirection){

		this.researchDirection = researchDirection;
	}

	    /**邮箱邮箱*/
	public void setMail (String mail){

		this.mail = mail;
	}

	    /**专业专业*/
	public void setProfession (String profession){

		this.profession = profession;
	}

	    /**职务/身份职务/身份,0:xxx1:xx...*/
	public void setJob (Integer job){

		this.job = job;
	}

	    /**年级年级*/
	public void setGrade (String grade){

		this.grade = grade;
	}

	    /**班级班级*/
	public void setClassName (String className){

		this.className = className;
	}

	    /**学号学号*/
	public void setSno (String sno){

		this.sno = sno;
	}

	    /**微信号微信号*/
	public void setWxAccount (String wxAccount){

		this.wxAccount = wxAccount;
	}

	    /**账号状态账号状态*/
	public void setAccountStatus (Integer accountStatus){

		this.accountStatus = accountStatus;
	}

	    /**系统内部识别码系统内部识别码*/
	public void setIdentificationCode (String identificationCode){

		this.identificationCode = identificationCode;
	}

	    /**备注备注*/
	public void setRemarks (String remarks){

		this.remarks = remarks;
	}

	    /**是否未认证是否未认证:0否，1是*/
	public void setNotAccreditation (Integer notAccreditation){

		this.notAccreditation = notAccreditation;
	}

	    /**二级绑定单位二级绑定单位*/
	public void setSecBinding (String secBinding){

		this.secBinding = secBinding;
	}

	    /**创建时间创建时间*/
	public void setCreateTime (java.util.Date createTime){

		this.createTime = createTime;
	}

	    /**修改时间*/
	public void setUpdateTime (java.util.Date updateTime){

		this.updateTime = updateTime;
	}

	    /**删除标识0 未删除 1已删除*/
	public void setIsDel (Integer isDel){

		this.isDel = isDel;
	}

	    /**新建人id*/
	public void setCreateUserId (String createUserId){

		this.createUserId = createUserId;
	}

	    /**修改人id*/
	public void setUpdateUserId (String updateUserId){

		this.updateUserId = updateUserId;
	}

	
}