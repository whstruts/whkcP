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
        khzlMapper.updateYZYGOODS();
    }

    public void deleteYZYGOODS(){
        khzlMapper.deleteYZYGOODS();
    }

    public void insertYZYGOODS(YZYGOODS yzygoods){
        khzlMapper.insertYZYGOODS(yzygoods);
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
}
