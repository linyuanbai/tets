package io.renren.modules.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 人员附加信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-29
 */
@Data
@ApiModel(value = "人员附加信息表")
public class SysUserExtraDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "用户ID")
	private Long userId;

	@ApiModelProperty(value = "办公室")
	private String office;

	@ApiModelProperty(value = "办公电话")
	private String workPhone;

	@ApiModelProperty(value = "监察执法证号")
	private String superviseCardNo;

	@ApiModelProperty(value = "行政执法证号")
	private String administrationCardNo;

	@ApiModelProperty(value = "人员类别  1：有/0：无")
	private String userType;

	@ApiModelProperty(value = "级别")
	private String workGrade;

	@ApiModelProperty(value = "岗位情况")
	private String officeInfo;

	@ApiModelProperty(value = "民族")
	private String nation;

	@ApiModelProperty(value = "来源")
	private String source;

	@ApiModelProperty(value = "籍贯")
	private String nativePlace;

	@ApiModelProperty(value = "出生日期")
	private String birthday;

	@ApiModelProperty(value = "开始工作时间")
	private String startWork;

	@ApiModelProperty(value = "结束工作时间")
	private String endWork;

	@ApiModelProperty(value = "最高学历")
	private String education;

	@ApiModelProperty(value = "考核年份")
	private String checkYear;

	@ApiModelProperty(value = "考核情况")
	private String checkInfo;

	@ApiModelProperty(value = "身份证号码")
	private String cardNo;

	@ApiModelProperty(value = "创建时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@ApiModelProperty(value = "创建人")
	private Long creator;

	@ApiModelProperty(value = "备注")
	private String remarks;

	@ApiModelProperty(value = "是否删除  1：已删除<br />0：未删除")
	private Integer isDeleted;

	// 关联属性
	@ApiModelProperty(value = "创建人姓名")
	private Long creatorName;

}