package hykx.ds.whkc.rabbitmq;


import hykx.ds.whkc.entity.YZYGOODS;
import hykx.ds.whkc.entity.ysbddhz;
import hykx.ds.whkc.entity.ysbddmx;
import hykx.ds.whkc.mapper.KhzlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhzlService {
    @Autowired
    private KhzlMapper khzlMapper;

//    public void updateYZYGOODS(){
//        khzlMapper.updateYZYGOODSAll();
//    }
//
//    public void deleteYZYGOODS(){
//        khzlMapper.deleteYZYGOODSAll();
//    }

    public void insertYZYGOODS(YZYGOODS yzygoods){
        if(khzlMapper.getYZYGOODS(yzygoods.getGoods_sn())>0)
        {
            khzlMapper.updateYZYGOODS(yzygoods);
            khzlMapper.updateYZYGOODSFIX(yzygoods);
            System.out.println("更新中台数据:"+yzygoods.toString());
        }
        else {
            khzlMapper.insertYZYGOODS(yzygoods);
            khzlMapper.insertYZYGOODS_FIX(yzygoods);
            System.out.println("写入中台数据:"+yzygoods.toString());
        }
    }

    public void insertYZYGOODS_FIX(YZYGOODS yzygoods){
        khzlMapper.insertYZYGOODS_FIX(yzygoods);
    }

    public List<YZYGOODS> getYZYGOODS_FIX(String goods_sn){
        return khzlMapper.getYZYGOODS_FIX(goods_sn);
    }


    public void updateysbddhz(String djbh) { khzlMapper.updateysbddhz(djbh);}

    public List<ysbddhz> getysbddhzs() {
        return  khzlMapper.getysbddhzs();
    }

    public List<ysbddmx> getysbddmxbydjbh(String djbh) {
        return  khzlMapper.getysbddmxbydjbh(djbh);
    }

    public void unOnSale() { khzlMapper.unOnSale();}

    public void batchUpdate(List<YZYGOODS> list){
      //  khzlMapper.batchUpdate(list);
    }

    public void updateysbddhz2(ysbddhz hz) { khzlMapper.updateysbddhz2(hz);}
    public void updateysbddmx(ysbddmx mx) { khzlMapper.updateysbddmx(mx);}

    public void updateNotHYOrder() {khzlMapper.updateNotHYOrder();}


    public void deleteYZYGOODSP(){
        khzlMapper.deleteYZYGOODSAllP();
    }
    public void insertYZYGOODSP(YZYGOODS yzygoods){
        khzlMapper.insertYZYGOODSP(yzygoods);
    }
}
