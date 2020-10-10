package io.renren.modules.demo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 商品管理
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2020-10-05
 */
@Data
public class GoodsExcel {
    @Excel(name = "商品ID")
    private Long id;
    @Excel(name = "商品名")
    private String name;
    @Excel(name = "介绍")
    private String intro;
    @Excel(name = "价格")
    private Integer price;
    @Excel(name = "数量")
    private Integer num;
    @Excel(name = "创建者")
    private Long creator;
    @Excel(name = "创建时间")
    private Date createDate;

}