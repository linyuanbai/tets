package io.renren.modules.demo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 人员附加信息表
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-29
 */
@Data
public class SysUserExtraExcel {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "用户ID")
    private Long userId;
    @Excel(name = "手机号码")
    private String mobilePhone;
    @Excel(name = "办公室")
    private String office;
    @Excel(name = "办公电话")
    private String workPhone;
    @Excel(name = "电子邮箱")
    private String email;
    @Excel(name = "有无监察执法证号  1：有/0：无")
    private String hasSuperviseCard;
    @Excel(name = "监察执法证号")
    private String superviseCardNo;
    @Excel(name = "有无行政执法证号  1：有/0：无")
    private String hasAdministrationCard;
    @Excel(name = "行政执法证号")
    private String administrationCardNo;
    @Excel(name = "人员类别  1：有/0：无")
    private String userType;
    @Excel(name = "级别")
    private String workGrade;
    @Excel(name = "岗位情况")
    private String officeInfo;
    @Excel(name = "性别  0：男   1：女    2：保密")
    private String gender;
    @Excel(name = "民族")
    private String nation;
    @Excel(name = "来源")
    private String source;
    @Excel(name = "籍贯")
    private String nativePlace;
    @Excel(name = "出生日期")
    private String birthday;
    @Excel(name = "开始工作时间")
    private String startWork;
    @Excel(name = "结束工作时间")
    private String endWork;
    @Excel(name = "最高学历")
    private String education;
    @Excel(name = "考核年份")
    private String checkYear;
    @Excel(name = "考核情况")
    private String checkInfo;
    @Excel(name = "身份证号码")
    private String cardNo;
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