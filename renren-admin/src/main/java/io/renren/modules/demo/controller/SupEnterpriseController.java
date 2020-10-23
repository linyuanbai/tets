package io.renren.modules.demo.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.demo.dto.SupEnterpriseDTO;
import io.renren.modules.demo.dto.SupTaskPlanDTO;
import io.renren.modules.demo.service.SupEnterpriseService;
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
 * 企业信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@RestController
@RequestMapping("demo/supenterprise")
@Api(tags="企业信息表")
public class SupEnterpriseController {
    @Autowired
    private SupEnterpriseService supEnterpriseService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("demo:supenterprise:page")
    public Result<PageData<SupEnterpriseDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<SupEnterpriseDTO> page = supEnterpriseService.page(params);

        return new Result<PageData<SupEnterpriseDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:supenterprise:info")
    public Result<SupEnterpriseDTO> get(@PathVariable("id") Long id){
        SupEnterpriseDTO data = supEnterpriseService.get(id);

        return new Result<SupEnterpriseDTO>().ok(data);
    }

    @GetMapping
    @ApiOperation("多条信息")
    @RequiresPermissions("demo:supenterprise:info")
    public Result<PageData<SupEnterpriseDTO>> get(@ApiIgnore @RequestParam Map<String, String> params){
        PageData<SupEnterpriseDTO> page = supEnterpriseService.getInfo(params);

        return new Result<PageData<SupEnterpriseDTO>>().ok(page);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:supenterprise:save")
    public Result save(@RequestBody SupEnterpriseDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        supEnterpriseService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:supenterprise:update")
    public Result update(@RequestBody SupEnterpriseDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        supEnterpriseService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:supenterprise:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        supEnterpriseService.delete(ids);

        return new Result();
    }

}