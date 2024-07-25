package hykx.ds.whkc.mapper;

import hykx.ds.whkc.entity.MyGoodsEntity;
import hykx.ds.whkc.entity.erpsp;
import hykx.ds.whkc.entity.ysbddhz;
import hykx.ds.whkc.entity.ysbddmx;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface KhzlMapper {
    @Insert("INSERT INTO hydeeif.yzygoods(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,CDMC,GG,DW,JX,PZWH,BZ,ZBZ,YXQ,ISRETAIL,PCH,SCRQ,goods_id_s,ypbh,updatetime) "+
            " VALUES(#{id},#{ypmc},#{sl},#{lsj},#{dj},1,#{cdmc},#{gg},#{dw},#{jx},"+
            " #{pzwh},#{bz},#{zbz},#{yxq},#{isretail},#{pch},#{scrq},#{id},#{ypbh},sysdate)")
    void insertYZYGOODS(MyGoodsEntity yzygoods);

    @Insert("INSERT INTO hydeeif.yzygoods_fix(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,CDMC,GG,DW,JX,PZWH,BZ,ZBZ,YXQ,ISRETAIL,PCH,SCRQ,goods_id_s,ypbh,updatetime) "+
            " VALUES(#{id},#{ypmc},#{sl},#{lsj},#{dj},1,#{cdmc},#{gg},#{dw},#{jx},"+
            " #{pzwh},#{bz},#{zbz},#{yxq},#{isretail},#{pch},#{scrq},#{id},#{ypbh},sysdate)")
    void insertYZYGOODS_FIX(MyGoodsEntity yzygoods);


    @Select("select count(*) from hydeeif.yzygoods where goods_id_s = #{goods_id_s}")
    public int getYZYGOODS(String goods_id_s);

    @Update("update hydeeif.yzygoods set goods_sn = #{id},goods_name = #{ypmc},goods_number = #{sl},market_price = #{lsj},shop_price = #{dj},is_on_sale = 1,CDMC = #{cdmc},GG = #{gg},DW = #{dw},JX = #{jx},PZWH = #{pzwh},BZ = #{bz},ZBZ = #{zbz},YXQ = #{yxq}, ISRETAIL = #{isretail},PCH = #{pch},SCRQ = #{scrq},ypbh = #{ypbh},updatetime = sysdate " +
            "where goods_id_s = #{id}")
    public void updateYZYGOODS(MyGoodsEntity yzygoods);

    @Update("update hydeeif.yzygoods_fix set goods_sn = #{id},goods_name = #{ypmc},goods_number = #{sl},market_price = #{lsj},shop_price = #{dj},is_on_sale = 1,CDMC = #{cdmc},GG = #{gg},DW = #{dw},JX = #{jx},PZWH = #{pzwh},BZ = #{bz},ZBZ = #{zbz},YXQ = #{yxq}, ISRETAIL = #{isretail},PCH = #{pch},SCRQ = #{scrq},ypbh = #{ypbh},updatetime = sysdate  " +
            "where goods_id_s = #{id}")
    public void updateYZYGOODSFIX(MyGoodsEntity yzygoods);

    @Select("select * from hydeeif.ysb_ddhz where is_run_hy = 0 ")
    public List<ysbddhz> getysbddhzs();

    @Select("select a.*,b.wareqty,c.batchnum,c.validity,c.proddate,d.setwhlprice1" +
            " from h2.v_ysb_ware a,h2.v_ysb_wareqty b," +
            " (select warecode as drugCode,min(makeno) as batchNum," +
            " nvl(to_char(min(invalidate),'yyyy-mm-dd'),'') as validity," +
            " nvl(to_char(min(makedate),'yyyy-mm-dd'),'') as prodDate" +
            " from H2.v_Ysb_Ware_Store_i" +
            " group by warecode) c,h2.v_ysb_ware_whlprice d" +
            " where a.drugcode = b.WARECODE and a.drugcode = c.drugcode and a.drugcode = d.warecode")
    public List<erpsp> getERPSP();

    @Select("select * from hydeeif.ysb_ddmx where djbh = #{djbh} ")
    public List<ysbddmx> getysbddmxbydjbh(String djbh);

    @Update("update hydeeif.ysb_ddhz set is_run_hy = 1 where is_run_hy = 0 and djbh = #{djbh}")
     public void updateysbddhz(String djbh);


    @Update("update hydeeif.yzygoods set is_on_sale = 0,updatetime = sysdate ")
    public void unOnSale();

}
