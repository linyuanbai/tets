package io.renren.modules.demo.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.demo.dto.SupTaskPlanDTO;
import io.renren.modules.demo.entity.SupTaskPlanEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务计划表基本信息
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Mapper
@Repository
public interface SupTaskPlanDao extends BaseDao<SupTaskPlanEntity> {

    List<SupTaskPlanDTO> getInfo(@Param("taskName") String taskName, @Param("startTime") String startTime, @Param("order") String order, @Param("orderField") String orderField, @Param("limit") int limit, @Param("offset") int offset);

    long count(@Param("taskName") String taskName, @Param("startTime") String startTime);
}