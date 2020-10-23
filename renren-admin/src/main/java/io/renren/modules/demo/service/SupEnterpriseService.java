package io.renren.modules.demo.service;

import io.renren.common.page.PageData;
import io.renren.common.service.CrudService;
import io.renren.modules.demo.dto.SupEnterpriseDTO;
import io.renren.modules.demo.entity.SupEnterpriseEntity;

import java.util.Map;

/**
 * 企业信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
public interface SupEnterpriseService extends CrudService<SupEnterpriseEntity, SupEnterpriseDTO> {

    PageData<SupEnterpriseDTO> getInfo(Map<String, String> params);
}