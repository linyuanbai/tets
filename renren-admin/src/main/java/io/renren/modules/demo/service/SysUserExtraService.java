package io.renren.modules.demo.service;

import io.renren.common.service.CrudService;
import io.renren.modules.demo.dto.SysUserExtraDTO;
import io.renren.modules.demo.entity.SysUserExtraEntity;

/**
 * 人员附加信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-29
 */
public interface SysUserExtraService extends CrudService<SysUserExtraEntity, SysUserExtraDTO> {

    SysUserExtraDTO selectByUserId(Long id);
}