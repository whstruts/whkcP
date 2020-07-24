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
            " VALUES(#{goods_sn},#{goods_name},#{goods_number},#{market_price},#{shop_price},#{is_on_sale},#{YPDM},#{CDMC},#{CDDM},#{GG},#{TXM},#{DW},#{JX},"+
            " #{PZWH},#{BZ},#{ZBZ},#{YXQ},#{PH},#{ISRETAIL},#{PCH},#{SCRQ},#{goods_id_s})")
    void insertYZYGOODS(YZYGOODS yzygoods);

    @Insert("INSERT INTO YZYGOODS_FIX(goods_sn,goods_name,goods_number,market_price,shop_price,is_on_sale,YPDM,CDMC,CDDM,GG,TXM,DW,JX,PZWH,BZ,ZBZ,YXQ,PH,ISRETAIL,PCH,SCRQ,goods_id_s) "+
            " VALUES(#{goods_sn},#{goods_name},#{goods_number},#{market_price},#{shop_price},#{is_on_sale},#{YPDM},#{CDMC},#{CDDM},#{GG},#{TXM},#{DW},#{JX},"+
            " #{PZWH},#{BZ},#{ZBZ},#{YXQ},#{PH},#{ISRETAIL},#{PCH},#{SCRQ},#{goods_id_s})")
    void insertYZYGOODS_FIX(YZYGOODS yzygoods);

    @Select("select * from YZYGOODS_FIX where goods_sn = #{goods_sn}")
    public List<YZYGOODS> getYZYGOODS_FIX(String goods_sn);

    @Select("select * from ysb_ddhz where is_run = 0 ")
    public List<ysbddhz> getysbddhzs();

    @Select("select * from ysb_ddmx where djbh = #{djbh} ")
    public List<ysbddmx> getysbddmxbydjbh(String djbh);

    @Update("update ysb_ddhz set is_run = 1 where is_run = 0 and djbh = #{djbh}")
     public void updateysbddhz(String djbh);

    @Select("select jingd,'XH' as gysbh,'XH'+RTRIM(a.spbh) as drugCode,isnull(b.hwshl-isnull(t.ykdshl,0)-isnull(c.shl,0),0) as stock, " +
            "sptm,spmch,zjm,dw,shpchd,shpgg,pizhwh,jixing,youxq,shangplx,leibie,jlgg,bzgg,cunchtj,gmpzsyxq,gmpzsh,gsp_pzwhyxq,zzhcpj,ssxkcy,ysbgwj " +
            "from spkfk a " +
            "left join (select spid,sum(hwshl) hwshl from hwsp  " +
            "group by spid) b on a.spid = b.spid " +
            "left join (select mx.spid,sum(mx.shl) as ykdshl from jzorder_MX MX " +
            "where mx.djbh like 'XSG%' and mx.is_zx='否' " +
            "group by mx.spid) t on b.spid = t.spid " +
            "left join (select spid,sum(shl) as shl from tmp_dj_XSG212 " +
            "where gzid not like 'YSB%' " +
            "group by spid) c on b.spid = c.spid " +
            "where a.beactive = '是' " +
            "and a.ysbgwj > 0 " +
            "order by drugCode")
    public List<gysgoods> getGYSGoods();
}
