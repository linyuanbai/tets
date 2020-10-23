package io.renren.modules.demo.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.demo.dto.SupTaskPlanDTO;
import io.renren.modules.demo.service.SupTaskPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


/**
 * 任务计划表基本信息
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-13
 */
@RestController
@RequestMapping("demo/suptaskplan")
@Api(tags="任务计划表基本信息")
public class SupTaskPlanController {

    @Autowired
    private SupTaskPlanService supTaskPlanService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("demo:suptaskplan:page")
    // {order=, orderField=, page=1, limit=10, taskName=, startTime=, _t=1602589235474}
    public Result<PageData<SupTaskPlanDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<SupTaskPlanDTO> page = supTaskPlanService.page(params);

        return new Result<PageData<SupTaskPlanDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:suptaskplan:info")
    public Result<SupTaskPlanDTO> get(@PathVariable("id") Long id){
        SupTaskPlanDTO data = supTaskPlanService.get(id);

        return new Result<SupTaskPlanDTO>().ok(data);
    }

    @GetMapping
    @ApiOperation("多条信息")
    @RequiresPermissions("demo:suptaskplan:info")
    public Result<PageData<SupTaskPlanDTO>> get(@ApiIgnore @RequestParam Map<String, String> params){
        PageData<SupTaskPlanDTO> page = supTaskPlanService.getInfo(params);

        return new Result<PageData<SupTaskPlanDTO>>().ok(page);
    }

//    @PostMapping
//    @ApiOperation("保存")
//    @LogOperation("保存")
//    @RequiresPermissions("demo:suptaskplan:save")
//    public Result save(@RequestBody SupTaskPlanDTO dto){
//        // 效验数据
//        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
//
//        supTaskPlanService.save(dto);
//
//        return new Result();
//    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:suptaskplan:save")
    public Result save(@ApiIgnore @RequestParam Map<String, String> params){
        //{taskName=搜索, taskType=1, enterpriseName=,
        // province=, startTime=2020-10-06, endTime=2020-11-10, id[0]=1, id[1]=2}
        supTaskPlanService.saveByParams(params);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:suptaskplan:update")
    public Result update(@RequestBody SupTaskPlanDTO dto){
        // SupTaskPlanDTO(id=1, uuid=121212121212, createDate=Tue Oct 13 00:00:00 CST 2020,
        // creator=11, updateDate=Tue Oct 13 00:00:00 CST 2020, updater=12, isDeleted=0,
        // taskName=hello, taskType=1, startTime=Tue Oct 13 00:00:00 CST 2020,
        // endTime=Tue Oct 13 00:00:00 CST 2020, personNum=10, maker=1444)
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        supTaskPlanService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:suptaskplan:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        supTaskPlanService.delete(ids);

        return new Result();
    }

}