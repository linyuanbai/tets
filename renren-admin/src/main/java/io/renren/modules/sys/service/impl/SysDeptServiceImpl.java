/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.service.impl;

import io.renren.common.constant.Constant;
import io.renren.common.exception.ErrorCode;
import io.renren.common.exception.RenException;
import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.common.utils.TreeUtils;
import io.renren.modules.demo.dto.RankEquipmentDTO;
import io.renren.modules.demo.service.RankEquipmentService;
import io.renren.modules.security.user.SecurityUser;
import io.renren.modules.security.user.UserDetail;
import io.renren.modules.sys.dao.SysDeptDao;
import io.renren.modules.sys.dto.SysDeptDTO;
import io.renren.modules.sys.entity.SysDeptEntity;
import io.renren.modules.sys.enums.SuperAdminEnum;
import io.renren.modules.sys.service.SysDeptService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysDeptDao sysDeptDao;
	@Autowired
	private RankEquipmentService rankEquipmentService;

	@Override
	public List<SysDeptDTO> list(Map<String, Object> params) {
		//普通管理员，只能查询所属部门及子部门的数据
		UserDetail user = SecurityUser.getUser();
		if(user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
			params.put("deptIdList", getSubDeptIdList(user.getDeptId()));
		}

		//查询部门列表
		List<SysDeptEntity> entityList = baseDao.getList(params);

		List<SysDeptDTO> dtoList = ConvertUtils.sourceToTarget(entityList, SysDeptDTO.class);

		// 查询负责人姓名，并将其设置进dto
		for (SysDeptDTO sysDeptDTO : dtoList) {
			Long master = sysDeptDTO.getMaster();
			if (master != null) {
				sysDeptDTO.setMasterName(sysUserService.get(master).getRealName());
			}
		}

		return TreeUtils.build(dtoList);
	}

	@Override
	public SysDeptDTO get(Long id) {
		//超级管理员，部门ID为null
		if(id == null){
			return null;
		}

		SysDeptEntity entity = baseDao.getById(id);

		return ConvertUtils.sourceToTarget(entity, SysDeptDTO.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysDeptDTO dto) {
		// {id=null, pid=1067246875800000064, name='ggrea', sort=2, createDate=null,
		// parentName='郑州市环境监察支队', address='eawgaghrah', phone='15345556868',
		// email='418964@qq.com', master=null, leaf=0, masterName='null', status='1'}
		// 查询上级部门的机构等级
		SysDeptEntity preDeptEntity = sysDeptDao.getById(dto.getPid());
		if (preDeptEntity != null) {
			// 上级部门的机构等级加1并设置进dto
			Integer grade = new Integer(preDeptEntity.getGrade()) + 1;
			dto.setGrade(grade.toString());

			// 上级菜单是叶子节点
			if (preDeptEntity.getLeaf() == 1) {
				// 设置dto的leaf
				dto.setLeaf(1);
				// 更改上级菜单的leaf
				preDeptEntity.setLeaf(0);
				update(ConvertUtils.sourceToTarget(preDeptEntity, SysDeptDTO.class));
			} else {
				// 设置dto的leaf
				dto.setLeaf(1);
			}
		}
		// {id=null, pid=1321360974862692353, name='589', sort=3, createDate=null,
		// parentName='宣传部', address='36565', phone='15656565656', email='5646468416@qq.com',
		// master=null, leaf=1, masterName='null', status='1', grade='3'}
		//System.out.println(dto);
		// (pid=1067246875800000064, pids=1067246875800000066,1067246875800000064, name=宣传部,
		// sort=0, updater=1067246875800000001, updateDate=Thu Oct 29 16:01:05 CST 2020,
		// parentName=郑州市环境监察支队, address=郑州市惠济区, phone=17845698723,
		// email=6594654946@qq.com, master=1316643373901168642, leaf=0, status=1, grade=2)
		//System.out.println(preDeptEntity);

		SysDeptEntity entity = ConvertUtils.sourceToTarget(dto, SysDeptEntity.class);

		entity.setPids(getPidList(entity.getPid()));
		insert(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysDeptDTO dto) {
		// {id=1067246875800000066, pid=1067246875800000063, name='河南省环境监察总队',
		// sort=0, createDate=null, parentName='洛阳市环境监察支队', address='郑州市二七区',
		// phone='19865865485', email='5348435484@qq.com', master=1316643373901168642, leaf=0, masterName='null', status='1'}
		SysDeptEntity entity = ConvertUtils.sourceToTarget(dto, SysDeptEntity.class);

		//上级部门不能为自身
		if(entity.getId().equals(entity.getPid())){
			throw new RenException(ErrorCode.SUPERIOR_DEPT_ERROR);
		}

		//上级部门不能为下级部门
		List<Long> subDeptList = getSubDeptIdList(entity.getId());
		if(subDeptList.contains(entity.getPid())){
			throw new RenException(ErrorCode.SUPERIOR_DEPT_ERROR);
		}

		entity.setPids(getPidList(entity.getPid()));
		updateById(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		//判断是否有子部门
		List<Long> subList = getSubDeptIdList(id);
		if(subList.size() > 1){
			throw new RenException(ErrorCode.DEPT_SUB_DELETE_ERROR);
		}

		//判断部门下面是否有用户
		int count = sysUserService.getCountByDeptId(id);
		if(count > 0){
			throw new RenException(ErrorCode.DEPT_USER_DELETE_ERROR);
		}

		// 找移动执法装备记录，如果有就删除
		RankEquipmentDTO rankEquipmentDTO = rankEquipmentService.selectByDeptId(id);
		if (rankEquipmentDTO != null) {
			rankEquipmentService.deleteById(rankEquipmentDTO.getId());
		}

		// 判断父节点是否有其他叶子节点
		Long pid = get(id).getPid();
		if (pid != null) {
			List<Long> deptIdList = getSubDeptIdList(pid);
			if (deptIdList.size() == 2) { // 没有其他叶子节点
				SysDeptDTO sysDeptDTO = get(pid);
				sysDeptDTO.setLeaf(1);
				update(sysDeptDTO);
			}
		}

		//删除
		baseDao.deleteById(id);
	}

	@Override
	public List<Long> getSubDeptIdList(Long id) {
		List<Long> deptIdList = baseDao.getSubDeptIdList("%" + id + "%");
		deptIdList.add(id);

		return deptIdList;
	}

	@Override
	public List<SysDeptDTO> getListByLevel(int grade, Long pid) {
		List<SysDeptEntity> deptEntityList = sysDeptDao.getListByLevel(grade, pid);

		return ConvertUtils.sourceToTarget(deptEntityList, SysDeptDTO.class);
	}

	/**
	 * 获取所有上级部门ID
	 * @param pid 上级ID
	 */
	private String getPidList(Long pid){
		//顶级部门，无上级部门
		if(Constant.DEPT_ROOT.equals(pid)){
			return Constant.DEPT_ROOT + "";
		}

		//所有部门的id、pid列表
		List<SysDeptEntity> deptList = baseDao.getIdAndPidList();

		//list转map
		Map<Long, SysDeptEntity> map = new HashMap<>(deptList.size());
		for(SysDeptEntity entity : deptList){
			map.put(entity.getId(), entity);
		}

		//递归查询所有上级部门ID列表
		List<Long> pidList = new ArrayList<>();
		getPidTree(pid, map, pidList);

		return StringUtils.join(pidList, ",");
	}

	private void getPidTree(Long pid, Map<Long, SysDeptEntity> map, List<Long> pidList) {
		//顶级部门，无上级部门
		if(Constant.DEPT_ROOT.equals(pid)){
			return ;
		}

		//上级部门存在
		SysDeptEntity parent = map.get(pid);
		if(parent != null){
			getPidTree(parent.getPid(), map, pidList);
		}

		pidList.add(pid);
	}
}
