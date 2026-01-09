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
    public static final String MID_SYN_PGBY_KC_URL = "http://121.40.203.59:9021/GetAllPGBY";

    public static List<YZYGOODS> GetPGBY(String userName) throws Exception {
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_PGBY_KC_URL, param);
        if (res == null || res.isEmpty()) {
            log.warn("GetPGBY返回空数据");
            return new ArrayList<>();
        }
        JSONObject jsonObject = JSONObject.parseObject(res);
        if (jsonObject == null || !jsonObject.containsKey("data")) {
            log.warn("GetPGBY返回数据格式错误");
            return new ArrayList<>();
        }
        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
        return yzygoodsList;
    }

    public static List<YZYGOODS> GetYZYGOODSByUser(String userName) throws Exception {
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_KC_URL, param);
        if (res == null || res.isEmpty()) {
            log.warn("GetYZYGOODSByUser返回空数据");
            return new ArrayList<>();
        }
        JSONObject jsonObject = JSONObject.parseObject(res);
        if (jsonObject == null || !jsonObject.containsKey("data")) {
            log.warn("GetYZYGOODSByUser返回数据格式错误");
            return new ArrayList<>();
        }
        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
        return yzygoodsList;
    }

    public static void main(String[] args) throws Exception {
        GetPGBY("HBQJ");
    }
}
