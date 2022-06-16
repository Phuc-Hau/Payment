package vn.com.smartpay.model;

import com.google.gson.JsonArray;

import java.util.List;

public class Details {
    private String serviceCode;
    private String customerCode;
    private List<GoodDetails> goodDetails;

    public Details(String serviceCode, String customerCode, List goodDetails) {
        this.serviceCode = serviceCode;
        this.customerCode = customerCode;
        this.goodDetails = goodDetails;
    }

    public Details() {
    }


    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public List getGoodDetails() {
        return goodDetails;
    }

    public void setGoodDetails(List goodDetails) {
        this.goodDetails = goodDetails;
    }
}
