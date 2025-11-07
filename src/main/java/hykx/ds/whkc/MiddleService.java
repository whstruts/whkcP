package hykx.ds.whkc;


import com.alibaba.fastjson.JSONObject;
import hykx.ds.whkc.bean.YZYGOODS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MiddleService {
    public static final String MID_SYN_KC_URL_P = "http://112.124.67.70:9021/GetAllPGBY";

    public static List<YZYGOODS> GetYZYGOODSByUser(String userName) throws Exception {
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_KC_URL_P, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
        return yzygoodsList;
    }

//    public static void main(String[] args) throws Exception {
//        GetYZYGOODSByUser("HNYJ");
//    }
}
