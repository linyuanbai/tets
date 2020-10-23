package io.renren.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.demo.dao.SupTaskPlanEnterpriseTimeDao;
import io.renren.modules.demo.dto.SupTaskPlanEnterpriseTimeDTO;
import io.renren.modules.demo.entity.SupTaskPlanEnterpriseTimeEntity;
import io.renren.modules.demo.service.SupTaskPlanEnterpriseTimeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 任务计划企业监察次数信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Service
public class SupTaskPlanEnterpriseTimeServiceImpl extends CrudServiceImpl<SupTaskPlanEnterpriseTimeDao, SupTaskPlanEnterpriseTimeEntity, SupTaskPlanEnterpriseTimeDTO> implements SupTaskPlanEnterpriseTimeService {

    @Override
    public QueryWrapper<SupTaskPlanEnterpriseTimeEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SupTaskPlanEnterpriseTimeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}