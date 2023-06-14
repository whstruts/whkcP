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

    public void ysb_kh2erp() {
        khzlMapper.ysb_kh2erp();
    }
    public void ysb_kh2erp_x() {
        khzlMapper.ysb_kh2erp_x();
    }

    public void updateYZYGOODS(){
        khzlMapper.updateYZYGOODSAll();
    }

    public void deleteYZYGOODS(){
        khzlMapper.deleteYZYGOODSAll();
    }

    public void insertYZYGOODS(YZYGOODS yzygoods){
        if(khzlMapper.getYZYGOODS(yzygoods.getGoods_id_s())>0)
        {
            khzlMapper.updateYZYGOODS(yzygoods);
            khzlMapper.updateYZYGOODSFIX(yzygoods);
        }
        else {
            khzlMapper.insertYZYGOODS(yzygoods);
            khzlMapper.insertYZYGOODS_FIX(yzygoods);
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


}
