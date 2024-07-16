package hykx.ds.whkc.mapper;

import hykx.ds.whkc.bean.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface KhzlMapper {
    @Update("update YZYGOODS set is_on_sale = 0 ")
    void updateYZYGOODSAll();
    @Delete("DELETE FROM YZYGOODS where is_on_sale = 0")
    void deleteYZYGOODSAll();
    @Insert("INSERT INTO YZYGOODS(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,is_sy,ypbh,updatetime) "+
            " VALUES(#{goods_sn},#{goods_name},#{goods_number},#{market_price},#{shop_price_st},#{is_on_sale},#{YPDM},#{CDMC},#{CDDM},#{GG},#{TXM},#{DW},#{JX},"+
            " #{PZWH},#{BZ},#{ZBZ},#{YXQ},#{PH},#{ISRETAIL},#{PCH},#{SCRQ},#{goods_id_s},'0',#{ypbh},GETDATE())")
    void insertYZYGOODS(YZYGOODS yzygoods);

    @Insert("INSERT INTO YZYGOODS_FIX(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,is_sy,ypbh,updatetime) "+
            " VALUES(#{goods_sn},#{goods_name},#{goods_number},#{market_price},#{shop_price_st},#{is_on_sale},#{YPDM},#{CDMC},#{CDDM},#{GG},#{TXM},#{DW},#{JX},"+
            " #{PZWH},#{BZ},#{ZBZ},#{YXQ},#{PH},#{ISRETAIL},#{PCH},#{SCRQ},#{goods_id_s},'0',#{ypbh},GETDATE())")
    void insertYZYGOODS_FIX(YZYGOODS yzygoods);


    @Select("select * from YZYGOODS_FIX where goods_sn = #{goods_sn}")
    public List<YZYGOODS> getYZYGOODS_FIX(String goods_sn);

    @Select("select count(*) from YZYGOODS where goods_id_s = #{goods_id_s}")
    public int getYZYGOODS(String goods_id_s);

    @Update("update YZYGOODS set goods_sn = #{goods_sn},goods_name = #{goods_name},goods_number = #{goods_number},market_price = #{market_price},shop_price = #{shop_price_st},is_on_sale = #{is_on_sale},YPDM = #{YPDM},CDMC = #{CDMC},CDDM = #{CDDM},GG = #{GG},TXM = #{TXM},DW = #{DW},JX = #{JX},PZWH = #{PZWH},BZ = #{BZ},ZBZ = #{ZBZ},YXQ = #{YXQ}, PH = #{PH},ISRETAIL = #{ISRETAIL},PCH = #{PCH},SCRQ = #{SCRQ},ypbh = #{ypbh},updatetime = GETDATE() " +
            "where goods_id_s = #{goods_id_s}")
    public void updateYZYGOODS(YZYGOODS yzygoods);

    @Update("update YZYGOODS_FIX set goods_sn = #{goods_sn},goods_name = #{goods_name},goods_number = #{goods_number},market_price = #{market_price},shop_price = #{shop_price_st},is_on_sale = #{is_on_sale},YPDM = #{YPDM},CDMC = #{CDMC},CDDM = #{CDDM},GG = #{GG},TXM = #{TXM},DW = #{DW},JX = #{JX},PZWH = #{PZWH},BZ = #{BZ},ZBZ = #{ZBZ},YXQ = #{YXQ}, PH = #{PH},ISRETAIL = #{ISRETAIL},PCH = #{PCH},SCRQ = #{SCRQ},ypbh = #{ypbh},updatetime = GETDATE() " +
            "where goods_id_s = #{goods_id_s}")
    public void updateYZYGOODSFIX(YZYGOODS yzygoods);

    @Select("select a.* from ysb_ddhz a,ysb_ddmx b " +
            "where a.djbh = b.djbh and a.is_run = 0 and b.drugCode like 'YSB%' ")
    public List<ysbddhz> getysbddhzs();

    @Select("select * from ysb_ddmx where djbh = #{djbh} ")
    public List<ysbddmx> getysbddmxbydjbh(String djbh);

    @Update("update ysb_ddhz set is_run = 1 where is_run = 0 and djbh = #{djbh}")
     public void updateysbddhz(String djbh);

    @Update("update YZYGOODS set is_on_sale = 0,updatetime = GETDATE() ")
    public void unOnSale();

    @Update({"<script>" +
            "<foreach collection=\"goodsList\" item=\"item\" separator=\";\">" +
            " UPDATE" +
            " YZYGOODS" +
            "  SET goods_number = #{item.goods_number, jdbcType=INTEGER}, " +
            "  shop_price = #{item.shop_price_st, jdbcType=DOUBLE}, " +
            "  is_on_sale = 1, " +
            "  updatetime = GETDATE() " +
            "   where goods_id_s = #{item.goods_id_s,jdbcType=VARCHAR} " +
            "</foreach>" +
            "</script>"})
    void batchUpdate(@Param("goodsList") List<YZYGOODS> goodsList);

    @Update("update a set a.spid = b.erp_id from ysb_ddmx a,YZYGOODS_FIX b where  a.drugCode = b.goods_sn and a.drugCode like 'YSB%' and LEN(b.erp_id)>0 and a.spid is null ")
    void UpdateYSBDDMX();

    @Update("update c set c.erp_id = a.spid from spkfk a,YZYGOODS_FIX b,YZYGOODS_FIX c  where a.yspbh = b.goods_sn and b.ypbh = c.ypbh and c.erp_id is null ")
    void UpdateSPID();

    @Insert({"<script>" +
            "<foreach collection=\"goodsList\" item=\"item\" separator=\";\">" +
            "INSERT INTO YZYGOODS_TMP(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,is_sy,ypbh,updatetime) "+
            " VALUES(#{item.goods_sn},#{item.goods_name},#{item.goods_number},#{item.market_price},#{item.shop_price_st},#{item.is_on_sale},#{item.YPDM},#{item.CDMC},#{item.CDDM},#{item.GG},#{item.TXM},#{item.DW},#{item.JX},"+
            " #{item.PZWH},#{item.BZ},#{item.ZBZ},#{item.YXQ},#{item.PH},#{item.ISRETAIL},#{item.PCH},#{item.SCRQ},#{item.goods_id_s},'0',#{item.ypbh},GETDATE()) "+
            "</foreach>" +
            "</script>"})
    void batchInsert(@Param("goodsList") List<YZYGOODS> goodsList);

    @Delete("DELETE FROM YZYGOODS_TMP")
    void deleteGoodsTmp();

    @Insert("INSERT INTO YZYGOODS(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,spbh,is_sy,ypbh,erp_id,updatetime) " +
            "SELECT goods_sn,goods_name,goods_number,market_price,shop_price,1,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,spbh,is_sy,ypbh,erp_id,updatetime " +
            "FROM YZYGOODS_TMP a WHERE NOT EXISTS (SELECT 1 FROM YZYGOODS b WHERE a.goods_id_s = b.goods_id_s)")
    void insertTMP2YZYGOODS();

    @Insert("INSERT INTO YZYGOODS_FIX(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,spbh,is_sy,ypbh,erp_id,updatetime) " +
            "SELECT goods_sn,goods_name,goods_number,market_price,shop_price,1,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,spbh,is_sy,ypbh,erp_id,updatetime " +
            "FROM YZYGOODS_TMP a WHERE NOT EXISTS (SELECT 1 FROM YZYGOODS_FIX b WHERE a.goods_id_s = b.goods_id_s)")
    void insertTMP2FIX();

    @Update("UPDATE YZYGOODS " +
            "SET YZYGOODS.goods_number = YZYGOODS_TMP.goods_number,YZYGOODS.shop_price = YZYGOODS_TMP.shop_price,YZYGOODS.is_on_sale = 1,YZYGOODS.updatetime = GETDATE() " +
            "FROM YZYGOODS " +
            "INNER JOIN YZYGOODS_TMP " +
            "ON YZYGOODS.goods_id_s = YZYGOODS_TMP.goods_id_s ")
    void updateTMP2YZYGOODS();
}
