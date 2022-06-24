package vn.com.smartpay.model;

import java.util.List;

public class FileUpload {
    private int amount;
    private List<String> fileName;



    public List<String> getFileName() {
        return fileName;
    }

    public void setFileName(List<String> fileName) {
        this.fileName = fileName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
