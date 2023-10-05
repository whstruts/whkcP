package hykx.ds.whkc.rabbitmq;


import hykx.ds.whkc.bean.ERPddhz;
import hykx.ds.whkc.bean.ERPddmx;
import hykx.ds.whkc.entity.MyGoodsEntity;
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

    public void insertYZYGOODS(MyGoodsEntity yzygoods){
        if(khzlMapper.getYZYGOODS(yzygoods.getId())>0)
        {
            khzlMapper.updateYZYGOODS(yzygoods);
            khzlMapper.updateYZYGOODSFIX(yzygoods);
        }
        else {
            khzlMapper.insertYZYGOODS(yzygoods);
            khzlMapper.insertYZYGOODS_FIX(yzygoods);
        }
    }

    public void insertYZYGOODS_FIX(MyGoodsEntity yzygoods){
        khzlMapper.insertYZYGOODS_FIX(yzygoods);
    }

    public List<MyGoodsEntity> getYZYGOODS_FIX(String goods_sn){
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
    public void ItoDDHZs(ERPddhz ddhz){
        khzlMapper.insertDDHZ(ddhz);
    }
    public void ItoDDMXs(ERPddmx ddmx){
        khzlMapper.insertDDMX(ddmx);
    }
    public void updateysbddmx(ERPddmx erPddmx){
        khzlMapper.updateysbddmx(erPddmx);
    }

    public void insertysbddmxbak(ERPddmx erPddmx){
        khzlMapper.insertysbddmxbak(erPddmx);
    }

    public void DoERPDD(String kpbh,String cgjhbh,String xsjhbh) {
        khzlMapper.DoERPDD(kpbh,cgjhbh,xsjhbh);
    }

    public void updateERPDD(String kpbh) { khzlMapper.updateERPDD(kpbh);}

}
