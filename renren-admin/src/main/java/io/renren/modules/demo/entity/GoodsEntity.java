package io.renren.modules.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品管理
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("tb_goods")
public class GoodsEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 商品名
     */
	private String name;
    /**
     * 介绍
     */
	private String intro;
    /**
     * 价格
     */
	private Integer price;
    /**
     * 数量
     */
	private Integer num;
}