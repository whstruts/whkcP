package hykx.ds.whkc;

import com.alibaba.fastjson.JSONObject;
import hykx.ds.whkc.entity.MyGoodsEntity;
import hykx.ds.whkc.entity.YBMGoods;
import hykx.ds.whkc.entity.YZYGOODS;
import hykx.ds.whkc.entity.ysbdd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MiddleService {
    public static final String MID_SYN_KC_URL = "http://116.62.46.187:10018/GetYZYGOODSByUser";
    public static final String MID_SYN_NC_KC_URL = "http://116.62.46.187:10018/GetNCGoods";
    public static final String MID_SYN_HYGY_KC_URL = "http://111.173.116.31:9020/GetHYGYAllWithSN";

    public static final String MID_SYN_HYGY_ORDER_URL = "http://111.173.116.31:9020/saveOrderGY";
    public static List<YZYGOODS> GetYZYGOODSByUser(String userName) throws Exception {
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_KC_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
        return yzygoodsList;
    }
    public static List<YZYGOODS> GetNCGoods(String userName) throws Exception {
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_NC_KC_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
        return yzygoodsList;
    }
    public static List<YZYGOODS> GetHYGYGoods(String userName) throws Exception {
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_HYGY_KC_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
        return yzygoodsList;
    }


    public static JSONObject saveOrder2GY(ysbdd order) throws Exception {
        try{
            String res = HttpUtils.getByBody(MID_SYN_HYGY_ORDER_URL,JSONObject.toJSONString(order));
            JSONObject jsonObject = JSONObject.parseObject(res);
            return jsonObject;
        }
        catch (Exception e)
        {
            return null;
        }

    }


    public static void main(String[] args) throws Exception {
        GetHYGYGoods("18692180722");
    }
}
