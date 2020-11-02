package io.renren.modules.demo.service;

import io.renren.common.service.CrudService;
import io.renren.modules.demo.dto.RankEquipmentDTO;
import io.renren.modules.demo.entity.RankEquipmentEntity;

/**
 * 移动执法装备管理信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-19
 */
public interface RankEquipmentService extends CrudService<RankEquipmentEntity, RankEquipmentDTO> {

    /**
     * 通过deptId查询数据
     * @param deptId
     * @return
     */
    RankEquipmentDTO selectByDeptId(Long deptId);

    void delete(Long[] ids);
}