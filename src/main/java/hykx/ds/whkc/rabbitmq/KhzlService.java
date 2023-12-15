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


    public void insertYZYGOODS(MyGoodsEntity yzygoods){
        try {
            if (khzlMapper.getYZYGOODS(yzygoods.getId()) > 0) {
                khzlMapper.updateYZYGOODS(yzygoods);
            } else {
                khzlMapper.insertYZYGOODS(yzygoods);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString()+yzygoods.toString());
        }
    }

    public void insertYZYGOODSP(MyGoodsEntity yzygoods){
        try {
            if (khzlMapper.getYZYGOODSP(yzygoods.getYpbh()) > 0) {
                khzlMapper.updateYZYGOODSP(yzygoods);
            } else {
                khzlMapper.insertYZYGOODSP(yzygoods);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString()+yzygoods.toString());
        }
    }

    public void updateysbddhz(String djbh) { khzlMapper.updateysbddhz(djbh);}

    public List<ysbddhz> getysbddhzs() {
        return  khzlMapper.getysbddhzs();
    }

    public List<ysbddmx> getysbddmxbydjbh(String djbh) {
        return  khzlMapper.getysbddmxbydjbh(djbh);
    }

    public void unOnSale() { khzlMapper.unOnSale();}

    public void updateysbddmx(ERPddmx erPddmx){
        khzlMapper.updateysbddmx(erPddmx);
    }

    public void updateGWBH() { khzlMapper.updateGWBH();}

}
