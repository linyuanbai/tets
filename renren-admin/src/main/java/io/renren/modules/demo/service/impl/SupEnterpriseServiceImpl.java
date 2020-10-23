package io.renren.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.demo.dao.SupEnterpriseDao;
import io.renren.modules.demo.dto.SupEnterpriseDTO;
import io.renren.modules.demo.entity.SupEnterpriseEntity;
import io.renren.modules.demo.service.SupEnterpriseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 企业信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Service
public class SupEnterpriseServiceImpl extends CrudServiceImpl<SupEnterpriseDao, SupEnterpriseEntity, SupEnterpriseDTO> implements SupEnterpriseService {

    @Autowired
    private SupEnterpriseDao supEnterpriseDao;

    @Override
    public QueryWrapper<SupEnterpriseEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SupEnterpriseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public PageData<SupEnterpriseDTO> getInfo(Map<String, String> params) {
        // 任务名
        String enterpriseName = params.get("enterpriseName");
        // 开始时间
        String province = params.get("province");
        // 排序方式
        String order = params.get("order");
        // 排序属性
        String orderField = params.get("orderField");
        // 行数
        int limit = Integer.parseInt(params.get("limit"));
        // 行偏移量
        int offset = (Integer.parseInt(params.get("page")) - 1) * limit;

        //数据列表
        List<SupEnterpriseDTO> dataList = supEnterpriseDao.getInfo(enterpriseName, province, order, orderField, limit, offset);
        //查询总记录数
        long total = supEnterpriseDao.count(enterpriseName, province);
        return new PageData<>(dataList, total);
    }
}