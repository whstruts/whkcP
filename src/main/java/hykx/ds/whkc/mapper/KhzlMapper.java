package hykx.ds.whkc.mapper;

import hykx.ds.whkc.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface KhzlMapper {
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

//    @Update("update jk_cgddzb set is_run = 1 where is_run = 0 and kpbh = #{kpbh}")
//    public void updateERPDD(String kpbh);
}
