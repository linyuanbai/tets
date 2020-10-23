package io.renren.modules.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 企业信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sup_enterprise")
public class SupEnterpriseEntity extends BaseEntity {
	private static final long serialVersionUID = 11L;

    /**
     * 企业名
     */
	private String enterpriseName;
    /**
     * 所在省
     */
	private String province;
    /**
     * 所在市
     */
	private String city;
    /**
     * 所在县/区
     */
	private String country;
    /**
     * 监管类别
     */
	private String superviseType;
    /**
     * 联系人
     */
	private String linkMan;
    /**
     * 联系方式
     */
	private String linkWay;
}