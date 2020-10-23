package io.renren.modules.demo.service;

import io.renren.common.page.PageData;
import io.renren.common.service.CrudService;
import io.renren.modules.demo.dto.SupTaskPlanDTO;
import io.renren.modules.demo.entity.SupTaskPlanEntity;

import java.util.List;
import java.util.Map;

/**
 * 任务计划表基本信息
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
public interface SupTaskPlanService extends CrudService<SupTaskPlanEntity, SupTaskPlanDTO> {

    /**
     * 按条件查询所有的任务计划信息
     * @param params
     * @return
     */
    PageData<SupTaskPlanDTO> getInfo(Map<String, String> params);

    /**
     * 依据前端传过来的数据进行数据库插入操作
     * @param params
     */
    void saveByParams(Map<String, String> params);
}