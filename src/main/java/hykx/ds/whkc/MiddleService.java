package hykx.ds.whkc;

import com.alibaba.fastjson.JSONObject;
import hykx.ds.whkc.entity.MyGoodsEntity;
import hykx.ds.whkc.entity.YBMGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MiddleService {
    public static final String MID_SYN_KC_URL = "http://116.62.46.187:10018/GetMyGoodsEntityByUser";
    public static final String MID_SYN_KC_YBM_URL = "http://116.62.46.187:10018/GetYBMGoodsByUser";
    public static List<MyGoodsEntity> GetMyGoodsEntityByUse(String userName) throws Exception {
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_KC_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<MyGoodsEntity> myGoodsEntityList = jsonObject.getJSONArray("data").toJavaList(MyGoodsEntity.class);
        return myGoodsEntityList;
    }
    public static List<YBMGoods> GetYBMGoodsByUser(String userName) throws Exception {
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_KC_YBM_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<YBMGoods> ybmGoodsList = jsonObject.getJSONArray("data").toJavaList(YBMGoods.class);
        return ybmGoodsList;
    }
    public static void main(String[] args) throws Exception {
        //GetMyGoodsEntityByUse("18573102130");
        GetYBMGoodsByUser("HNWH");
    }
}
