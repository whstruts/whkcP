package hykx.ds.whkc.rabbitmq;


import hykx.ds.whkc.mapper.KhzlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
