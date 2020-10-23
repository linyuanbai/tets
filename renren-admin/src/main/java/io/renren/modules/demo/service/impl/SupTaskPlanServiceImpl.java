package io.renren.modules.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.demo.dao.SupTaskPlanDao;
import io.renren.modules.demo.dto.SupTaskPlanDTO;
import io.renren.modules.demo.entity.SupTaskPlanEntity;
import io.renren.modules.demo.service.SupTaskPlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 任务计划表基本信息
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@Service
public class SupTaskPlanServiceImpl extends CrudServiceImpl<SupTaskPlanDao, SupTaskPlanEntity, SupTaskPlanDTO> implements SupTaskPlanService {

    @Autowired
    private SupTaskPlanDao supTaskPlanDao;

    @Override
    public QueryWrapper<SupTaskPlanEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SupTaskPlanEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<SupTaskPlanDTO> getInfo(Map<String, String> params) {
        // {order=, orderField=, page=1, limit=5, taskName=, startTime=, _t=1602592109533}
        // 任务名
        String taskName = params.get("taskName");
        // 开始时间
        String startTime = params.get("startTime").replace(".", "/");
        // 排序方式
        String order = params.get("order");
        // 排序属性
        String orderField = params.get("orderField");
        // 行数
        int limit = Integer.parseInt(params.get("limit"));
        // 行偏移量
        int offset = (Integer.parseInt(params.get("page")) - 1) * limit;

        //数据列表
        List<SupTaskPlanDTO> dataList = supTaskPlanDao.getInfo(taskName, startTime, order, orderField, limit, offset);
        //查询总记录数
        long total = supTaskPlanDao.count(taskName, startTime);
        return new PageData<>(dataList, total);
    }

    @Override
    public void saveByParams(Map<String, String> params) {
        // {taskName=是, taskType=月度计划,
        // enterpriseName=, province=, startTime=2020-10-09, endTime=2020-11-10, id=["1","2"]}
        // 任务名
        String taskName = params.get("taskName");
        // 任务类型
        String taskType = params.get("taskType");
        // 开始时间
        String startTime = params.get("startTime");
        // 结束时间
        String endTime = params.get("endTime");
        // 获取当前的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());

        //
        JSONArray array = JSONArray.parseArray(params.get("id"));
        for (int i = 0 ; i < array.size() ; i ++) {
            System.out.println(array.get(i));
        }
    }
}