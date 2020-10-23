package io.renren.modules.demo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 移动执法装备管理信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-19
 */
@Data
public class RankEquipmentExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "所属机构")
    private Long deptId;
    @Excel(name = "手持移动终端配置数")
    private Integer handMobileNumber;
    @Excel(name = "手持移动终端配置时间")
    private String handMobileDate;
    @Excel(name = "笔记本电脑配置数")
    private Integer laptopNumber;
    @Excel(name = "笔记本电脑配置时间")
    private String laptopDate;
    @Excel(name = "便携式扫描打印配置数")
    private Integer portablePrinterNumber;
    @Excel(name = "便携式扫描打印机配置时间")
    private String portablePrinterDate;
    @Excel(name = "便携式Wifi热点配置数")
    private Integer portableWifiNumber;
    @Excel(name = "便携式Wifi热点配置时间")
    private String portableWifiDate;
    @Excel(name = "创建时间")
    private Date createDate;
    @Excel(name = "修改时间")
    private Date updateDate;
    @Excel(name = "创建人")
    private Long creator;
    @Excel(name = "修改人")
    private Long updater;
    @Excel(name = "是否删除  1：已删除<br />0：未删除")
    private Integer isDeleted;

}