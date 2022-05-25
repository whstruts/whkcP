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
            "rtrim(a.spmch) as drugName , " +
            "rtrim(a.shpgg) as pack , " +
            "rtrim(a.shengccj) as factory , " +
            "rtrim(a.dw) as unit , " +
            "rtrim(a.sptm) as barcode , " +
            "rtrim(a.pizhwh) as approval , " +
            "rtrim(a.leibie) as busiType , " +
            "rtrim(a.spid) as inCode, " +
            "1 as step , " +
            "a.shlv as taxRate , " +
            "a.bzgg as midPack , " +
            "a.jlgg as wholePack , " +
            "a.zjm as zjm , " +
            "a.jixing as jixing  " +
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
            " and isnull(b.hwshl-isnull(t.ykdshl,0)-isnull(c.shl,0),0)> 0")
    public List<STGoods> getSTGoods();

    @Select("select dwbh,danwbh,dwmch,zjm,kehufl,isjh,isxs,dzhdh,yhzhh,lxr,ghxde,xsxde,otd," +
            "xdqxg,xdqxx,koul,canskl,yshye,yfye,yingshsx,yshjzh,yfjzh,beactive,yishj,xvkz,shfyyzz," +
            "yingyzz,jingyfw,oldyfye,oldyshye,frdb,zhgzsh,zhj_bl,shn_xshe,pzhshl,is_jg,shn_hgl,khyh," +
            "idcard,xvkzname,fzhjg,fzhrq,yyzzdjjg,reg_capital,shwdjh,jyfsh,gonghnl,yusye,yufye,flye,chnjsts," +
            "oldflye,oldyufye,oldyusye,yufjzh,yusjzh,fljzh,kehulbbh,shenhe,gsp_gspyxq,gsp_gmpyxq,gsp_yyzzyxq," +
            "gsp_xkzyxq,gsp_wtsyxq,gsp_zbxyyxq,gsp_dmzyxq,khhm,gspzs,gmpzs,gsp_jgdmnjyxq,gsp_thr,gsp_thrsfz,gsp_zlfzr," +
            "gsp_cgwtr,gsp_cgwtrsfz,gsp_cgwtrdh,gsp_cgwtsyxq,zhilbztx,xydcb,email,xy_gxysfz,xy_frwts,xy_zzjgdmz,xy_swdjz from mchk")
    public List<mchk> getCustomer();


    @Select("select * from YZYGOODS_FIX where goods_sn = #{goods_sn}")
    public List<YZYGOODS> getYZYGOODS_FIX(String goods_sn);


    @Select("select * from ysb_ddhz where is_run = 0 ")
    public List<ysbddhz> getysbddhzs();

    @Select("select * from ysb_ddmx where djbh = #{djbh} ")
    public List<ysbddmx> getysbddmxbydjbh(String djbh);

    @Update("update ysb_ddhz set is_run = 1 where is_run = 0 and djbh = #{djbh}")
     public void updateysbddhz(String djbh);
}
