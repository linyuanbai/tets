package io.renren.modules.demo.dto;

import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;


/**
 * 移动执法装备管理信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-19
 */
@Data
@ApiModel(value = "移动执法装备管理信息表")
public class RankEquipmentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	@Null(message="{id.null}", groups = AddGroup.class)
	@NotNull(message="{id.require}", groups = UpdateGroup.class)
	private Long id;

	@ApiModelProperty(value = "所属机构")
	@NotNull(message="{deptId.require}", groups = AddGroup.class)
	private Long deptId;

	@ApiModelProperty(value = "手持移动终端配置数")
	@NotNull(message="{handMobileNumber.require}", groups = AddGroup.class)
	private Integer handMobileNumber;

	@ApiModelProperty(value = "手持移动终端配置时间")
	private String handMobileDate;

	@ApiModelProperty(value = "笔记本电脑配置数")
	@NotNull(message="{laptopNumber.require}", groups = AddGroup.class)
	private Integer laptopNumber;

	@ApiModelProperty(value = "笔记本电脑配置时间")
	private String laptopDate;

	@ApiModelProperty(value = "便携式扫描打印配置数")
	@NotNull(message="{portablePrinterNumber.require}", groups = AddGroup.class)
	private Integer portablePrinterNumber;

	@ApiModelProperty(value = "便携式扫描打印机配置时间")
	private String portablePrinterDate;

	@ApiModelProperty(value = "便携式Wifi热点配置数")
	@NotNull(message="{portableWifiNumber.require}", groups = AddGroup.class)
	private Integer portableWifiNumber;

	@ApiModelProperty(value = "便携式Wifi热点配置时间")
	private String portableWifiDate;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "修改时间")
	private Date updateDate;

	@ApiModelProperty(value = "创建人")
	private Long creator;

	@ApiModelProperty(value = "修改人")
	private Long updater;

	@ApiModelProperty(value = "是否删除  1：已删除<br />0：未删除")
	@Range(min=0, max=1, message = "{isDeleted.range}", groups = DefaultGroup.class)
	private Integer isDeleted;

	@ApiModelProperty(value = "部门名称")
	private String deptName;

	@ApiModelProperty(value = "创建人姓名")
	private String creatorName;

	@ApiModelProperty(value = "修改人姓名")
	private String updaterName;

	@ApiModelProperty(value = "上级部门名称")
	private String preDeptName;
}