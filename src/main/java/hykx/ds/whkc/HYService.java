package hykx.ds.whkc;

import com.alibaba.fastjson.JSONObject;
import hykx.ds.whkc.bean.YZYGOODS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HYService {
    public static final String HY_SYN_KC_URL = "http://localhost:9020/getHYGoods";
    public static List<YZYGOODS> GetHYGoods() throws Exception {
        String res = HttpUtils.sendGet(HY_SYN_KC_URL,"");
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<YZYGOODS> goodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
        return goodsList;
    }
    public static void main(String[] args) throws Exception {
        GetHYGoods();
    }
}

