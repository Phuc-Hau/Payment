package vn.com.smartpay;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import vn.com.smartpay.proccess.imlp.CreateProccess;
import vn.com.smartpay.proccess.imlp.ImageFileProccess;
import vn.com.smartpay.proccess.imlp.ReceivePaymentproccess;
import vn.com.smartpay.proccess.imlp.ReceivePaymentproccess;

public class JettyServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletHandler handler =new ServletHandler();

        handler.addServletWithMapping(CreateProccess.class,"/api/user/createusers");
        handler.addServletWithMapping(ReceivePaymentproccess.class,"/api/user/receivepayment");
        handler.addServletWithMapping(ImageFileProccess.class,"/api/imagefile");
        handler.addServletWithMapping(ReceivePaymentproccess.class,"/api/user/receivepayment");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
