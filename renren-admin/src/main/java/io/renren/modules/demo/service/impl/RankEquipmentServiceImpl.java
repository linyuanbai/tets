package io.renren.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.modules.demo.dao.RankEquipmentDao;
import io.renren.modules.demo.dto.RankEquipmentDTO;
import io.renren.modules.demo.entity.RankEquipmentEntity;
import io.renren.modules.demo.service.RankEquipmentService;
import io.renren.modules.sys.service.SysDeptService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 移动执法装备管理信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-19
 */
@Service
public class RankEquipmentServiceImpl extends CrudServiceImpl<RankEquipmentDao, RankEquipmentEntity, RankEquipmentDTO> implements RankEquipmentService {

    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public QueryWrapper<RankEquipmentEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<RankEquipmentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public PageData<RankEquipmentDTO> page(Map<String, Object> params) {
        Integer limit = new Integer(params.get("limit").toString());
        Integer page = new Integer(params.get("page").toString());
        params.put("offset", (page - 1) * limit);
        params.put("limit", limit);
        //获取符合条件的总记录数
        Long total = baseDao.count(params);
        // 查询所有符合条件的记录(分页)
        List<RankEquipmentEntity> list = baseDao.getList(params);
        // 将entity对象转化为dto
        List<RankEquipmentDTO> dtoList = ConvertUtils.sourceToTarget(list, RankEquipmentDTO.class);
        //遍历所有的记录
        for (RankEquipmentDTO rankEquipmentDTO : dtoList) {
            // RankEquipmentDTO(id=1318164070838407169, deptId=1067246875800000065,
            // handMobileNumber=8, handMobileDate=2020-10-21, laptopNumber=8,
            // laptopDate=2020-10-21, portablePrinterNumber=8, portablePrinterDate=2020-10-21,
            // portableWifiNumber=8, portableWifiDate=2020-10-21, createDate=Mon Oct 19 20:16:19 CST 2020,
            // updateDate=Mon Oct 19 20:16:19 CST 2020, creator=1067246875800000001,
            // updater=1067246875800000001, isDeleted=null, deptName=null, creatorName=null, updaterName=null)

            // 获取部门名字
            String deptName = sysDeptService.selectById(rankEquipmentDTO.getDeptId()).getName();
            // 获取创建者名字
            String creatorName = sysUserService.selectById(rankEquipmentDTO.getCreator()).getRealName();
            // 获取更新者名字
            String updaterName = sysUserService.selectById(rankEquipmentDTO.getUpdater()).getRealName();
            //将查询数据设置进dto
            rankEquipmentDTO.setDeptName(deptName);
            rankEquipmentDTO.setCreatorName(creatorName);
            rankEquipmentDTO.setUpdaterName(updaterName);
        }
        return getPageData(dtoList, total, RankEquipmentDTO.class);
    }

}