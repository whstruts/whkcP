package hykx.ds.whkc.mapper;

import hykx.ds.whkc.bean.ERPddmx;
import hykx.ds.whkc.entity.MyGoodsEntity;
import hykx.ds.whkc.entity.ysbddhz;
import hykx.ds.whkc.entity.ysbddmx;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface KhzlMapper {

    @Insert("INSERT INTO powererp_hnhryy.jk_hy_yp(ypbh,goods_name,market_price,shop_price,is_on_sale,ypdm,cddm,CDMC,GG,TXM,DJ,DW,JX,PZWH,BZ,ZBZ,ISRETAIL,updatetime) "+
            " VALUES(#{ypbh},#{ypmc},#{lsj},#{dj},1,#{ypdm},#{cddm},#{cdmc},#{gg},#{tm},#{dj},#{dw},#{jx},"+
            " #{pzwh},#{bz},#{zbz},#{isretail},sysdate)")
    void insertYZYGOODSP(MyGoodsEntity yzygoods);

    @Insert("INSERT INTO powererp_hnhryy.jk_hy_kc_ph(goods_id_s,ypbh,goods_number,scrq,yxq,ph,updatetime) "+
            " VALUES(#{id},#{ypbh},#{sl},#{scrq},#{yxq},#{ph},sysdate)")
    void insertYZYGOODS(MyGoodsEntity yzygoods);


    @Select("select count(*) from powererp_hnhryy.jk_hy_kc_ph where goods_id_s = #{goods_id_s}")
    public int getYZYGOODS(String goods_id_s);

    @Select("select count(*) from powererp_hnhryy.jk_hy_yp where ypbh = #{ypbh}")
    public int getYZYGOODSP(String ypbh);


    @Update("update powererp_hnhryy.jk_hy_kc_ph set goods_number = #{sl},updatetime = sysdate  " +
            "where goods_id_s = #{id}")
    public void updateYZYGOODS(MyGoodsEntity yzygoods);

    @Update("update powererp_hnhryy.jk_hy_yp set is_on_sale = 1,dj = #{dj},updatetime = sysdate  " +
            "where ypbh = #{ypbh}")
    public void updateYZYGOODSP(MyGoodsEntity yzygoods);

    @Select("select * from powererp_hnhryy.ysb_ddhz where is_zx = '否' ")
    public List<ysbddhz> getysbddhzs();

    @Select("select a.djbh,a.dj_sn,b.goods_id_s as drugcode,a.shl,a.dj,a.je from powererp_hnhryy.ysb_ddmx a,\n" +
            "(" +
            "select gwbh,goods_id_s from (" +
            "select 'HY2HNHY'||ypbh as gwbh,goods_id_s,updatetime,row_number() over (partition by ypbh order by updatetime desc) as row_num\n" +
            " from powererp_hnhryy.jk_hy_kc_ph ) " +
            " where row_num = 1 ) b " +
            " where djbh = #{djbh} " +
            " and a.drugcode like 'HY2HNHY%' " +
            " and a.drugcode = b.gwbh " +
            " union " +
            " select a.djbh,a.dj_sn, a.drugcode,a.shl,a.dj,a.je from powererp_hnhryy.ysb_ddmx a " +
            " where djbh = #{djbh} " +
            " and a.drugcode not like 'HY2HNHY%' ")
    public List<ysbddmx> getysbddmxbydjbh(String djbh);

    @Update("update powererp_hnhryy.ysb_ddhz set is_zx = '是' where is_zx = '否' and djbh = #{djbh}")
     public void updateysbddhz(String djbh);

    @Update("update powererp_hnhryy.ysb_ddmx set hy_fkxx_flag = #{status},hy_fkxx_msg = #{beizhu},cg_je = #{cgje},cg_dj = #{cgdj} where djbh = #{djbh} and dj_sn = #{dj_sn}")
    public void updateysbddmx(ERPddmx erPddmx);

    @Update("update powererp_hnhryy.jk_hy_yp set is_on_sale = 0,updatetime = sysdate ")
    public void unOnSale();

    @Update("update powererp_hnhryy.jk_hy_yp set gwbh = 'HY2HNHY'||ypbh where gwbh is null")
    public void updateGWBH();

}
