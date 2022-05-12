package hykx.ds.whkc.mapper;

import hykx.ds.whkc.bean.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface KhzlMapper {
    @Update("update YZYGOODS set is_on_sale = 0 ")
    void updateYZYGOODS();
    @Delete("DELETE FROM YZYGOODS where is_on_sale = 0")
    void deleteYZYGOODS();
    @Insert("INSERT INTO YZYGOODS(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s) "+
            " VALUES(#{goods_sn},#{goods_name},#{goods_number},#{market_price},#{shop_price_st},#{is_on_sale},#{YPDM},#{CDMC},#{CDDM},#{GG},#{TXM},#{DW},#{JX},"+
            " #{PZWH},#{BZ},#{ZBZ},#{YXQ},#{PH},#{ISRETAIL},#{PCH},#{SCRQ},#{goods_id_s})")
    void insertYZYGOODS(YZYGOODS yzygoods);

    @Insert("INSERT INTO YZYGOODS_FIX(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s) "+
            " VALUES(#{goods_sn},#{goods_name},#{goods_number},#{market_price},#{shop_price_st},#{is_on_sale},#{YPDM},#{CDMC},#{CDDM},#{GG},#{TXM},#{DW},#{JX},"+
            " #{PZWH},#{BZ},#{ZBZ},#{YXQ},#{PH},#{ISRETAIL},#{PCH},#{SCRQ},#{goods_id_s})")
    void insertYZYGOODS_FIX(YZYGOODS yzygoods);


    @Select("select rtrim(a.spbh) as drugCode,isnull(b.hwshl-isnull(t.ykdshl,0)-isnull(c.shl,0),0) as stock,a.lshj as price, a.lshj as chainPrice," +
            "rtrim(isnull(k.pihao,'')) as batchNum," +
            "isnull(convert(varchar(100), k.baozhiqi, 23),'') as prodDate," +
            "isnull(convert(varchar(100), k.sxrq, 23),'') as validity ," +
            "rtrim(a.spmch) as drugName , --商品名称(字符串） *" +
            "rtrim(a.shpgg) as pack , --商品规格(字符串） *" +
            "rtrim(a.shengccj) as factory , --生产厂家(字符串） *--可能是shpchd这个字段" +
            "rtrim(a.dw) as unit , --单位中文药品单位，如盒、包、箱等(字符串） *" +
            "rtrim(a.sptm) as barcode , --商品条形码(字符串）（*最好有）" +
            "rtrim(a.pizhwh) as approval , --批准文号，国药准字H20103180(字符串） *" +
            "rtrim(a.leibie) as busiType , ----经营类别（*最好有）（字符串）可能是leibie或shangplx这个字段(匹配药店经营范围)" +
            "isnull(b.hwshl,0) as stock , --库存（数字）（建议与库存同步语句匹配，可不取有效库存）*" +
            "isnull(a.lshj,9999) as price , --价格（数字）（可选）（有同步价格的需要这个，取值与价格同步语句相同）" +
            "rtrim(a.spid) as inCode, --药品内码(字符串）" +
            "1 as step , --购买增量、步长(数字类型）--即 采购倍数（1）" +
            "a.shlv as taxRate , --税率（数字）" +
            "a.bzgg as midPack , --中包装数（数字）" +
            "a.jlgg as wholePack  --整包装数（数字）" +
            "from spkfk a (nolock)" +
            "left join (select spid,sum(shl) hwshl from sphwph (nolock) " +
            "where hw in ('HWI00000004','HWI00000005','HWI00000015') and dangqzht='合格' and shl>0 " +
            " and sxrq > convert(varchar,getdate(),23) group by spid) b on a.spid=b.spid" +
            " left join (select mx.spid,sum(mx.shl) as ykdshl from jzorder_MX MX (nolock)" +
            " where (mx.djbh like 'XSG%' OR mx.djbh like 'JHT%') and mx.is_zx='否'" +
            " and mx.hw in ('HWI00000004','HWI00000005','HWI00000015') group by mx.spid) t " +
            " on b.spid=t.spid " +
            " left join(select spid,sum(shl) as shl from tmp_dj_xsg212 (nolock)" +
            " where hw in ('HWI00000004','HWI00000005','HWI00000015') and gzid not like '%YSB%' " +
            " group by spid)c " +
            " on b.spid=c.spid" +
            " left join (select * from sphwph (nolock)) k" +
            " on k.spid=a.spid" +
            " where a.beactive='是' " +
            " and isnull(b.hwshl-isnull(t.ykdshl,0)-isnull(c.shl,0),0)> 0;")
    public List<YZYGOODS> getSTGoods();


    @Select("select * from YZYGOODS_FIX where goods_sn = #{goods_sn}")
    public List<YZYGOODS> getYZYGOODS_FIX(String goods_sn);


    @Select("select * from ysb_ddhz where is_run = 0 ")
    public List<ysbddhz> getysbddhzs();

    @Select("select * from ysb_ddmx where djbh = #{djbh} ")
    public List<ysbddmx> getysbddmxbydjbh(String djbh);

    @Update("update ysb_ddhz set is_run = 1 where is_run = 0 and djbh = #{djbh}")
     public void updateysbddhz(String djbh);
}
