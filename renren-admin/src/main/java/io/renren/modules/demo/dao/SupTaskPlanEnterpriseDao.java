package io.renren.modules.demo.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.demo.dto.SupTaskPlanEnterpriseDTO;
import io.renren.modules.demo.entity.SupTaskPlanEnterpriseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务计划企业信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Mapper
@Repository
public interface SupTaskPlanEnterpriseDao extends BaseDao<SupTaskPlanEnterpriseEntity> {

    List<SupTaskPlanEnterpriseDTO> selectByUUID(String uuid);
}