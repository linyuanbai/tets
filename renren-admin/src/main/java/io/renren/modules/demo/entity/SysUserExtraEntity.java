package io.renren.modules.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 人员附加信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_user_extra")
public class SysUserExtraEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	private Long userId;
    /**
     * 手机号码
     */
	private String mobilePhone;
    /**
     * 办公室
     */
	private String office;
    /**
     * 办公电话
     */
	private String workPhone;
    /**
     * 电子邮箱
     */
	private String email;
    /**
     * 有无监察执法证号  1：有/0：无
     */
	private String hasSuperviseCard;
    /**
     * 监察执法证号
     */
	private String superviseCardNo;
    /**
     * 有无行政执法证号  1：有/0：无
     */
	private String hasAdministrationCard;
    /**
     * 行政执法证号
     */
	private String administrationCardNo;
    /**
     * 人员类别  1：有/0：无
     */
	private String userType;
    /**
     * 级别
     */
	private String workGrade;
    /**
     * 岗位情况
     */
	private String officeInfo;
    /**
     * 性别  0：男   1：女    2：保密
     */
	private String gender;
    /**
     * 民族
     */
	private String nation;
    /**
     * 来源
     */
	private String source;
    /**
     * 籍贯
     */
	private String nativePlace;
    /**
     * 出生日期
     */
	private String birthday;
    /**
     * 开始工作时间
     */
	private String startWork;
    /**
     * 结束工作时间
     */
	private String endWork;
    /**
     * 最高学历
     */
	private String education;
    /**
     * 考核年份
     */
	private String checkYear;
    /**
     * 考核情况
     */
	private String checkInfo;
    /**
     * 身份证号码
     */
	private String cardNo;
    /**
     * 修改时间
     */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
    /**
     * 修改人
     */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;
    /**
     * 是否删除  1：已删除<br />0：未删除
     */
	private Integer isDeleted;
}