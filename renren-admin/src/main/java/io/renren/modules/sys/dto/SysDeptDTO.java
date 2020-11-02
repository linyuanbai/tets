/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.renren.common.utils.TreeNode;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 部门管理
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@ApiModel(value = "部门管理")
@ToString
public class SysDeptDTO extends TreeNode implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	@Null(message="{id.null}", groups = AddGroup.class)
	@NotNull(message="{id.require}", groups = UpdateGroup.class)
	private Long id;

	@ApiModelProperty(value = "上级ID")
	@NotNull(message="{sysdept.pid.require}", groups = DefaultGroup.class)
	private Long pid;

	@ApiModelProperty(value = "部门名称")
	@NotBlank(message="{sysdept.name.require}", groups = DefaultGroup.class)
	private String name;

	@ApiModelProperty(value = "排序")
	@Min(value = 0, message = "{sort.number}", groups = DefaultGroup.class)
	private Integer sort;

	@ApiModelProperty(value = "所属区域_省")
	private Long regionProvince;

	@ApiModelProperty(value = "所属区域_地市")
	private Long regionCity;

	@ApiModelProperty(value = "所属区域_区县")
	private Long regionCounty;

	@ApiModelProperty(value = "所属区域_街道")
	private Long regionStreet;

	@ApiModelProperty(value = "机构编码")
	private String code;

	@ApiModelProperty(value = "机构类型")
	private String type;

	@ApiModelProperty(value = "机构等级")
	private String grade;

	@ApiModelProperty(value = "机构性质")
	private String nature;

	@ApiModelProperty(value = "是否临时机构")
	private String isTemporary;

	@ApiModelProperty(value = "有效期起始")
	private Date validDayStart;

	@ApiModelProperty(value = "有效期截止")
	private Date validDayEnd;

	@ApiModelProperty(value = "机构状态")
	private String status;

	@ApiModelProperty(value = "联系地址")
	private String address;

	@ApiModelProperty(value = "邮政编码")
	private String zipCode;

	@ApiModelProperty(value = "电话")
	private String phone;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "是否启用")
	private String isUseable;

	@ApiModelProperty(value = "负责人")
	private Long master;

	@ApiModelProperty(value = "副负责人")
	private Long deputyPerson;

	@ApiModelProperty(value = "备注信息")
	private String remarks;

	@ApiModelProperty(value = "是否删除")
	private Integer isDeleted;

	@ApiModelProperty(value = "创建时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@ApiModelProperty(value = "上级部门名称")
	private String parentName;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getPid() {
		return pid;
	}

	@Override
	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getRegionProvince() {
		return regionProvince;
	}

	public void setRegionProvince(Long regionProvince) {
		this.regionProvince = regionProvince;
	}

	public Long getRegionCity() {
		return regionCity;
	}

	public void setRegionCity(Long regionCity) {
		this.regionCity = regionCity;
	}

	public Long getRegionCounty() {
		return regionCounty;
	}

	public void setRegionCounty(Long regionCounty) {
		this.regionCounty = regionCounty;
	}

	public Long getRegionStreet() {
		return regionStreet;
	}

	public void setRegionStreet(Long regionStreet) {
		this.regionStreet = regionStreet;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getIsTemporary() {
		return isTemporary;
	}

	public void setIsTemporary(String isTemporary) {
		this.isTemporary = isTemporary;
	}

	public Date getValidDayStart() {
		return validDayStart;
	}

	public void setValidDayStart(Date validDayStart) {
		this.validDayStart = validDayStart;
	}

	public Date getValidDayEnd() {
		return validDayEnd;
	}

	public void setValidDayEnd(Date validDayEnd) {
		this.validDayEnd = validDayEnd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsUseable() {
		return isUseable;
	}

	public void setIsUseable(String isUseable) {
		this.isUseable = isUseable;
	}

	public Long getMaster() {
		return master;
	}

	public void setMaster(Long master) {
		this.master = master;
	}

	public Long getDeputyPerson() {
		return deputyPerson;
	}

	public void setDeputyPerson(Long deputyPerson) {
		this.deputyPerson = deputyPerson;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}