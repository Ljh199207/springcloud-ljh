package com.example.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 表2
 * </p>
 *
 * @author ljh
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("table_two")
@ApiModel(value = "TableTwo对象", description = "表2")
public class TableTwo extends Model<TableTwo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "备用1")
    @TableField("back_one")
    private String backOne;

    @ApiModelProperty(value = "备用2")
    @TableField("back_two")
    private String backTwo;

    @ApiModelProperty(value = "备用3")
    @TableField("back_three")
    private String backThree;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
