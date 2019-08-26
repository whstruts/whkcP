package hykx.ds.whkc.bean;

import java.util.List;

public class ERPDD {
    private ERPddhz erpddhz;

    public ERPddhz getErpddhz() {
        return erpddhz;
    }

    public void setErpddhz(ERPddhz erpddhz) {
        this.erpddhz = erpddhz;
    }

    public List<ERPddmx> getErpddmxs() {
        return erpddmxs;
    }

    public void setErpddmxs(List<ERPddmx> erpddmxs) {
        this.erpddmxs = erpddmxs;
    }

    private List<ERPddmx> erpddmxs;
}
