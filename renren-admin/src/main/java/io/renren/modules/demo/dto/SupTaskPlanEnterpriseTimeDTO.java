package io.renren.modules.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 任务计划企业监察次数信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Data
@ApiModel(value = "任务计划企业监察次数信息表")
public class SupTaskPlanEnterpriseTimeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "创建人")
	private Long creator;

	@ApiModelProperty(value = "修改时间")
	private Date updateDate;

	@ApiModelProperty(value = "修改人")
	private Long updater;

	@ApiModelProperty(value = "是否删除    1:删除  0：未删除")
	private Integer isDeleted;

	@ApiModelProperty(value = "企业id")
	private Long eid;

	@ApiModelProperty(value = "任务计划uuid")
	private String taskUuid;

	@ApiModelProperty(value = "执法模板id")
	private Long lawTemplateId;

	@ApiModelProperty(value = "监察次数")
	private Integer times;

	@ApiModelProperty(value = "监察人")
	private String superviser;

	@ApiModelProperty(value = "开始时间")
	private Date startTime;

	@ApiModelProperty(value = "结束时间")
	private Date endTime;


}