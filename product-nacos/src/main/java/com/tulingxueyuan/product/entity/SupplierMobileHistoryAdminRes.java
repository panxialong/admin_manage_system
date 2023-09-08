package com.tulingxueyuan.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel
@Data
public class SupplierMobileHistoryAdminRes {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户账号")
    private String userName;

    @ApiModelProperty(value = "运力id")
    private Integer supplierId;

    @ApiModelProperty(value = "运力品牌名称")
    private String supplierName;

    @ApiModelProperty(value = "0:虚拟号 1:真实号")
    private Integer mobileTag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
