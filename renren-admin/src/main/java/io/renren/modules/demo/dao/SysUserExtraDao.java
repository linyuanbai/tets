package io.renren.modules.demo.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.demo.entity.SysUserExtraEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人员附加信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-29
 */
@Mapper
public interface SysUserExtraDao extends BaseDao<SysUserExtraEntity> {

    SysUserExtraEntity selectByUserId(Long id);

    void deleteByUserIds(Long[] userIds);
}