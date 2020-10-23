package io.renren.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.demo.dao.SupEnterpriseDao;
import io.renren.modules.demo.dao.SupTaskPlanDao;
import io.renren.modules.demo.dao.SupTaskPlanEnterpriseDao;
import io.renren.modules.demo.dto.SupEnterpriseDTO;
import io.renren.modules.demo.dto.SupTaskPlanDTO;
import io.renren.modules.demo.dto.SupTaskPlanEnterpriseDTO;
import io.renren.modules.demo.entity.SupEnterpriseEntity;
import io.renren.modules.demo.entity.SupTaskPlanEnterpriseEntity;
import io.renren.modules.demo.entity.SupTaskPlanEntity;
import io.renren.modules.demo.service.SupTaskPlanEnterpriseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务计划企业信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Service
public class SupTaskPlanEnterpriseServiceImpl extends CrudServiceImpl<SupTaskPlanEnterpriseDao, SupTaskPlanEnterpriseEntity, SupTaskPlanEnterpriseDTO> implements SupTaskPlanEnterpriseService {

    @Autowired
    private SupTaskPlanDao supTaskPlanDao;
    @Autowired
    private SupTaskPlanEnterpriseDao supTaskPlanEnterpriseDao;
    @Autowired
    private SupEnterpriseDao supEnterpriseDao;


    @Override
    public QueryWrapper<SupTaskPlanEnterpriseEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SupTaskPlanEnterpriseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public PageData<Map<String, String>> getInfo(Map<String, String> params) {
        // 创建一个容器，用来放需要返回的数据
        List<Map<String, String>> dataList = new ArrayList<>();
        // 通过id查询任务计划表基本信息表，得到uuid
        SupTaskPlanEntity supTaskPlanEntity = supTaskPlanDao.selectById(params.get("id"));
        String uuid = supTaskPlanEntity.getUuid();
        // 通过uuid查询任务计划企业信息表，得到主键id、企业id、执法模板id
        List<SupTaskPlanEnterpriseDTO> supTaskPlanEnterpriseList = supTaskPlanEnterpriseDao.selectByUUID(uuid);
        for (SupTaskPlanEnterpriseDTO dto : supTaskPlanEnterpriseList) {
            //创建一个Map集合，用来放前端需要的数据
            Map<String, String> map = new HashMap<>();
            //通过eid查询企业信息表，得到企业名称、地址、联系人、联系方式
            Long eid = dto.getEid();
            SupEnterpriseEntity supEnterpriseEntity = supEnterpriseDao.selectById(eid);
            //将前端需要的数据放入Map集合
            map.put("id", dto.getId().toString());
            map.put("enterpriseName", supEnterpriseEntity.getEnterpriseName());
            map.put("address", supEnterpriseEntity.getProvince() + supEnterpriseEntity.getCity() + supEnterpriseEntity.getCountry());
            map.put("linkMan", supEnterpriseEntity.getLinkMan());
            map.put("linkWay", supEnterpriseEntity.getLinkWay());
            dataList.add(map);
        }
        return new PageData<>(dataList, supTaskPlanEnterpriseList.size());
    }
}