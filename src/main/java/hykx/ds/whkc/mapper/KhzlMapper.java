package hykx.ds.whkc.mapper;

import hykx.ds.whkc.bean.ERPddhz;
import hykx.ds.whkc.bean.ERPddmx;
import hykx.ds.whkc.entity.YZYGOODS;
import hykx.ds.whkc.entity.ysbddhz;
import hykx.ds.whkc.entity.ysbddmx;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface KhzlMapper {

    @Select("call ysb_kh2erp()")
    public void ysb_kh2erp();

    @Select("call ysb_kh2erp_x()")
    public void ysb_kh2erp_x();
    @Update("update jk_hy_kc set is_on_sale = 0 ")
    void updateYZYGOODSAll();
    @Delete("DELETE FROM jk_hy_kc where is_on_sale = 0")
    void deleteYZYGOODSAll();
    @Insert("INSERT INTO jk_hy_kc(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,ypbh,updatetime) "+
            " VALUES(#{goods_sn},#{goods_name},#{goods_number},#{market_price},#{shop_price_st},1,#{YPDM},#{CDMC},#{CDDM},#{GG},#{TXM},#{DW},#{JX},"+
            " #{PZWH},#{BZ},#{ZBZ},#{YXQ},#{PH},#{ISRETAIL},#{PCH},#{SCRQ},#{goods_id_s},#{ypbh},sysdate)")
    void insertYZYGOODS(YZYGOODS yzygoods);

    @Insert("INSERT INTO jk_hy_kc_fix(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s,ypbh,updatetime) "+
            " VALUES(#{goods_sn},#{goods_name},#{goods_number},#{market_price},#{shop_price_st},1,#{YPDM},#{CDMC},#{CDDM},#{GG},#{TXM},#{DW},#{JX},"+
            " #{PZWH},#{BZ},#{ZBZ},#{YXQ},#{PH},#{ISRETAIL},#{PCH},#{SCRQ},#{goods_id_s},#{ypbh},sysdate)")
    void insertYZYGOODS_FIX(YZYGOODS yzygoods);


    @Select("select * from jk_hy_kc_fix where goods_sn = #{goods_sn}")
    public List<YZYGOODS> getYZYGOODS_FIX(String goods_sn);

    @Select("select count(*) from jk_hy_kc where goods_id_s = #{goods_id_s}")
    public int getYZYGOODS(String goods_id_s);

    @Update("update jk_hy_kc set goods_sn = #{goods_sn},goods_name = #{goods_name},goods_number = #{goods_number},market_price = #{market_price},shop_price = #{shop_price_st},is_on_sale = 1,YPDM = #{YPDM},CDMC = #{CDMC},CDDM = #{CDDM},GG = #{GG},TXM = #{TXM},DW = #{DW},JX = #{JX},PZWH = #{PZWH},BZ = #{BZ},ZBZ = #{ZBZ},YXQ = #{YXQ}, PH = #{PH},ISRETAIL = #{ISRETAIL},PCH = #{PCH},SCRQ = #{SCRQ},ypbh = #{ypbh},updatetime = sysdate " +
            "where goods_id_s = #{goods_id_s}")
    public void updateYZYGOODS(YZYGOODS yzygoods);

    @Update("update jk_hy_kc_fix set goods_sn = #{goods_sn},goods_name = #{goods_name},goods_number = #{goods_number},market_price = #{market_price},shop_price = #{shop_price_st},is_on_sale = 1,YPDM = #{YPDM},CDMC = #{CDMC},CDDM = #{CDDM},GG = #{GG},TXM = #{TXM},DW = #{DW},JX = #{JX},PZWH = #{PZWH},BZ = #{BZ},ZBZ = #{ZBZ},YXQ = #{YXQ}, PH = #{PH},ISRETAIL = #{ISRETAIL},PCH = #{PCH},SCRQ = #{SCRQ},ypbh = #{ypbh},updatetime = sysdate " +
            "where goods_id_s = #{goods_id_s}")
    public void updateYZYGOODSFIX(YZYGOODS yzygoods);

    @Select("select * from ysb_ddhz where is_run_hy = 0 ")
    public List<ysbddhz> getysbddhzs();

    @Select("select * from ysb_ddmx where djbh = #{djbh} ")
    public List<ysbddmx> getysbddmxbydjbh(String djbh);

    @Update("update ysb_ddhz set is_run_hy = 1 where is_run_hy = 0 and djbh = #{djbh}")
     public void updateysbddhz(String djbh);

    @Update("update ysb_ddmx a set a.`HY_FKXX_FLAG` = #{status},a.`HY_FKXX_MSG` = #{beizhu},a.`cg_je` = #{cgje},a.`cg_dj` = #{cgdj} where a.djbh = #{djbh} and a.dj_sn = #{dj_sn}")
    public void updateysbddmx(ERPddmx erPddmx);

    @Update("update jk_hy_kc set is_on_sale = 0,updatetime = sysdate ")
    public void unOnSale();

    @Update({"<script>" +
            "<foreach collection=\"goodsList\" item=\"item\" separator=\";\">" +
            " UPDATE" +
            " jk_hy_kc" +
            "  SET goods_number = #{item.goods_number, jdbcType=INTEGER}, " +
            "  shop_price = #{item.shop_price_st, jdbcType=DOUBLE}, " +
            "  is_on_sale = 1, " +
            "  updatetime = sysdate " +
            "   where goods_id_s = #{item.goods_id_s,jdbcType=VARCHAR} " +
            "</foreach>" +
            "</script>"})
    void batchUpdate(@Param("goodsList") List<YZYGOODS> goodsList);

    @Insert("INSERT INTO jk_cgddzb(kpbh,kprq,khcode,khmc,soft,dsfddh,hyzbddh,provide) VALUES(#{djbh},to_date(#{rq},'yyyy-mm-dd'),#{customerId},#{customerName},4,#{xgdjbh},#{hydjbh},'0015')")
    void insertDDHZ(ERPddhz ddhz);
    @Insert("INSERT INTO jk_cgddmxb(kpbh,ywxh,spmc,spgg,spcd,unit,pzwh,amount,factprice,xfactprice,batchnumber,yxqz,validdate,productdate,fhdd,kprq,factkprq) "+
            " VALUES(#{djbh},SQ_ZT_YWLX.NextVal,#{ypmc},#{gg},#{cdmc},#{dw},#{pzwh},#{shl},#{cgdj},#{dj},#{ph},"+
            " #{validity},to_date(#{yxq},'yyyymmdd'),to_date(RPAD(#{scrq},10,'-15'),'yyyy-mm-dd'),2,sysdate,sysdate)")
    void insertDDMX(ERPddmx ddmx);

    @Select("call proc_of_jk_cgdd(#{kpbh},#{cgjhbh},#{xsjhbh}) ")
    public void DoERPDD(String kpbh,String cgjhbh,String xsjhbh);

    @Update("update jk_cgddzb set is_run = 1 where is_run = 0 and kpbh = #{kpbh}")
    public void updateERPDD(String kpbh);
}
