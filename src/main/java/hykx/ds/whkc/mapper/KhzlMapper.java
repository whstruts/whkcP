package hykx.ds.whkc.mapper;

import org.apache.ibatis.annotations.Select;

public interface KhzlMapper {

    @Select("call ysb_kh2erp()")
    public void ysb_kh2erp();

    @Select("call ysb_kh2erp_x()")
    public void ysb_kh2erp_x();

}
