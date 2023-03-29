package hykx.ds.whkc.mapper;
import hykx.ds.whkc.bean.ysbddhz;
import hykx.ds.whkc.bean.ysbddmx;
import org.apache.ibatis.annotations.Update;

public interface ysbddMapper {

    @Update("update ysb_ddmx set cg_dj = #{cgdj},cg_je = #{cgje} where djbh = #{djbh} and drugcode = #{drugcode}")
    public void updateddmx(ysbddmx ddmx);

    @Update("update ysb_ddhz set status = '已锁定' where djbh = #{djbh} ")
    public void updateddhz(ysbddhz ddhz);

}
