package hykx.ds.whkc.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class xyyddmx {
    private int id;
    private int order_id;
    private String order_no;
    private int order_detail_id;
    private String erp_code;
    private String sku_name;
    private float sku_price;
    private float sku_purchase_price;
    private float purchase_num;
    private float discount_amount;
    private float platform_amount;
    private float shop_amount;
    private float pay_amount;
    private float sku_pay_price;
    private int promo_type;
}
