package hykx.ds.whkc;

import com.alibaba.fastjson.JSONObject;
import hykx.ds.whkc.entity.MyGoodsEntity;
import hykx.ds.whkc.entity.YBMGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public static List<MyGoodsEntity> GetYBMG2MGEByUser(String userName) throws Exception {
        List<MyGoodsEntity> myGoodsEntityList = new ArrayList<>();
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_KC_YBM_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<YBMGoods> ybmGoodsList = jsonObject.getJSONArray("data").toJavaList(YBMGoods.class);
        ybmGoodsList.forEach(ybmGoods -> {
            MyGoodsEntity myGoodsEntity = new MyGoodsEntity();
            myGoodsEntity.setId(ybmGoods.getSkuId()+ybmGoods.getPname()+ybmGoods.getDname()+"L");
            myGoodsEntity.setYpbh(ybmGoods.getSkuId()+ybmGoods.getPname()+ybmGoods.getDname()+"L");
            myGoodsEntity.setSl(ybmGoods.getKcsl());
            myGoodsEntity.setScrq(ybmGoods.getScrq());
            myGoodsEntity.setYxq(ybmGoods.getXq());
            myGoodsEntity.setUpdateTime(ybmGoods.getUpdatedate());
            myGoodsEntity.setYpmc(ybmGoods.getName());
            myGoodsEntity.setDj((float) ybmGoods.getPrice());
            myGoodsEntity.setCdmc(ybmGoods.getSccj());
            myGoodsEntity.setGg(ybmGoods.getGg());
            myGoodsEntity.setDw(ybmGoods.getPack());
            myGoodsEntity.setJx(ybmGoods.getJx());
            myGoodsEntity.setPzwh(ybmGoods.getPzwh());
            myGoodsEntity.setZbz(String.valueOf(ybmGoods.getZbz()));
            myGoodsEntity.setBz(String.valueOf(ybmGoods.getBz()));
            myGoodsEntity.setIsretail(1);
            myGoodsEntity.setPh(" ");
            myGoodsEntity.setCddm(" ");
            myGoodsEntity.setYpdm(" ");
            myGoodsEntity.setTm(" ");
            myGoodsEntity.setLsj(0.00F);
            myGoodsEntityList.add(myGoodsEntity);
        });
        return myGoodsEntityList;
    }


    public static void main(String[] args) throws Exception {
        //GetMyGoodsEntityByUse("18573102130");
        GetYBMG2MGEByUser("18163798584");
    }
}
