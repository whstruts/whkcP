package hykx.ds.whkc.rabbitmq;

import hykx.ds.whkc.bean.ThirdCommodity;
import hykx.ds.whkc.bean.ThirdCustomer;
import hykx.ds.whkc.mapper.ThirdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThirdService {
    @Autowired
    private ThirdMapper thirdMapper;

    public List<ThirdCommodity> getCommodity() {return thirdMapper.getCommodity();}

    public List<ThirdCustomer> getCustomer() {return thirdMapper.getCustomer();}
}
