package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.*;
import hykx.ds.whkc.mapper.KhzlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class KhzlService {
    @Autowired
    private KhzlMapper khzlMapper;
    public List<Khzl> getKH() {
        return  khzlMapper.getKH();
    }

    public List<Ddzt> getDD() {
        return  khzlMapper.getDD();
    }

    public List<SpKC> getKC() {
        return  khzlMapper.getKC();
    }
    public List<Spzl> getSP() { return khzlMapper.getSP();}
    public void ItoDDHZs(ERPddhz ddhz){
        khzlMapper.insertDDHZ(ddhz);
    }
    public void ItoDDMXs(ERPddmx ddmx){
        khzlMapper.insertDDMX(ddmx);
    }

    public List<ERPddhz> getERPDD() {
        return  khzlMapper.getERPDD();
    }

    public List<DZFP> getdzfp() {
        return  khzlMapper.getdzfp();
    }

    public void DoERPDD(String kpbh,String cgjhbh,String xsjhbh) {
        khzlMapper.DoERPDD(kpbh,cgjhbh,xsjhbh);
    }

    public void updateERPDD(String kpbh) { khzlMapper.updateERPDD(kpbh);}

    public void ItoYSBKH(ysbkh ysbkh){
        khzlMapper.insertYSBKH(ysbkh);
    }

    public void ysb_kh2erp() {
        khzlMapper.ysb_kh2erp();
    }
    public void ysb_kh2erp_x() {
        khzlMapper.ysb_kh2erp_x();
    }
}
