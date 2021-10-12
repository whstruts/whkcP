package hykx.ds.whkc.mapper;

import hykx.ds.whkc.bean.ThirdCommodity;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface ThirdMapper {
    @Select("select " +
            "a.code as drugid, " +
            "a.spmc as drugCommonName ," +
            "a.spellcode as nameZjm," +
            "a.spcd as manufacturer," +
            "a.spcdzjm as manufacturerZjm," +
            "a.pzwh as approval," +
            "'' as drugImg," +
            "'' as recipeType," +
            "'' as typeCode," +
            "'' as dosageForm," +
            "'' as appearance," +
            "'' as bases," +
            "'' as majorFunctions," +
            "'' as untowardEffect," +
            "'' as taboo," +
            "'' as store," +
            "'' as warnings," +
            "'' as drugInteractions," +
            "'' as brand," +
            "'' as usageDosage," +
            "a.spgg as specifications , " +
            "a.unit as packageUnit," +
            "'1' as mediumPackage," +
            "a.spbz as largePackage," +
            "'' as leftView," +
            "'' as rightView," +
            "'' as unpackView," +
            "'' as specificationView," +
            "a.code as goodsSn," +
            "'1' as isRetail," +
            "wm_concat(k.batchnumber) as productionBatch," +
            "wm_concat(nvl(to_char(k.validdate,'yyyy-mm-dd'),'')) as dateExpriation," +
            "nvl(sum(k.amount),0) as repertory ," +
            "'' as barCode," +
            "'安童生' as supplierName," +
            "'' as supplier," +
            "nvl(max(case when a.zdjj=0 then 9999 else a.zdjj end),9999)  as supplierPrice , " +
            "wm_concat(nvl(to_char(k.productdate,'yyyy-mm-dd'),'')) as productionDate " +
            "from zt_spzl a " +
            "left join zt_kc k on k.code=a.code " +
            "left join ZT_ZL_JYFW c on a.jyfw=c.code " +
            "group by a.code,a.spmc,a.spgg,a.spcd,a.unit,a.pzwh,c.name,1,a.xxsl,a.spellcode,a.spcdzjm,a.spbz " +
            "having sum(k.amount) > 0 " +
            "order by drugid")
    public List<ThirdCommodity> getCommodity();

}
