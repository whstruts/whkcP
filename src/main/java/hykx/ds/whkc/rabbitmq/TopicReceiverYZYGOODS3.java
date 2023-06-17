//package hykx.ds.whkc.rabbitmq;
//
//import hykx.ds.whkc.bean.YZYGOODS;
//import lombok.extern.slf4j.Slf4j;
//import net.sf.json.JSONArray;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.List;
//
//@Slf4j
//@Component
//@RabbitListener(queues = "topic.JXTYGoods")
//public class TopicReceiverYZYGOODS3 {
//    @Autowired
//    private KhzlService khzlService;
//    @RabbitHandler
//    public void process(String message) throws IOException
//    {
//        int i_pos;
//        int i_pos2;
//        String s_json;
//        YZYGOODS yzygoods = new YZYGOODS();
//        i_pos = message.indexOf("DELETEYZYGOODS");
//        i_pos2 = message.indexOf("UPDATEYZYGOODS");
//        if(i_pos>0){
//            khzlService.deleteYZYGOODS();
//            System.out.println("接收者 message," + message);
//        }else if(i_pos2>0){
//            khzlService.updateYZYGOODS();
//            System.out.println("接收者 message," + message);
//        }
//        else {
//            try {
//                i_pos = message.indexOf("[");
//                s_json = message.substring(i_pos);
//                List<YZYGOODS> goodsList = JSONArray.fromObject(s_json);
//                khzlService.batchUpdate(goodsList);
//            }
//            catch (Exception e)
//            {
//                System.out.println(e.toString());
//                log.error("batchUpdate", e);
//            }
//        }
//    }
//}
