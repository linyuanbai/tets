package io.renren.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.demo.dao.GoodsDao;
import io.renren.modules.demo.dto.GoodsDTO;
import io.renren.modules.demo.entity.GoodsEntity;
import io.renren.modules.demo.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品管理
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-05
 */
@Service
public class GoodsServiceImpl extends CrudServiceImpl<GoodsDao, GoodsEntity, GoodsDTO> implements GoodsService {

    @Override
    public QueryWrapper<GoodsEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<GoodsEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}