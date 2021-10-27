package hykx.ds.whkc.mapper;

import hykx.ds.whkc.bean.ThirdCommodity;
import hykx.ds.whkc.bean.ThirdCustomer;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface ThirdMapper {
    @Select("select  " +
            "            a.code as drugid,  " +
            "            a.spmc as drugCommonName , " +
            "            a.spellcode as nameZjm, " +
            "            a.spcd as manufacturer, " +
            "            a.spcdzjm as manufacturerZjm, " +
            "            a.pzwh as approveNumber, " +
            "            '' as drugImg, " +
            "            '' as recipeType, " +
            "            '' as typeCode, " +
            "             max(a.HYLSJ)  as supplierPrice , " +
            "            '' as dosageForm, " +
            "            '' as appearance, " +
            "            '' as bases, " +
            "            '' as majorFunctions, " +
            "            '' as untowardEffect, " +
            "            '' as taboo, " +
            "            '' as store, " +
            "            '' as warnings, " +
            "            '' as drugInteractions, " +
            "            '' as brand, " +
            "            '' as usageDosage, " +
            "            a.spgg as specifications ,  " +
            "            a.unit as packageUnit, " +
            "            '1' as mediumPackage, " +
            "            a.spbz as largePackage, " +
            "            '' as leftView, " +
            "            '' as rightView, " +
            "            '' as unpackView, " +
            "            '' as specificationView, " +
            "            a.code as goodsSn, " +
            "            '1' as isRetail, " +
            "            wm_concat(k.batchnumber) as productionBatch, " +
            "            wm_concat(nvl(to_char(k.validdate,'yyyy-mm-dd'),'')) as dateExpriation, " +
            "            nvl(sum(k.amount),0) as repertory , " +
            "            '' as barCode, " +
            "            nvl(a.ZDYBM,'安童生') as supplierName, " +
            "            '' as supplier, " +
            "            wm_concat(nvl(to_char(k.productdate,'yyyy-mm-dd'),'')) as productionDate  " +
            "            from zt_spzl a  " +
            "            left join zt_ywkc k on k.code=a.code  " +
            "            left join ZT_ZL_JYFW c on a.jyfw=c.code  " +
            "            where k.storehouse in('2','3','7') and k.cwtz in('01','03','04')   " +
            "            and k.state=1 and k.sfgq=0 " +
            "            group by a.code,a.spmc,a.spgg,a.spcd,a.unit,a.pzwh,c.name,1,a.xxsl,a.spellcode,a.spcdzjm,a.spbz,a.HYLSJ,a.ZDYBM  " +
            "            having sum(k.amount) > 0  " +
            "            order by drugid")
    public List<ThirdCommodity> getCommodity();

    @Select("select code,name,telephone,linkman,address,zzzch as taxNumber,xkzh from zt_kh")
    public List<ThirdCustomer> getCustomer();

}