package io.renren.modules.demo.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.demo.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品管理
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-05
 */
@Mapper
public interface GoodsDao extends BaseDao<GoodsEntity> {
	
}