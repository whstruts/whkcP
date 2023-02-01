package hykx.ds.whkc.mapper;

import hykx.ds.whkc.bean.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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


    @Select("select BillNo as djbh,Dates as rq,OnTime as ontime,'' as customerId,'已提交' as status,Remark as beizhu,customerName,'否' as is_zx from SALEORDERMT where  is_run_hy = 0 ")
    public List<ysbddhz> getysbddhzs();

    @Select("select BillNo as djbh,BillSn as dj_sn,drugCode,Num as shl,Taxprice as dj,Num*Taxprice as je,'否' as is_zx from SALEORDERDT where BillNo = #{djbh} ")
    public List<ysbddmx> getysbddmxbydjbh(String djbh);

    @Update("update SALEORDERMT set is_run_hy = 1 where is_run_hy = 0 and BillNo = #{djbh}")
     public void updateysbddhz(String djbh);

    @Update("update YZYGOODS set is_on_sale = 0,updatetime = GETDATE() ")
    public void unOnSale();
}
