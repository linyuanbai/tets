package io.renren.modules.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 移动执法装备管理信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-19
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("rank_equipment")
public class RankEquipmentEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 所属机构
     */
	private Long deptId;
    /**
     * 手持移动终端配置数
     */
	private Integer handMobileNumber;
    /**
     * 手持移动终端配置时间
     */
	private String handMobileDate;
    /**
     * 笔记本电脑配置数
     */
	private Integer laptopNumber;
    /**
     * 笔记本电脑配置时间
     */
	private String laptopDate;
    /**
     * 便携式扫描打印配置数
     */
	private Integer portablePrinterNumber;
    /**
     * 便携式扫描打印机配置时间
     */
	private String portablePrinterDate;
    /**
     * 便携式Wifi热点配置数
     */
	private Integer portableWifiNumber;
    /**
     * 便携式Wifi热点配置时间
     */
	private String portableWifiDate;
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