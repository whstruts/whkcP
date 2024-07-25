package hykx.ds.whkc.rabbitmq;


import hykx.ds.whkc.bean.ERPddhz;
import hykx.ds.whkc.bean.ERPddmx;
import hykx.ds.whkc.entity.*;
import hykx.ds.whkc.mapper.KhzlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhzlService {
    @Autowired
    private KhzlMapper khzlMapper;


    public void insertYZYGOODS(MyGoodsEntity myGoodsEntity){
        if(khzlMapper.getYZYGOODS(myGoodsEntity.getId())>0)
        {
            khzlMapper.updateYZYGOODS(myGoodsEntity);
            khzlMapper.updateYZYGOODSFIX(myGoodsEntity);
        }
        else {
            khzlMapper.insertYZYGOODS(myGoodsEntity);
            khzlMapper.insertYZYGOODS_FIX(myGoodsEntity);
        }
    }

    public void updateysbddhz(String djbh) { khzlMapper.updateysbddhz(djbh);}

    public List<ysbddhz> getysbddhzs() {
        return  khzlMapper.getysbddhzs();
    }

    public List<erpsp> getERPSP() {
        return  khzlMapper.getERPSP();
    }

    public List<ysbddmx> getysbddmxbydjbh(String djbh) {
        return  khzlMapper.getysbddmxbydjbh(djbh);
    }

    public void unOnSale() { khzlMapper.unOnSale();}

}
