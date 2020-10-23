package io.renren.modules.demo.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.demo.entity.SupTaskPlanEnterpriseTimeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务计划企业监察次数信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Mapper
public interface SupTaskPlanEnterpriseTimeDao extends BaseDao<SupTaskPlanEnterpriseTimeEntity> {
	
}