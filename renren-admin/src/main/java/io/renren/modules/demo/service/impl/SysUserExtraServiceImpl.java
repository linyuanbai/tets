package io.renren.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.modules.demo.dao.SysUserExtraDao;
import io.renren.modules.demo.dto.SysUserExtraDTO;
import io.renren.modules.demo.entity.SysUserExtraEntity;
import io.renren.modules.demo.service.SysUserExtraService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 人员附加信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-29
 */
@Service
public class SysUserExtraServiceImpl extends CrudServiceImpl<SysUserExtraDao, SysUserExtraEntity, SysUserExtraDTO> implements SysUserExtraService {

    @Autowired
    public SysUserExtraDao sysUserExtraDao;

    @Override
    public QueryWrapper<SysUserExtraEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SysUserExtraEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public SysUserExtraDTO selectByUserId(Long id) {
        SysUserExtraEntity sysUserExtraEntity = sysUserExtraDao.selectByUserId(id);
        return ConvertUtils.sourceToTarget(sysUserExtraEntity, SysUserExtraDTO.class);
    }
}