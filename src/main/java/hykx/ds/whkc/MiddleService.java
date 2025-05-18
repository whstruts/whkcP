package hykx.ds.whkc;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import hykx.ds.whkc.entity.*;
import hykx.ds.whkc.tools.CompressionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class MiddleService {
    public static final String MID_SYN_KC_URL = "http://116.62.46.187:10018/GetYZYGOODSByUser";
    public static final String MID_SYN_NC_KC_URL = "http://116.62.46.187:10018/GetNCGoods";
    public static final String MID_SYN_HYGY_KC_URL = "http://112.124.67.70:9023/GetHYGYAllWithSN";

    //public static final String MID_SYN_HYGY_ORDER_URL = "http://localhost:9020/saveOrderGY";
    public static final String MID_SYN_HYGY_ORDER_URL = "http://112.124.67.70:9023/saveOrderGY";
    public static final String MID_SYN_PGBY_KC_URL = "http://112.124.67.70:9023/GetAllPGBY";
    public static final String MID_SYN_PGBY_KC_URL_X = "http://112.124.67.70:9023/GetAllPGBY_X";
//    public static List<YZYGOODS> GetYZYGOODSByUser(String userName) throws Exception {
//        String param = "userName=" + userName;
//        String res = HttpUtils.sendGet(MID_SYN_KC_URL, param);
//        JSONObject jsonObject = JSONObject.parseObject(res);
//        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
//        return yzygoodsList;
//    }
//    public static List<YZYGOODS> GetNCGoods(String userName) throws Exception {
//        String param = "userName=" + userName;
//        String res = HttpUtils.sendGet(MID_SYN_NC_KC_URL, param);
//        JSONObject jsonObject = JSONObject.parseObject(res);
//        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
//        return yzygoodsList;
//    }
//    public static List<YZYGOODS> GetHYGYGoods(String userName) throws Exception {
//        String param = "userName=" + userName;
//        String res = HttpUtils.sendGet(MID_SYN_HYGY_KC_URL, param);
//        JSONObject jsonObject = JSONObject.parseObject(res);
//        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
//        return yzygoodsList;
//    }


//    public static JSONObject saveOrder2GY(ysbdd order) throws Exception {
//        try{
//            String res = HttpUtils.getByBody(MID_SYN_HYGY_ORDER_URL,JSONObject.toJSONString(order));
//            JSONObject jsonObject = JSONObject.parseObject(res);
//            return jsonObject;
//        }
//        catch (Exception e)
//        {
//            return null;
//        }
//
//    }

    public static List<YZYGOODS> GetPGBY(String userName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> param = new HashMap<>();
        param.put("userName", userName); // 键为"userName"，值为userName变量
        String res = HttpUtils.sendGet(MID_SYN_PGBY_KC_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        // 4. 解压缩数据
        String decompressedJson = CompressionUtils.decompress(jsonObject.getBytes("data"));

        // 反序列化为List<YZYGOODS>
        List<YZYGOODS> goodsList = mapper.readValue(
                decompressedJson,
                mapper.getTypeFactory().constructCollectionType(List.class, YZYGOODS.class)
        );
        return goodsList;
    }

    public static List<YZYGOODS> GetPGBY_X(String userName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> param = new HashMap<>();
        param.put("userName", userName); // 键为"userName"，值为userName变量
        String res = HttpUtils.sendGet(MID_SYN_PGBY_KC_URL_X, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        // 4. 解压缩数据
        String decompressedJson = CompressionUtils.decompress(jsonObject.getBytes("data"));

        // 反序列化为List<YZYGOODS>
        List<YZYGOODS> goodsList = mapper.readValue(
                decompressedJson,
                mapper.getTypeFactory().constructCollectionType(List.class, YZYGOODS.class)
        );
        return goodsList;
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
        GetPGBY_X("YYKR");
    }
}
