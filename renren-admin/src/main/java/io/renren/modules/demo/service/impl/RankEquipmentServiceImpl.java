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
    @Autowired
    private RankEquipmentDao rankEquipmentDao;

    @Override
    public QueryWrapper<RankEquipmentEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<RankEquipmentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    // 设计一个方法，遍历一个list集合(dto)里的所有元素，为其关联一些信息(部门名字、创建人名字、修改人名字、上级部门名字)
    private void setExtraInfoToList (List<RankEquipmentDTO> dtoList) {
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
            // 获取修改人名字
            String updaterName = sysUserService.selectById(rankEquipmentDTO.getUpdater()).getRealName();
            // 获取上级部门名
            String preDeptName = sysDeptService.get(rankEquipmentDTO.getDeptId()).getParentName();
            //将查询数据设置进dto
            rankEquipmentDTO.setDeptName(deptName);
            rankEquipmentDTO.setCreatorName(creatorName);
            rankEquipmentDTO.setUpdaterName(updaterName);
            rankEquipmentDTO.setPreDeptName(preDeptName);
        }
    }

    @Override
    public PageData<RankEquipmentDTO> page(Map<String, Object> params) {
        Integer limit = new Integer(params.get("limit").toString());
        Integer page = new Integer(params.get("page").toString());
        params.put("offset", (page - 1) * limit);
        params.put("limit", limit);
        // 如果参数中有deptId，则获取所有子部门ID列表
        String deptId = params.get("deptId").toString();
        if (deptId != null && !deptId.isEmpty()) {
            List<Long> deptIdList = sysDeptService.getSubDeptIdList(new Long(deptId));
            params.put("deptIdList", deptIdList);
        }
        // 获取符合条件的总记录数
        Long total = baseDao.count(params);
        // 查询所有符合条件的记录(分页)
        List<RankEquipmentEntity> list = baseDao.getList(params);
        // 将entity对象转化为dto
        List<RankEquipmentDTO> dtoList = ConvertUtils.sourceToTarget(list, RankEquipmentDTO.class);
        // 关联外键信息
        setExtraInfoToList(dtoList);
        return getPageData(dtoList, total, RankEquipmentDTO.class);
    }

    @Override
    public void save(RankEquipmentDTO dto) {
        // 获取部门id
        Long deptId = dto.getDeptId();
        // 查询数据库是否已经存在此id部门的移动执法装备记录
        RankEquipmentEntity rankEquipmentEntity = rankEquipmentDao.selectByDeptId(deptId);
        // 如果存在就修改这条记录的信息，反之则向表中添加一条记录
        if (rankEquipmentEntity != null) {
            dto.setId(rankEquipmentEntity.getId());
            update(dto);
        } else {
            RankEquipmentEntity entity = ConvertUtils.sourceToTarget(dto, RankEquipmentEntity.class);
            insert(entity);
        }
    }
}