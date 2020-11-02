package io.renren.modules.demo.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.demo.entity.RankEquipmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 移动执法装备管理信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-19
 */
@Mapper
@Repository
public interface RankEquipmentDao extends BaseDao<RankEquipmentEntity> {

    List<RankEquipmentEntity> getList(Map<String, Object> params);

    Long count(Map<String, Object> params);

    RankEquipmentEntity selectByDeptId(Long deptId);

    void deleteBatchIds(Long[] ids);
}