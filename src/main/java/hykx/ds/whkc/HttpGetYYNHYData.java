package hykx.ds.whkc;
import hykx.ds.whkc.entity.MyGoodsEntity;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianl
 */
public class HttpGetYYNHYData {

    //        private static final String url = "http://www.hbyyn.com:9527/hykx/getspbnewymd";  武汉库商品数据，暂不使用
    //private static final String url = "http://localhost:9527/getHYGoods";
    //private static final String url = "http://www.hbyyn.com:9527/hykx/getHYGoods";
    private static final String url = "http://localhost:9527/getHYGoods";
    //private static final String urlP = "http://www.hbyyn.com:9527/hykx/getHYGoodsP?customNo={customNo}";
    private static final String urlP = "http://localhost:9527/getHYGoodsP?customNo={customNo}";

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(getHYGoodsP("hnhr"));
        System.out.println("使用时长：" + (System.currentTimeMillis() - startTime));
//        String ss = "{\"ypdm\":\"xftejn\",\"cddm\":\"bjlyzyyxgs\",\"jx\":\"胶囊剂\",\"scrq\":\"2019-08-27,2019-08-17\"," +
//                "\"txm\":null,\"goods_id_s\":null,\"drug_common_name\":\"朗依 硝呋太尔胶囊（左通） 自营\",
//                \"manufacturer\":\"北京金城泰尔制药有限公司\"," +
//                "\"approve_number\":\"国药准字H20080299 \",\"recipe_type\":\"\",\"type_code\":\"\",\"dosage_form\":\"\"," +
//                "\"appearance\":\"\",\"bases\":\"\",\"major_functions\":\"\",\"untoward_effect\":\"\",\"taboo\":\"\"," +
//                "\"store\":\"\",\"warnings\":\"\",\"drug_interactions\":\"\",\"brand\":\"\"," +
//                "\"drug_img\":\"images/201910/goods_img/773552_G_1570646208242.jpg\",\"specifications\":\"0.1g*28s\"," +
//                "\"package_unit\":\"盒\",\"medium_package\":\"10\",\"large_package\":\"400\",\"usage_dosage\":\"\"," +
//                "\"is_retail\":\"1\",\"production_batch\":\"190810,190808\",\"date_expiration\":\"2021-07-31,2021-07-31\"," +
//                "\"repertory\":\"780\",\"supplier\":\"\",\"left_view\":\"\",\"right_view\":\"\",\"bar_code\":null," +
//                "\"unpack_view\":\"\",\"specification_view\":\"\",\"supplier_price\":\"23.00\",\"drugid\":\"773552\"}";
//        CommodityModelDTO on = JSONObject.parseObject(ss, CommodityModelDTO.class);
//        System.out.println(ss);
    }

    public static List<MyGoodsEntity> getHYGoods() {
        //复杂构造函数的使用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000 * 60 * 30);// 设置超时
        requestFactory.setReadTimeout(1000 * 60 * 30);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String body = response.getBody();

        List<MyGoodsEntity> list = JSONObject.parseArray(body, MyGoodsEntity.class);
        return list;
    }

    public static List<MyGoodsEntity> getHYGoodsP(String customNo) {
        //复杂构造函数的使用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000 * 60 * 30);// 设置超时
        requestFactory.setReadTimeout(1000 * 60 * 30);
        Map<String, String> params = new HashMap<>();
        params.put("customNo", customNo);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        ResponseEntity<String> response = restTemplate.getForEntity(urlP, String.class,params);

        String body = response.getBody();

        List<MyGoodsEntity> list = JSONObject.parseArray(body, MyGoodsEntity.class);
        return list;
    }


//    public static void test1() {
//        // 2、创建httpGet请求，设置url访问地址
//        // 获取连接客户端工具
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        String entityStr = null;
//        CloseableHttpResponse response = null;
//        try {
//            // 3、使用httpClient发起请求，获取response
//            URIBuilder uriBuilder = new URIBuilder("http://www.hbyyn.com:9527/hykx/getspbnewymd");
//            HttpGet httpGet = new HttpGet(uriBuilder.build());
//            httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0
//            .8," +
//                    "application/signed-exchange;v=b3");
//            httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
//            httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
//            httpGet.addHeader("Connection", "keep-alive");
//            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)
//            " +
//                    "Chrome/75.0.3770.100 Safari/537.36");
//
//            // 执行请求
//            response = httpClient.execute(httpGet);
//            // 获得响应的实体对象
//            HttpEntity entity = response.getEntity();
//            // 使用Apache提供的工具类进行转换成字符串
//            entityStr = EntityUtils.toString(entity, "UTF-8");
//            System.out.println(entityStr);
//            JSONArray array = JSONArray.parseArray(entityStr);
//            for (Object o : array) {
//                System.out.println(o);
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        // 4、解析响应
//        catch (URISyntaxException e) {
//            e.printStackTrace();
//        } finally {
//            // 释放连接
//            if (null != response) {
//                try {
//                    response.close();
//                    httpClient.close();
//                } catch (IOException e) {
//                    System.err.println("释放连接出错");
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
