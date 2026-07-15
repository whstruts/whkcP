package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.ERPddhz;
import hykx.ds.whkc.bean.ERPddmx;
import hykx.ds.whkc.entity.*;
import hykx.ds.whkc.mapper.KhzlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KhzlService {
    @Autowired
    private KhzlMapper khzlMapper;


    public void insertYZYGOODS(MyGoodsEntity myGoodsEntity){
        try {
            log.debug("insertYZYGOODS - 开始检查商品: {}", myGoodsEntity.getId());
            long startTime = System.currentTimeMillis();
            
            int count = khzlMapper.getYZYGOODS(myGoodsEntity.getId());
            log.debug("insertYZYGOODS - 查询结果: count={}, 耗时: {}ms", count, System.currentTimeMillis() - startTime);
            
            if(count > 0) {
                log.debug("insertYZYGOODS - 更新商品: {}", myGoodsEntity.getId());
                khzlMapper.updateYZYGOODS(myGoodsEntity);
                khzlMapper.updateYZYGOODSFIX(myGoodsEntity);
                log.debug("insertYZYGOODS - 更新完成: {}", myGoodsEntity.getId());
            }
            else {
                log.debug("insertYZYGOODS - 插入商品: {}", myGoodsEntity.getId());
                khzlMapper.insertYZYGOODS(myGoodsEntity);
                khzlMapper.insertYZYGOODS_FIX(myGoodsEntity);
                log.debug("insertYZYGOODS - 插入完成: {}", myGoodsEntity.getId());
            }
        } catch (Exception e) {
            log.error("insertYZYGOODS - 异常: {}, 商品ID: {}, 错误信息: {}", 
                      e.getClass().getName(), myGoodsEntity.getId(), e.getMessage());
            throw e;
        }
    }

    public void insertYZYGOODSGY(YZYGOODS myGoodsEntity){
        if(khzlMapper.getYZYGOODSGY(myGoodsEntity.getGoods_sn())>0)
        {
            khzlMapper.updateYZYGOODSGY(myGoodsEntity);
            khzlMapper.updateYZYGOODSFIXGY(myGoodsEntity);
        }
        else {
            khzlMapper.insertYZYGOODSGY(myGoodsEntity);
            khzlMapper.insertYZYGOODS_FIXGY(myGoodsEntity);
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

    public void updateddmx(ysbddmx mx){khzlMapper.updateddmx(mx);}
    public void updateddhz(ysbddhz hz){khzlMapper.updateddhz(hz);}

    public int getDDByBH(String djbh){return khzlMapper.getDDByBH(djbh);}

    public int getMX(ysbddmx ddmx){return khzlMapper.getMX(ddmx);}

    public void insertYSBDDHZ(ysbddhz ddhz){khzlMapper.insertYSBDDHZ(ddhz);}

    public void insertYSBDDMX(ysbddmx ddmx){khzlMapper.insertYSBDDMX(ddmx);}

    public void deleteYZYGOODSP(){
        khzlMapper.deleteYZYGOODSAllP();
    }
    public void insertYZYGOODSP(YZYGOODS yzygoods){
        khzlMapper.insertYZYGOODSP(yzygoods);
    }

    public void insertYZYGOODSPGBY(YZYGOODS myGoodsEntity){
        if(khzlMapper.getYZYGOODSGY(myGoodsEntity.getGoods_sn())>0)
        {
            khzlMapper.updateYZYGOODSGY(myGoodsEntity);
            khzlMapper.updateYZYGOODSFIXGY(myGoodsEntity);
        }
        else {
            khzlMapper.insertYZYGOODSPGBY(myGoodsEntity);
            khzlMapper.insertYZYGOODS_FIXPGBY(myGoodsEntity);
        }
    }

}