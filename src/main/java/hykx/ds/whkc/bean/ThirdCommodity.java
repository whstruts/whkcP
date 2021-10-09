package hykx.ds.whkc.bean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ThirdCommodity {
    @ApiModelProperty("商品唯一码")
    String drugid;

    /**
     * spu数据
     */
    @ApiModelProperty("商品名，需要拆解")
    String drugCommonName;
    @ApiModelProperty("药品代码")
    String nameZjm;
    @ApiModelProperty("生产厂家")
    String manufacturer;
    @ApiModelProperty("产地代码")
    String manufacturerZjm;
    @ApiModelProperty("批准文号")
    String approveNumber;
    @ApiModelProperty("药品图片")
    String drugImg;
    @ApiModelProperty("处方分类")
    String recipeType;
    @ApiModelProperty("所属分类")
    String typeCode;
    @ApiModelProperty("剂型")
    String dosageForm;
    @ApiModelProperty("性状")
    String appearance;
    @ApiModelProperty("成分")
    String bases;
    @ApiModelProperty("适应症/功能主治")
    String majorFunctions;
    @ApiModelProperty("不良反应")
    String untowardEffect;
    @ApiModelProperty("禁忌")
    String taboo;
    @ApiModelProperty("贮藏")
    String store;
    @ApiModelProperty("注意事项")
    String warnings;
    @ApiModelProperty("药物相互作用")
    String drugInteractions;
    @ApiModelProperty("品牌")
    String brand;
    @ApiModelProperty("用法用量")
    String usageDosage;

    /**
     * sku数据
     */
    @ApiModelProperty("规格")
    String specifications;
    @ApiModelProperty("包装单位")
    String packageUnit;
    @ApiModelProperty("中包装数量")
    Integer mediumPackage;
    @ApiModelProperty("大包装数量")
    Integer largePackage;
    @ApiModelProperty("左侧面图")
    String leftView;
    @ApiModelProperty("右侧面图")
    String rightView;
    @ApiModelProperty("拆包装图")
    String unpackView;
    @ApiModelProperty("说明书图")
    String specificationView;
    @ApiModelProperty("商品编号")
    String goodsSn;


    /**
     * 商品数据
     */
    @ApiModelProperty("是否可拆零")
    String isRetail;
    @ApiModelProperty("生产批次")
    String productionBatch;
    @ApiModelProperty("有效日期")
    String dateExpiration;
    @ApiModelProperty("库存")
    String repertory;
    @ApiModelProperty("条码图")
    String barCode;
    @ApiModelProperty("供应商名称")
    String supplierName;
    @ApiModelProperty("供应商")
    String supplier;
    @ApiModelProperty("供应商价格")
    String supplierPrice;
    @ApiModelProperty("生产日期")
    String productionDate;
}
