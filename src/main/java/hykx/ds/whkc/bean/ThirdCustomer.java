package hykx.ds.whkc.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ThirdCustomer {
    @ApiModelProperty("客户编码")
    String code;
    @ApiModelProperty("客户地址")
    String address;
    @ApiModelProperty("客户企业名称")
    String name;
    @ApiModelProperty("客户联系方式")
    String telephone;
    @ApiModelProperty("客户企业征信代码")
    String taxNumber;
    @ApiModelProperty("客户企业征信代码")
    String linkman;
    @ApiModelProperty("客户企业许可证号")
    String xkzh;
}
