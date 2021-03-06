package io.renren.modules.demo.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.demo.dto.RankEquipmentDTO;
import io.renren.modules.demo.excel.RankEquipmentExcel;
import io.renren.modules.demo.service.RankEquipmentService;
import io.renren.modules.sys.dto.SysDeptDTO;
import io.renren.modules.sys.service.SysDeptService;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 移动执法装备管理信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-19
 */
@RestController
@RequestMapping("demo/rankequipment")
@Api(tags="移动执法装备管理信息表")
public class RankEquipmentController {

    @Autowired
    private RankEquipmentService rankEquipmentService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "deptId", value = "部门ID", paramType = "query", dataType="String")
    })
    @RequiresPermissions("demo:rankequipment:page")
    public Result<PageData<RankEquipmentDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<RankEquipmentDTO> page = rankEquipmentService.page(params);

        return new Result<PageData<RankEquipmentDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:rankequipment:info")
    public Result<RankEquipmentDTO> get(@PathVariable("id") Long id){
        RankEquipmentDTO data = rankEquipmentService.get(id);
        // 查询部门信息、创建人、修改人姓名
        SysDeptDTO sysDeptDTO = sysDeptService.get(data.getDeptId());
        String creatorName = sysUserService.get(data.getCreator()).getRealName();
        // 设置部门名称、上级部门名称、修改人姓名、创建人姓名
        data.setDeptName(sysDeptDTO.getName());
        data.setPreDeptName(sysDeptDTO.getParentName());
        data.setCreatorName(creatorName);

        return new Result<RankEquipmentDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:rankequipment:save")
    public Result save(@RequestBody RankEquipmentDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        rankEquipmentService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:rankequipment:update")
    public Result update(@RequestBody RankEquipmentDTO dto){
        System.out.println(dto);
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        rankEquipmentService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:rankequipment:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        rankEquipmentService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("demo:rankequipment:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<RankEquipmentDTO> list = rankEquipmentService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, RankEquipmentExcel.class);
    }

}