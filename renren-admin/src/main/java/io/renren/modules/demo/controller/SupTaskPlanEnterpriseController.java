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
import io.renren.modules.demo.dto.SupTaskPlanDTO;
import io.renren.modules.demo.dto.SupTaskPlanEnterpriseDTO;
import io.renren.modules.demo.service.SupTaskPlanEnterpriseService;
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
 * 任务计划企业信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@RestController
@RequestMapping("demo/suptaskplanenterprise")
@Api(tags="任务计划企业信息表")
public class SupTaskPlanEnterpriseController {
    @Autowired
    private SupTaskPlanEnterpriseService supTaskPlanEnterpriseService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("demo:suptaskplanenterprise:page")
    public Result<PageData<SupTaskPlanEnterpriseDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<SupTaskPlanEnterpriseDTO> page = supTaskPlanEnterpriseService.page(params);

        return new Result<PageData<SupTaskPlanEnterpriseDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:suptaskplanenterprise:info")
    public Result<SupTaskPlanEnterpriseDTO> get(@PathVariable("id") Long id){
        SupTaskPlanEnterpriseDTO data = supTaskPlanEnterpriseService.get(id);

        return new Result<SupTaskPlanEnterpriseDTO>().ok(data);
    }

    @GetMapping()
    @ApiOperation("多条信息")
    @RequiresPermissions("demo:suptaskplanenterprise:info")
    public Result<PageData<Map<String, String>>> get(@ApiIgnore @RequestParam Map<String, String> params){
        System.out.println(params);
        PageData<Map<String, String>> page = supTaskPlanEnterpriseService.getInfo(params);

        return new Result<PageData<Map<String, String>>>().ok(page);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:suptaskplanenterprise:save")
    public Result save(@RequestBody SupTaskPlanEnterpriseDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        supTaskPlanEnterpriseService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:suptaskplanenterprise:update")
    public Result update(@RequestBody SupTaskPlanEnterpriseDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        supTaskPlanEnterpriseService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:suptaskplanenterprise:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        supTaskPlanEnterpriseService.delete(ids);

        return new Result();
    }

}