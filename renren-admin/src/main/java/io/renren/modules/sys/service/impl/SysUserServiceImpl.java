/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.modules.demo.dto.SysUserExtraDTO;
import io.renren.modules.demo.service.SysUserExtraService;
import io.renren.modules.security.user.SecurityUser;
import io.renren.modules.security.user.UserDetail;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.dto.SysUserDTO;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.security.password.PasswordUtils;
import io.renren.modules.sys.enums.SuperAdminEnum;
import io.renren.modules.sys.service.SysDeptService;
import io.renren.modules.sys.service.SysRoleUserService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysRoleUserService sysRoleUserService;
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private SysUserExtraService sysUserExtraService;

	@Override
	public PageData<SysUserDTO> page(Map<String, Object> params) {
		//转换成like
		paramsToLike(params, "username");

		//分页
		IPage<SysUserEntity> page = getPage(params, Constant.CREATE_DATE, false);

		//普通管理员，只能查询所属部门及子部门的数据
		UserDetail user = SecurityUser.getUser();
		if(user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
			params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
		}

		//查询
		List<SysUserEntity> list = baseDao.getList(params);

		return getPageData(list, page.getTotal(), SysUserDTO.class);
	}

	@Override
	public List<SysUserDTO> list(Map<String, Object> params) {
		//普通管理员，只能查询所属部门及子部门的数据
		UserDetail user = SecurityUser.getUser();
		if(user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
			params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
		}

		List<SysUserEntity> entityList = baseDao.getList(params);

		return ConvertUtils.sourceToTarget(entityList, SysUserDTO.class);
	}

	@Override
	public SysUserDTO get(Long id) {
		SysUserEntity entity = baseDao.getById(id);

		return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
	}

	@Override
	public SysUserDTO getByUsername(String username) {
		SysUserEntity entity = baseDao.getByUsername(username);
		return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

		//密码加密
		String password = PasswordUtils.encode(entity.getPassword());
		entity.setPassword(password);

		//保存用户
		entity.setSuperAdmin(SuperAdminEnum.NO.value());
		insert(entity);

		//保存角色用户关系
		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserDTO dto) {
		SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

		//密码加密
		if(StringUtils.isBlank(dto.getPassword())){
			entity.setPassword(null);
		}else{
			String password = PasswordUtils.encode(entity.getPassword());
			entity.setPassword(password);
		}

		//更新用户
		updateById(entity);

		//更新角色用户关系
		sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
	}

	@Override
	public void delete(Long[] ids) {
		//删除用户
		baseDao.deleteBatchIds(Arrays.asList(ids));

		//删除角色用户关系
		sysRoleUserService.deleteByUserIds(ids);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePassword(Long id, String newPassword) {
		newPassword = PasswordUtils.encode(newPassword);

		baseDao.updatePassword(id, newPassword);
	}

	@Override
	public int getCountByDeptId(Long deptId) {
		return baseDao.getCountByDeptId(deptId);
	}

	@Override
	public List<Long> getUserIdListByDeptId(List<Long> deptIdList) {
		return baseDao.getUserIdListByDeptId(deptIdList);
	}

	@Override
	public void save(Map<String, String> params) {
		// {username=gaewge, password=gewgewa, realName=fawef, status=1, job=0,
		// jobStart=, jobLevel=2, levelStart=, deptId=1067246875800000067,
		// sort=, remarks=, deptName=人事处, userId=, mobile=, office=,
		// workPhone=, email=, roleIdList[0]=1316642919876149249, superviseCardNo=,
		// administrationCardNo=, userType=, workGrade=, officeInfo=,
		// gender=0, nation=geaw, source=gew, nativePlace=gawe,
		// birthday=2020-10-20T16:00:00.000Z, startWork=, endWork=,
		// education=, checkYear=, checkInfo=, cardNo=gewg}

		// 创建用户dto
		SysUserDTO sysUserDTO = new SysUserDTO();
		sysUserDTO.setUsername(params.get("username"));
		sysUserDTO.setPassword(params.get("password"));
		sysUserDTO.setRealName(params.get("realName"));
		sysUserDTO.setGender(new Integer(params.get("gender")));
		sysUserDTO.setMobile(params.get("mobile"));
		sysUserDTO.setEmail(params.get("email"));
		sysUserDTO.setJob(params.get("job"));
		sysUserDTO.setJobLevel(params.get("jobLevel"));
		sysUserDTO.setDeptId(new Long(params.get("deptId")));
		sysUserDTO.setStatus(new Integer(params.get("status")));
		List<Long> roleIdList = new ArrayList<Long>();
		roleIdList.add(new Long(params.get("roleIdList[0]")));
		sysUserDTO.setRoleIdList(roleIdList);
		// 保存用户
		save(sysUserDTO);

		// 查询新增的用户
		SysUserDTO newDto = getByUsername(sysUserDTO.getUsername());

		// 创建用户额外信息dto
		SysUserExtraDTO sysUserExtraDTO = new SysUserExtraDTO();
		sysUserExtraDTO.setUserId(newDto.getId());
		sysUserExtraDTO.setMobilePhone(params.get("mobile"));
		sysUserExtraDTO.setOffice(params.get("office"));
		sysUserExtraDTO.setWorkPhone(params.get("workPhone"));
		sysUserExtraDTO.setEmail(params.get("email"));
		sysUserExtraDTO.setSuperviseCardNo(params.get("监察执法证号"));
		sysUserExtraDTO.setAdministrationCardNo(params.get("administrationCardNo"));
		sysUserExtraDTO.setUserType(params.get("userType"));
		sysUserExtraDTO.setOfficeInfo(params.get("officeInfo"));
		sysUserExtraDTO.setGender(params.get("gender"));
		sysUserExtraDTO.setNation(params.get("nation"));
		sysUserExtraDTO.setSource(params.get("source"));
		sysUserExtraDTO.setNativePlace(params.get("nativePlace"));
		sysUserExtraDTO.setBirthday(params.get("birthday"));
		sysUserExtraDTO.setStartWork(params.get("startWork"));
		sysUserExtraDTO.setEndWork(params.get("endWork"));
		sysUserExtraDTO.setEducation(params.get("education"));
		sysUserExtraDTO.setCheckYear(params.get("checkYear"));
		sysUserExtraDTO.setCheckInfo(params.get("checkInfo"));
		sysUserExtraDTO.setCardNo(params.get("cardNo"));

		// 保存用户额外信息
		sysUserExtraService.save(sysUserExtraDTO);
	}

	@Override
	public void update(Map<String, String> params) {
		// 创建用户dto
		SysUserDTO sysUserDTO = new SysUserDTO();
		sysUserDTO.setUsername(params.get("username"));
		sysUserDTO.setPassword(params.get("password"));
		sysUserDTO.setRealName(params.get("realName"));
		sysUserDTO.setGender(new Integer(params.get("gender")));
		sysUserDTO.setMobile(params.get("mobile"));
		sysUserDTO.setEmail(params.get("email"));
		sysUserDTO.setJob(params.get("job"));
		sysUserDTO.setJobLevel(params.get("jobLevel"));
		sysUserDTO.setDeptId(new Long(params.get("deptId")));
		sysUserDTO.setStatus(new Integer(params.get("status")));
		List<Long> roleIdList = new ArrayList<Long>();
		roleIdList.add(new Long(params.get("roleIdList[0]")));
		sysUserDTO.setRoleIdList(roleIdList);
		// 获取要修改用户的id
		Long userId = getByUsername(sysUserDTO.getUsername()).getId();
		sysUserDTO.setId(userId);
		update(sysUserDTO);

		// 获取额外信息记录的id
		Long id = sysUserExtraService.selectByUserId(userId).getId();
		// 创建用户额外信息dto
		SysUserExtraDTO sysUserExtraDTO = new SysUserExtraDTO();
		sysUserDTO.setId(id);
		sysUserExtraDTO.setUserId(userId);
		sysUserExtraDTO.setMobilePhone(params.get("mobile"));
		sysUserExtraDTO.setOffice(params.get("office"));
		sysUserExtraDTO.setWorkPhone(params.get("workPhone"));
		sysUserExtraDTO.setEmail(params.get("email"));
		sysUserExtraDTO.setSuperviseCardNo(params.get("监察执法证号"));
		sysUserExtraDTO.setAdministrationCardNo(params.get("administrationCardNo"));
		sysUserExtraDTO.setUserType(params.get("userType"));
		sysUserExtraDTO.setOfficeInfo(params.get("officeInfo"));
		sysUserExtraDTO.setGender(params.get("gender"));
		sysUserExtraDTO.setNation(params.get("nation"));
		sysUserExtraDTO.setSource(params.get("source"));
		sysUserExtraDTO.setNativePlace(params.get("nativePlace"));
		sysUserExtraDTO.setBirthday(params.get("birthday"));
		sysUserExtraDTO.setStartWork(params.get("startWork"));
		sysUserExtraDTO.setEndWork(params.get("endWork"));
		sysUserExtraDTO.setEducation(params.get("education"));
		sysUserExtraDTO.setCheckYear(params.get("checkYear"));
		sysUserExtraDTO.setCheckInfo(params.get("checkInfo"));
		sysUserExtraDTO.setCardNo(params.get("cardNo"));

		// 保存用户额外信息
		sysUserExtraService.update(sysUserExtraDTO);
	}

}
