package com.tulingxueyuan.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户信息管理
 *
 * @author weimingyang@ubenchuxing.com
 */
@Data
@ApiModel(value = "用户中心")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 6945247619508218225L;
    /** id */
    @ApiModelProperty(value = "id")
    private Long id;

    /** 微信用户ID */
    @ApiModelProperty(value = "微信用户ID")
    private String wxUserId;
    /** 租户ID */
    @ApiModelProperty(value = "租户ID")
    private Integer tenantId;

    /** 微信用户ID */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    /** 姓名 */
    @ApiModelProperty(value = "姓名")
    private String realName;
    /** 手机号 */
    @ApiModelProperty(value = "手机号")

    private String mobile;
    /**
     * vip 等级
     */

    @ApiModelProperty(value = "vip等级")
    private Integer vipLevel;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "create_time")
    private LocalDateTime createTime;


    @ApiModelProperty(value = "create_time")
    private LocalDateTime endTime;

    /**
     * 是否是新用户 0是老用户 1是新用户
     */

    @ApiModelProperty(value = "是否是新用户 0是老用户,1是新用户", allowableValues = "0,1",
        notes = "com.ubencx.facade.user.enums.IsNewUser")
    private Integer isNew;
}
