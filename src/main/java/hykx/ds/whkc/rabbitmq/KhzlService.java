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

    public void batchInsert(List<YZYGOODS> list){
        khzlMapper.batchInsert(list);
    }

    public void deleteGoodsTmp(){
        khzlMapper.deleteGoodsTmp();
    }

    public void insertTMP2YZYGOODS(){
        khzlMapper.insertTMP2YZYGOODS();
    }
    public void insertTMP2FIX(){
        khzlMapper.insertTMP2FIX();
    }
    public void updateTMP2YZYGOODS(){
        khzlMapper.updateTMP2YZYGOODS();
    }

    public void unOnSale() { khzlMapper.unOnSale();}

}
