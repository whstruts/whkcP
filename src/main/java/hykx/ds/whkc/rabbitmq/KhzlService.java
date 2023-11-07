package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.*;
import hykx.ds.whkc.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhzlService {
    @Autowired
    private KhzlMapper khzlMapper;

    public void updateYZYGOODS(){
        khzlMapper.updateYZYGoodsList();
    }

    public void deleteYZYGOODS(){
        khzlMapper.deleteYZYGoodsList();
    }

    public void insertYZYGOODS(YZYGOODS yzygoods){
        if(khzlMapper.getYZYGOODS(yzygoods.getGoods_id_s())>0)
        {
            khzlMapper.updateYZYGOODS(yzygoods);
            khzlMapper.updateYZYGOODSFIX(yzygoods);
        }
        else
            khzlMapper.insertYZYGOODS(yzygoods);
    }

    public void insertYZYGOODS_FIX(YZYGOODS yzygoods){
        khzlMapper.insertYZYGOODS_FIX(yzygoods);
    }

    public List<YZYGOODS> getYZYGOODS_FIX(String goods_sn){
        return khzlMapper.getYZYGOODS_FIX(goods_sn);
    }


    public void updateysbddhz(String djbh) { khzlMapper.updateysbddhz(djbh);}
    public void updatexyyddhz(String order_no) { khzlMapper.updatexyyddhz(order_no);}

    public List<ysbddhz> getysbddhzs() {
        return  khzlMapper.getysbddhzs();
    }

    public List<xyyddhz> getxyyddhzs() {
        return  khzlMapper.getxyyddhzs();
    }

    public List<ysbddmx> getysbddmxbydjbh(String djbh) {
        return  khzlMapper.getysbddmxbydjbh(djbh);
    }

    public List<xyyddmx> getxyyddmxbyOrderNo(String order_no) {
        return  khzlMapper.getxyyddmxbyOrderNo(order_no);
    }

    public void unOnSale() { khzlMapper.unOnSale();}

    public void batchUpdate(List<YZYGOODS> list){
        khzlMapper.batchUpdate(list);
    }

}
