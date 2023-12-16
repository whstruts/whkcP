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
        //khzlMapper.updateYZYGOODSAll();
    }

    public void deleteYZYGOODS(){
        khzlMapper.deleteYZYGOODSAll();
    }

    public void insertYZYGOODS(YZYGOODS yzygoods){
        if(khzlMapper.getYZYGOODS(yzygoods.getGoods_id_s())>0)
            khzlMapper.updateYZYGOODS(yzygoods);
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

    public void updatexyyddhz(String djbh) { khzlMapper.updatexyyddhz(djbh);}

    public List<ysbddhz> getysbddhzs() {
        return  khzlMapper.getysbddhzs();
    }
    public List<ysbddhz> getxyyddhzs() {
        return  khzlMapper.getxyyddhzs();
    }

    public List<ysbddmx> getysbddmxbydjbh(String djbh) {
        return  khzlMapper.getysbddmxbydjbh(djbh);
    }

    public List<ysbddmx> getxyyddmxbydjbh(String djbh) {
        return  khzlMapper.getxyyddmxbydjbh(djbh);
    }

    public void unOnSale() { khzlMapper.unOnSale();}

    public void batchUpdate(List<YZYGOODS> list){
        khzlMapper.batchUpdate(list);
    }

    public void UpdateYSBDDMX() { khzlMapper.UpdateYSBDDMX();}

}
