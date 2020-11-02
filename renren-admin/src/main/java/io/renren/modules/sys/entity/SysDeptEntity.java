/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 部门管理
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_dept")
public class SysDeptEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 上级ID
	 */
	private Long pid;
	/**
	 * 所有上级ID，用逗号分开
	 */
	private String pids;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 所属区域_省
	 */
	private Long regionProvince;
	/**
	 * 所属区域_地市
	 */
	private Long regionCity;
	/**
	 * 所属区域_区县
	 */
	private Long regionCounty;
	/**
	 * 所属区域_街道
	 */
	private Long regionStreet;
	/**
	 * 机构编码
	 */
	private String code;
	/**
	 * 机构类型
	 */
	private String type;
	/**
	 * 机构等级
	 */
	private String grade;
	/**
	 * 机构性质
	 */
	private String nature;
	/**
	 * 是否临时机构
	 */
	private String isTemporary;
	/**
	 * 有效期起始
	 */
	private Date validDayStart;
	/**
	 * 有效期截止
	 */
	private Date validDayEnd;
	/**
	 * 机构状态
	 */
	private String status;
	/**
	 * 联系地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private String zipCode;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 是否启用
	 */
	private String isUseable;
	/**
	 * 负责人
	 */
	private Long master;
	/**
	 * 副负责人
	 */
	private Long deputyPerson;
	/**
	 * 备注信息
	 */
	private String remarks;
	/**
	 * 是否删除
	 */
	private Integer isDeleted;
	/**
	 * 更新者
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
	/**
	 * 上级部门名称
	 */
	@TableField(exist = false)
	private String parentName;
}