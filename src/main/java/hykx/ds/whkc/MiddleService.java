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
    public static final String MID_SYN_HYGY_KC_URL = "http://116.62.46.187:9021/GetHYGYAllWithSN";

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
        //GetHYGYGoods("18692180722");
//        ysbdd dd = new ysbdd();
//        ysbddhz hz = new ysbddhz();
//        hz.setDjbh("100434395");
//        hz.setRq("2024-03-08");
//        hz.setOntime("21:53:10");
//        hz.setCustomerId("35746");
//        hz.setStatus("已提交");
//        hz.setJe(20);
//        hz.setBeizhu("321088640药师帮已付款旗舰店自营[药师帮首单，三证可能需要随货配送]");
//        hz.setIs_run(1);
//        hz.setDrugstorename("上蔡县隆仁大药房");
//        hz.setUserName("HNHR");
//        ysbddmx mx = new ysbddmx();
//        mx.setDjbh("100434295");
//        mx.setDj_sn("1");
//        mx.setDrugcode("fa71b4cc-0df2-44f7-bf0c-0eba8674f118");
//        mx.setShl(10);
//        mx.setDj(2);
//        mx.setJe(20);
//        List<ysbddmx> list = new ArrayList<>();
//        list.add(mx);
//        dd.setYsbddhz(hz);
//        dd.setYsbddmxes(list);
//        saveOrder2GY(dd);
//        GetHYGYGoods("HNYS");
        GetPGBYByUser("HNZXZH");
    }
}
