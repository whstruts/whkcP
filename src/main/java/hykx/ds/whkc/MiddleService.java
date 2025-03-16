package hykx.ds.whkc;

import com.alibaba.fastjson.JSONObject;
import hykx.ds.whkc.entity.MyGoodsEntity;
import hykx.ds.whkc.entity.YBMGoods;
import hykx.ds.whkc.entity.YZYGOODS;
import hykx.ds.whkc.entity.ysbdd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MiddleService {
    public static final String MID_SYN_KC_URL = "http://116.62.46.187:10018/GetMyGoodsEntityByUser";
    public static final String MID_SYN_KC_YBM_URL = "http://116.62.46.187:10018/GetYBMGoodsByUser";
    public static final String MID_SYN_HYGY_KC_URL = "http://121.40.203.59:9020/GetHYGYAllWithSN";
    public static final String MID_SYN_HYGY_ORDER_URL = "http://121.40.203.59:9020/saveOrderGY";

    public static final String MID_SYN_PGBY_KC_URL = "http://121.40.203.59:9020/GetAllPGBY";

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

    public static List<YZYGOODS> GetHYGYGoods(String userName) throws Exception {
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_HYGY_KC_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
        return yzygoodsList;
    }

    public static List<YZYGOODS> GetPGBY(String userName) throws Exception {
        String param = "userName=" + userName;
        String res = HttpUtils.sendGet(MID_SYN_PGBY_KC_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(res);
        List<YZYGOODS> yzygoodsList = jsonObject.getJSONArray("data").toJavaList(YZYGOODS.class);
        return yzygoodsList;
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
            if(0<ybmGoods.getKcsl_xg()&&ybmGoods.getKcsl_xg()<ybmGoods.getKcsl())
                myGoodsEntity.setSl(ybmGoods.getKcsl_xg());
            else
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
            if(ybmGoods.getKcsl_qg()>ybmGoods.getZbz())
                myGoodsEntity.setZbz(String.valueOf(ybmGoods.getKcsl_qg()));
            else
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
        GetPGBY("HNYZT");
    }
}
