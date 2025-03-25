package hykx.ds.whkc.mapper;

import hykx.ds.whkc.entity.*;
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


    @Insert("INSERT INTO hydeeif.YZYGOODS(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,ypbh,updatetime) "+
            " VALUES(#{goods_sn},#{goods_name},#{goods_number},#{market_price},#{shop_price},1,#{YPDM},#{CDMC},#{CDDM},#{GG},#{TXM},#{DW},#{JX},"+
            " #{PZWH},#{BZ},#{ZBZ},#{YXQ},#{PH},#{ISRETAIL},#{PCH},#{SCRQ},#{goods_id_s},#{ypbh},sysdate)")
    void insertYZYGOODSGY(YZYGOODS yzygoods);

    @Insert("INSERT INTO hydeeif.YZYGOODS_FIX(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,ypbh,updatetime) "+
            " VALUES(#{goods_sn},#{goods_name},#{goods_number},#{market_price},#{shop_price},1,#{YPDM},#{CDMC},#{CDDM},#{GG},#{TXM},#{DW},#{JX},"+
            " #{PZWH},#{BZ},#{ZBZ},#{YXQ},#{PH},#{ISRETAIL},#{PCH},#{SCRQ},#{goods_id_s},#{ypbh},sysdate)")
    void insertYZYGOODS_FIXGY(YZYGOODS yzygoods);


    @Select("select * from hydeeif.YZYGOODS_FIX where goods_sn = #{goods_sn}")
    public List<YZYGOODS> getYZYGOODS_FIXGY(String goods_sn);

    @Select("select count(*) from hydeeif.YZYGOODS where goods_sn = #{goods_sn}")
    public int getYZYGOODSGY(String goods_id_s);

    @Update("update hydeeif.YZYGOODS set goods_id_s = #{goods_id_s},goods_name = #{goods_name},goods_number = #{goods_number},market_price = #{market_price},shop_price = #{shop_price},is_on_sale = 1,YPDM = #{YPDM},CDMC = #{CDMC},CDDM = #{CDDM},GG = #{GG},TXM = #{TXM},DW = #{DW},JX = #{JX},PZWH = #{PZWH},BZ = #{BZ},ZBZ = #{ZBZ},YXQ = #{YXQ}, PH = #{PH},ISRETAIL = #{ISRETAIL},PCH = #{PCH},SCRQ = #{SCRQ},ypbh = #{ypbh},updatetime = sysdate " +
            "where goods_sn = #{goods_sn}")
    public void updateYZYGOODSGY(YZYGOODS yzygoods);

    @Update("update hydeeif.YZYGOODS_FIX set goods_id_s = #{goods_id_s},goods_name = #{goods_name},goods_number = #{goods_number},market_price = #{market_price},shop_price = #{shop_price},is_on_sale = 1,YPDM = #{YPDM},CDMC = #{CDMC},CDDM = #{CDDM},GG = #{GG},TXM = #{TXM},DW = #{DW},JX = #{JX},PZWH = #{PZWH},BZ = #{BZ},ZBZ = #{ZBZ},YXQ = #{YXQ}, PH = #{PH},ISRETAIL = #{ISRETAIL},PCH = #{PCH},SCRQ = #{SCRQ},ypbh = #{ypbh},updatetime = sysdate " +
            "where goods_sn = #{goods_sn}")
    public void updateYZYGOODSFIXGY(YZYGOODS yzygoods);

    @Update("update hydeeif.ysb_ddmx set cg_dj = #{cgdj},cg_je = #{cgje} where djbh = #{djbh} and drugcode = #{drugcode}")
    public void updateddmx(ysbddmx ddmx);

    @Update("update hydeeif.ysb_ddhz set status = '已锁定' where djbh = #{djbh} ")
    public void updateddhz(ysbddhz ddhz);






    @Select("select * from hydeeif.ysb_ddhz where is_run_hy = 0 ")
    public List<ysbddhz> getysbddhzs();

    @Select("select a.*,b.wareqty,c.batchnum,c.validity,c.proddate,c.ownername,d.setwhlprice1" +
            "             from h2.v_ysb_ware a,h2.v_ysb_wareqty b," +
            "             (select warecode as drugCode,min(makeno) as batchNum," +
            "             nvl(to_char(min(invalidate),'yyyy-mm-dd'),'') as validity," +
            "             nvl(to_char(min(makedate),'yyyy-mm-dd'),'') as prodDate," +
            "             min(ownername) as ownername" +
            "             from H2.v_Ysb_Ware_Store_i" +
            "             group by warecode) c,h2.v_ysb_ware_whlprice d" +
            "             where a.drugcode = b.WARECODE and a.drugcode = c.drugcode and a.drugcode = d.warecode")
    public List<erpsp> getERPSP();

    @Select("select b.goods_id_s as hy_id,a.* from hydeeif.ysb_ddmx a,hydeeif.yzygoods b where a.djbh = #{djbh} and a.drugcode = b.goods_sn" +
            "union " +
            "select b.goods_id_s as hy_id,a.* from hydeeif.ysb_ddmx a,hydeeif.yzygoods_p b where a.djbh = #{djbh} and a.drugcode = b.goods_sn")
    public List<ysbddmx> getysbddmxbydjbh(String djbh);

    @Update("update hydeeif.ysb_ddhz set is_run_hy = 1 where is_run_hy = 0 and djbh = #{djbh}")
     public void updateysbddhz(String djbh);


    @Update("update hydeeif.yzygoods set is_on_sale = 0,updatetime = sysdate ")
    public void unOnSale();


    @Select("select count(*) from hydeeif.ysb_ddhz where djbh = #{djbh}")
    public int getDDByBH(String djbh);

    @Insert("INSERT INTO hydeeif.ysb_ddhz(djbh,rq,ontime,customerid,status,je,is_zx,is_run_hy) "+
            " VALUES(#{djbh},#{rq},#{ontime},#{customerId},#{status},#{je},#{is_zx},1)")
    void insertYSBDDHZ(ysbddhz ddhz);

    @Insert("INSERT INTO hydeeif.ysb_ddmx(djbh,dj_sn,drugcode,shl,dj,je,is_zx,cg_dj,cg_je) "+
            " VALUES(#{djbh},#{dj_sn},#{drugcode},#{shl},#{dj},#{je},#{is_zx},#{cgdj},#{cgje})")
    void insertYSBDDMX(ysbddmx ddmx);

    @Delete("DELETE FROM hydeeif.yzygoods_p")
    void deleteYZYGOODSAllP();

    @Insert("INSERT INTO hydeeif.yzygoods_p(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,is_sy,ypbh,updatetime) "+
            " VALUES(#{goods_sn},#{goods_name},#{goods_number},#{market_price},#{shop_price},#{is_on_sale},#{YPDM},#{CDMC},#{CDDM},#{GG},#{TXM},#{DW},#{JX},"+
            " #{PZWH},#{BZ},#{ZBZ},#{YXQ},#{PH},#{ISRETAIL},#{PCH},#{SCRQ},#{goods_id_s},'0',#{ypbh},sysdate)")
    void insertYZYGOODSP(YZYGOODS yzygoods);

}
