package hykx.ds.whkc.bean;

public class SpKC {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private double amount;

    public String getBatchnumber() {
        return batchnumber;
    }

    public void setBatchnumber(String batchnumber) {
        this.batchnumber = batchnumber;
    }

    public String getValiddate() {
        return validdate;
    }

    public void setValiddate(String validdate) {
        this.validdate = validdate;
    }

    public String getProductdate() {
        return productdate;
    }

    public void setProductdate(String productdate) {
        this.productdate = productdate;
    }

    private String batchnumber;
    private String validdate;
    private String productdate;
}
