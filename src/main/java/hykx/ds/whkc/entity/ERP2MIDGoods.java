package hykx.ds.whkc.entity;
import lombok.Data;

@Data
public class ERP2MIDGoods {
    private String drugCode;
    private String drugName;
    private String pack;
    private String factory;
    private String unit;
    private String barcode;
    private String approval;
    private String busiType;
    private int stock;
    private float price;
    private int step;
    private float taxRate;
    private int midPack;
    private int wholePack;
    private float recommendedPrice;
    private String pName;
}
