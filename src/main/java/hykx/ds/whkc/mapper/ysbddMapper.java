package hykx.ds.whkc.mapper;
import hykx.ds.whkc.bean.ysbddmx;
import org.apache.ibatis.annotations.Update;

public interface ysbddMapper {

    @Update("update ysb_ddmx set cgdj = #{cgdj},cgje = #{cgje} where djbh = #{djbh} and drugcode = #{drugcode}")
    public void updateddmx(ysbddmx ddmx);

}
