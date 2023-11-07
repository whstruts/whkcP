package hykx.ds.whkc.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class xyyddhz {
    private int id;
    private String org_id;
    private String vender_code;
    private int order_id;
    private String order_no;
    private String order_create_time;
    private int pay_type;
    private int pay_channel;
    private float total_amount;
    private float discount_amount;
    private float platform_amount;
    private float shop_amount;
    private float freight;
    private float pay_amount;
    private String pick_name;
    private String pick_address;
    private String pick_phone;
    private String remark;
    private int order_state;
    private String merchant_name;
    private String province_name;
    private String city_name;
    private String area_name;
    private int first_order;
    private int order_type;
    private String order_code;
    private String order_status;
    private String erp_order_no;
    private int is_hy_run;
}
