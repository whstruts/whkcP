package hykx.ds.whkc.bean;

public class Ddzt {
    public String getKpbh() {
        return kpbh;
    }

    public void setKpbh(String kpbh) {
        this.kpbh = kpbh;
    }

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    //ERP接收到订单及处理的状态
    private String kpbh; //订单号
    private String kprq; //日期
    private String ds; //状态
}
