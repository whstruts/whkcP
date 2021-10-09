package hykx.ds.whkc.mapper;

import hykx.ds.whkc.bean.ThirdCommodity;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface ThirdMapper {
    @Select("select a.ddwname as Suppliers_name,b.prvno as ypdm,b.dspcd as cddm,c.jxmc as jx,isnull(d.scrq,'') as scrq," +
            "       a.dspid+'_'+isnull(d.picih,'') as Goods_id_s,b.dspcode as Goods_sn,b.dspname as Drug_common_name,b.shenccj as manufacturer,c.pzwh as Approve_number,a.picurl as Drug_img,b.dspspgg as specifications," +
            "       b.danwei as Package_unit,a.zhongbz as Medium_package,b.jianbz as Large_package,a.is_chail as Is_retail,isnull(d.picih,'') as production_batch,isnull(d.baozhiqi,'') as date_expiration,isnull(d.kcsl,0) as repertory," +
            "       b.sptm as Bar_code,a.price as Supplier_price,a.is_zais as Is_on_sale,c.G_YPJYFW as Category,a.is_gengx from doc_sp_wz a" +
            "                                                         left join doc_sp b on a.dspid=b.dspid" +
            "                                                         left join doc_sp_gsp c on a.dspid=c.dspid" +
            "                                                         left join (" +
            "    select dspid,picih,scrq,baozhiqi,SUM(kcsl-wcsl) as kcsl from sp_kfpc" +
            "    where kcsl-wcsl>0 group by dspid,picih,scrq,baozhiqi) d on a.dspid=d.dspid")
    public List<ThirdCommodity> getCommodity();

}
