package vn.com.smartpay.model;

public class Sandbox {
    private String desc;
    private Object details;
    private String notifyUrl;
    private String returnUrl;
    private String channel;
    private String subChannel;
    private String orderNo;
    private String created;
    private int amount;
    private String signature;

    public Sandbox() {
    }

    public Sandbox(String desc, Object details, String notifyUrl, String returnUrl, String channel, String subChannel, String orderNo, String created, int amount, String signature) {
        this.desc = desc;
        this.details = details;
        this.notifyUrl = notifyUrl;
        this.returnUrl = returnUrl;
        this.channel = channel;
        this.subChannel = subChannel;
        this.orderNo = orderNo;
        this.created = created;
        this.amount = amount;
        this.signature = signature;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(String subChannel) {
        this.subChannel = subChannel;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
