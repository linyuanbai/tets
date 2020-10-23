package io.renren.modules.demo.service;

import io.renren.common.page.PageData;
import io.renren.common.service.CrudService;
import io.renren.modules.demo.dto.SupTaskPlanDTO;
import io.renren.modules.demo.dto.SupTaskPlanEnterpriseDTO;
import io.renren.modules.demo.entity.SupTaskPlanEnterpriseEntity;

import java.util.Map;

/**
 * 任务计划企业信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
public interface SupTaskPlanEnterpriseService extends CrudService<SupTaskPlanEnterpriseEntity, SupTaskPlanEnterpriseDTO> {

    PageData<Map<String, String>> getInfo(Map<String, String> params);
}