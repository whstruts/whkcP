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
    //---20191205 whstruts
    @Select("select a.goodscode as drugCode,case when sum(ab.PlaceNum) -sum(isnull(ab.OccupNum,0.00))<0 then 0 else sum(ab.PlaceNum)-sum(isnull(ab.OccupNum,0.00)) end  as stock " +
            "from  goodsdoc a  " +
            "left join anglebalance ab  on a.goodsid=ab.goodsid " +
            "left join ( " +
            "select EntId,OwnerId,GoodsId,angleid,LocatId,sum(BaseNum) as basenum from GOODSOCCU  group by EntId,OwnerId,GoodsId,LocatId,angleid " +
            ") occ on ab.GoodsId = occ.GoodsId and ab.AngleId = occ.AngleId and ab.LocatId=occ.LocatId and occ.OwnerId = ab.ownerid  " +
            "and occ.entid=ab.entid  " +
            "where  " +
            "a.beactive = 'Y'  " +
            "and ab.locatid  in ('L2EPJD92UG1','L2EPJEGS2KL')   " +
            "group by a.goodscode")
    public List<BBSPKC> getBBSPKC();

    @Select("select a.GOODSCODE as drugCode,MAX(b.SaleTaxP) as price, " +
            "MAX(b.SaleTaxP) as chainprice " +
            "from GOODSDOC a join PGPRICE b on a.GoodsId=b.GoodsId " +
            "join GOODSATTR c on a.GoodsId=c.GoodsId " +
            "join STORBALANCE d on a.GoodsId=b.GoodsId and d.WHId='K2EJZ23Y1JB' " +
            "where   b.saleTaxP>0 " +
            "GROUP BY GoodsCode " +
            "ORDER BY GoodsCode ")
    public List<BBSPJG> getBBSPJG();

    @Select("select b.goodscode as drugCode, " +
            "isnull(c.batchcode,'') as batchNum, " +
            "isnull(convert(varchar(100), c.producedate, 102),'') as prodDate, " +
            "isnull(convert(varchar(100), c.valdate, 102),'') as validity " +
            "from anglebalance a,goodsdoc b,batchcode c " +
            "where a.placenum>0 " +
            "and b.beactive='Y' " +
            "and a.goodsid=b.goodsid " +
            "and a.entid=b.entid " +
            "and a.locatid  in ('L2EPJD92UG1','L2EPJEGS2KL')   " +
            "and a.goodsid=c.goodsid " +
            "and a.angleid=c.angleid " +
            "and c.valdate>getdate() " +
            "order by b.goodscode,c.valdate")
    public List<BBSPPH> getBBSPPH();

    @Select("select  " +
            "rtrim(a.goodscode) as drugCode,  " +
            "rtrim(a.GOODSNAME) as drugName ,  " +
            "rtrim(a.GOODSSPEC) as pack ,  " +
            "rtrim(a.MANUFACTURER) as factory , " +
            "isnull(rtrim(p.UNIT),'') as unit ,  " +
            "rtrim(a.barcode) as barcode ,  " +
            "isnull(rtrim(b.APPROVALNO),'') as approval ,  " +
            "isnull(rtrim(b.GCATEGORY),'') as busiType , " +
            "ISNULL(t.stock,0) as stock ,  " +
            "9999 as price ,  " +
            "rtrim(a.goodsid) as inCode,  " +
            "1 as step,   " +
            "outtaxrate as taxRate ,  " +
            "bzjs as wholePack,  " +
            "b.AdRetailP as recommendedPrice  " +
            "from goodsdoc a  " +
            "inner join ( " +
            "select goodsid,sum(placenum) as stock from ANGLEBALANCE  b       " +
            "where locatid in ('L2EPJD92UG1','L2EPJEGS2KL')    " +
            "group by goodsid " +
            ")t on a.GOODSID=t.GOODSID " +
            "left join goodsattr b on a.goodsid=b.goodsid and a.entid=b.entid " +
            "left join pgprice p on b.goodsid=p.goodsid and p.isbase='Y' " +
            "where a.BEACTIVE='Y' and b.issale = 'N' " +
            "and a.goodscode is not null and a.goodscode !='' " +
            "order by drugCode")
    public List<BBSPZL> getBBSPZL();  //20191205 whstruts ---

    @Select("select code,dwbh,name,jyfw,zzzch,zzyxqz,xkzh,xkzyxqz,gspzh,gspzyxqz,dwjb,qyfr,shr1,email,telephone,fzrlxdh from zt_kh ")
    public List<Khzl> getKH();

    @Select("select kpbh,to_char(kprq,'yyyy-mm-dd') as kprq,1 as ds from jk_cgddzb " +
            "where to_char(kprq,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd') " +
            "union " +
            "select kpbh,to_char(kprq,'yyyy-mm-dd') as kprq,2 as ds from jk_his_cgddzb " +
            "where to_char(kprq,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd') ")
    public List<Ddzt> getDD();

    @Select("select code,sum(amount) amount,wm_concat(batchnumber) batchnumber,wm_concat(to_char(validdate,'YYYY-MM-DD')) validdate,"+
            " wm_concat(to_char(productdate,'YYYY-MM-DD')) productdate from zt_ywkc where  cwtz = 5  group by code")
    public List<SpKC> getKC();
    @Select("select code,spmc,spgg,spcd,pzwh from zt_spzl where spcd is not null ")
    public List<Spzl> getSP();
    @Insert("INSERT INTO jk_cgddzb(kpbh,kprq,khcode,khmc,soft,dsfddh,hyzbddh,provide) VALUES(#{kpbh},to_date(#{kprq},'yyyy-mm-dd'),#{khcode},#{khmc},#{soft},#{dsfddh},#{hyzbddh},'0015')")
    void insertDDHZ(ERPddhz ddhz);

    @Insert("INSERT INTO jk_cgddmxb(kpbh,ywxh,spmc,spgg,spcd,jx,unit,spbz,pzwh,amount,factprice,xfactprice,batchnumber,yxqz,validdate,productdate,fhdd,kprq,factkprq) "+
            " VALUES(#{kpbh},#{ywxh},#{spmc},#{spgg},#{spcd},#{jx},#{unit},#{spbz},#{pzwh},#{amount},#{factprice},#{xfactprice},#{batchnumber},"+
            " #{yxqz},to_date(#{validdate},'yyyymmdd'),to_date(FN_FORM_DATE(#{productdate}),'yyyy-mm-dd'),#{fhdd},to_date(#{kprq},'yyyy-mm-dd'),to_date(#{factkprq},'yyyy-mm-dd'))")
    void insertDDMX(ERPddmx ddmx);

    @Select("select kpbh from （select kpbh,count(code) cc,count(*) ca from jk_cgddmxb group by kpbh） where cc = ca ")
    public List<ERPddhz> getERPDD();

    @Select("call proc_of_jk_cgdd(#{kpbh},#{cgjhbh},#{xsjhbh}) ")
    public void DoERPDD(String kpbh,String cgjhbh,String xsjhbh);

    @Select("select  trim(c.DSFDDH) as orderId, " +
            "trim(a.FP_DM) as invoiceCode, " +
            "trim(a.FP_HM) as invoiceNo, " +
            "'' as securityCode, " +
            "trim(nvl(a.PDF_URL,'')) as invoiceUrl, " +
            "a.dates  ,c.xsddbh " +
            "from FYYK_PSY_InvMainDt a " +
            "inner join FYYK_PSY_InvDetails b on a.DataExchangeId=b.DataExchangeId " +
            "inner join zt_xslist c on b.billcode=c.kpbh " +
            "where (c.xsddbh like '%YSB%' or c.xsddbh like '%YYC%') " +
            " and a.dates > to_char(sysdate-7,'yyyy-mm-dd') " +
            "group by c.DSFDDH,a.FP_DM,a.FP_HM,a.PDF_URL,a.dates,c.xsddbh ")
    public List<DZFP> getdzfp();

//    @Update("update jk_cgddzb set is_run = 1 where is_run = 0 and kpbh = #{kpbh}")
//    public void updateERPDD(String kpbh);

    @Select("select * from ysb_ddhz where is_run = 0 ")
    public List<ysbddhz> getysbddhzs();

    @Select("select * from ysb_ddmx where djbh = #{djbh} ")
    public List<ysbddmx> getysbddmxbydjbh(String djbh);

    @Update("update ysb_ddhz set is_run = 1 where is_run = 0 and djbh = #{djbh}")
     public void updateysbddhz(String djbh);
}
