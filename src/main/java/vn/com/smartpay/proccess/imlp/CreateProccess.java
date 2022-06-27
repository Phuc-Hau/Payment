package vn.com.smartpay.proccess.imlp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import vn.com.smartpay.model.CreateOrder;
import vn.com.smartpay.model.Details;
import vn.com.smartpay.model.GoodDetails;
import vn.com.smartpay.model.Sandbox;
import vn.com.smartpay.proccess.BaseSerlet;
import vn.com.smartpay.utils.JsonUtils;
import vn.com.smartpay.utils.HashUtlis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class CreateProccess extends BaseSerlet {
    @Override
    public Object proccess(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //TODO body
        String data = parseString(req);
        CreateOrder createOrder = JsonUtils.toJson(data,CreateOrder.class);

        //TODO proccess
        String A= "amount="+createOrder.getAmount()
                +"&channel="+createOrder.getChannel()
                +"&orderNo="+createOrder.getOrderNo()
                +"&requestId="+createOrder.getRequestId()
                +"&subChannel="+createOrder.getSubChannel();

        String SignTemp=A+"&key="+createOrder.getHashKey();
        String Signature = HashUtlis.SHA256(SignTemp);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strDate = formatter.format(date);

        GoodDetails goodDetails = new GoodDetails();
        goodDetails.setId("001");
        goodDetails.setName("Phuc hau");
        goodDetails.setPrice(1000);
        goodDetails.setQuantity(10);

        List<GoodDetails> goodDetailsList =new ArrayList<GoodDetails>();
        goodDetailsList.add(goodDetails);

        Details details = new Details();
        details.setServiceCode("PAYMENT");
        details.setCustomerCode("00001");
        details.setGoodDetails(goodDetailsList);

        Sandbox sandbox = new Sandbox();
        sandbox.setDesc("desc");
        sandbox.setDetails(details);
        sandbox.setNotifyUrl("https://notify.com.vn/notify");
        sandbox.setReturnUrl("https://notify.com.vn/notify");
        sandbox.setCreated("savista");
        sandbox.setSubChannel("savista");
        sandbox.setOrderNo("01DR0B5X0D4A75BW2SB3KBWGXN");
        sandbox.setCreated(strDate);
        sandbox.setAmount(1000);
        sandbox.setSignature(Signature);

        
        String datatext = Base64.getEncoder().encodeToString(JsonUtils.toString(sandbox).getBytes());

        String uri ="https://sb-gateway.paysmart.com.vn/v1.0/order?data="+datatext;

        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        try {
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(uri)).build();
            HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());


            System.out.println("Status code: " + response.statusCode());
            System.out.println("Headers: " + response.headers().allValues("content-type"));
            System.out.println("Body: " + response.body());

        } catch (IOException | InterruptedException e) {
            code = "";
        }
        return Signature;
    }

    @Override
    public Object get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return null;
    }
}
