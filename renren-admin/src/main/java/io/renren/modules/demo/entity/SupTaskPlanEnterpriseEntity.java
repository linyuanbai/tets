package io.renren.modules.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 任务计划企业信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sup_task_plan_enterprise")
public class SupTaskPlanEnterpriseEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 修改时间
     */
	private Date updateDate;
    /**
     * 修改人
     */
	private Long updater;
    /**
     * 是否删除    1:删除  0：未删除
     */
	private Integer isDeleted;
    /**
     * 企业id
     */
	private Long eid;
    /**
     * 任务uuid
     */
	private String taskUuid;
    /**
     * 执法模板id
     */
	private Long lawTemplateId;
}