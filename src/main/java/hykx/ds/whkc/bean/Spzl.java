package hykx.ds.whkc.bean;

public class Spzl {
    private String code; //商品内码
    private String spmc; //商品通用名称

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getSpgg() {
        return spgg;
    }

    public void setSpgg(String spgg) {
        this.spgg = spgg;
    }

    public String getSpcd() {
        return spcd;
    }

    public void setSpcd(String spcd) {
        this.spcd = spcd;
    }

    public String getPzwh() {
        return pzwh;
    }

    public void setPzwh(String pzwh) {
        this.pzwh = pzwh;
    }

    private String spgg; //商品规格
    private String spcd; //生产企业
    private String pzwh; //批准文号
}
