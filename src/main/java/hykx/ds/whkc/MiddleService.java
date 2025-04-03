package hykx.ds.whkc;

import com.alibaba.fastjson.JSONObject;
import hykx.ds.whkc.entity.ysbdd;
import hykx.ds.whkc.entity.YZYGOODS;
import hykx.ds.whkc.entity.ysbddhz;
import hykx.ds.whkc.entity.ysbddmx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MiddleService {
    public static final String MID_SYN_KC_URL = "http://116.62.46.187:10018/GetYZYGOODSByUser";
    public static final String MID_SYN_NC_KC_URL = "http://116.62.46.187:10018/GetNCGoods";

    public static final String MID_SYN_KC_PGBY_URL = "http://116.62.46.187:10018/GetPGBYByUser";
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
    public static List<YZYGOODS> GetPGBYByUser(String userName) throws Exception {
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_KC_PGBY_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
        return yzygoodsList;
    }
    public static void main(String[] args) throws Exception {
        GetPGBYByUser("JSDFYY");
    }
}
