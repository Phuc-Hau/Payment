package vn.com.smartpay.model;

public class QrcodeItem {
    private String quantity;
    private String note;
    private String qrInfor;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getQrInfor() {
        return qrInfor;
    }

    public void setQrInfor(String qrInfor) {
        this.qrInfor = qrInfor;
    }
}
