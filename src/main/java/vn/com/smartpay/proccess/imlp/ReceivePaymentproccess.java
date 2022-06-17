package vn.com.smartpay.proccess.imlp;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import vn.com.smartpay.model.PaymentData;
import vn.com.smartpay.model.QrcodeItem;
import vn.com.smartpay.model.RequestPaymentData;
import vn.com.smartpay.proccess.BaseSerlet;
import vn.com.smartpay.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.apache.commons.codec.digest.DigestUtils.md5;

public class ReceivePaymentproccess extends BaseSerlet {
    @Override
    public Object proccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //TODO proccess
        List<QrcodeItem> listQrCodeItems = new ArrayList<>();
        QrcodeItem qrCodeItems = new QrcodeItem();
        qrCodeItems.setQuantity("10");
        qrCodeItems.setNote("fghjudsfuji");
        qrCodeItems.setQrInfor("dfsgdhgjgjkhlk");
        listQrCodeItems.add(qrCodeItems);

        PaymentData paymentData = new PaymentData();
        paymentData.setMobile("0388826918");
        paymentData.setBankCode("ujdnbf");
        paymentData.setAccountNo("hrusnvvkduehfosnalgn");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String datetime = simpleDateFormat.format(date);
        paymentData.setPayDate(datetime);

        paymentData.setAddtionalData("dsadyhuidsyghuj");

        BigDecimal bigDecimal = new BigDecimal("5623");
        paymentData.setDebitAmount(bigDecimal);

        paymentData.setRespCode("gdydhfuvjd");
        paymentData.setRespDesc("dfghjkrtgfbndfg");

        Random rand = new Random();
        int upperbound = 1000000000;
        int int_random = rand.nextInt(upperbound);

        paymentData.setTraceTransfer(String.valueOf(int_random));
        paymentData.setMessageType("1");
        paymentData.setOrderCode("kfhjfkh");
        paymentData.setUserName("phfau");
        paymentData.setItem(listQrCodeItems);

        String acccesskey="fdghjkbf";
        String checkSum=paymentData.getMobile()
                +paymentData.getBankCode()
                +paymentData.getAccountNo()
                +paymentData.getPayDate()
                +paymentData.getDebitAmount()
                +paymentData.getRespCode()
                +paymentData.getOrderCode()
                +paymentData.getMessageType()
                +acccesskey;

        String checkSummd5 =Hex.encodeHexString(md5(checkSum));
        paymentData.setCheckSum(checkSummd5);

        String realAmount = String.valueOf(paymentData.getDebitAmount());
        paymentData.setRealAmount(realAmount);
        paymentData.setPromotionCode("gfhjkdfghj");
        paymentData.setRate("34");

        String result;
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("http://210.245.26.70/callBackMbbankAPI");
            post.setEntity(new StringEntity(JsonUtils.toString(paymentData)));
            HttpResponse response = httpClient.execute(post);
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        RequestPaymentData requestPaymentData= jsonUtils.toJson(result, RequestPaymentData.class);
        return requestPaymentData;
    }
}
