package io.renren.modules.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 企业信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Data
@ApiModel(value = "企业信息表")
public class SupEnterpriseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "企业名")
	private String enterpriseName;

	@ApiModelProperty(value = "所在省")
	private String province;

	@ApiModelProperty(value = "所在市")
	private String city;

	@ApiModelProperty(value = "所在县/区")
	private String country;

	@ApiModelProperty(value = "监管类别")
	private String superviseType;

	@ApiModelProperty(value = "联系人")
	private String linkMan;

	@ApiModelProperty(value = "联系方式")
	private String linkWay;

	@ApiModelProperty(value = "创建时间")
	private String createDate;

	@ApiModelProperty(value = "创建人")
	private String creator;

}