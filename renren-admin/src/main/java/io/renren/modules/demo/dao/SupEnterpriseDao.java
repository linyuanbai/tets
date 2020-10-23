package io.renren.modules.demo.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.demo.dto.SupEnterpriseDTO;
import io.renren.modules.demo.entity.SupEnterpriseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 企业信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Mapper
@Repository
public interface SupEnterpriseDao extends BaseDao<SupEnterpriseEntity> {

    List<SupEnterpriseDTO> getInfo(@Param("enterpriseName") String enterpriseName, @Param("province") String province, @Param("order") String order, @Param("orderField") String orderField, @Param("limit") int limit, @Param("offset") int offset);

    long count(String enterpriseName, String province);
}