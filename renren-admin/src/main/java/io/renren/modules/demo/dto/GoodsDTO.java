package io.renren.modules.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 商品管理
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-05
 */
@Data
@ApiModel(value = "商品管理")
public class GoodsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "商品ID")
	private Long id;

	@ApiModelProperty(value = "商品名")
	private String name;

	@ApiModelProperty(value = "介绍")
	private String intro;

	@ApiModelProperty(value = "价格")
	private Integer price;

	@ApiModelProperty(value = "数量")
	private Integer num;

	@ApiModelProperty(value = "创建者")
	private Long creator;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;


}