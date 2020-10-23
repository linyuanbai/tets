package io.renren.modules.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 任务计划表基本信息
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sup_task_plan")
public class SupTaskPlanEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 唯一标示符
     */
	private String uuid;
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
     * 任务名称
     */
	private String taskName;
    /**
     * 任务类型    0:重要且紧急  1:重要但不紧急  2:不重要但紧急  3:不重要且不紧急
     */
	private String taskType;
    /**
     * 任务开始时间
     */
	private Date startTime;
    /**
     * 结束时间
     */
	private Date endTime;
    /**
     * 人员数量
     */
	private Integer personNum;
    /**
     * 制定人
     */
	private Long maker;
}