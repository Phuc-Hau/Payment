package vn.com.smartpay;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import vn.com.smartpay.proccess.imlp.CreateProccess;

public class JettyServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletHandler handler =new ServletHandler();

        handler.addServletWithMapping(CreateProccess.class,"/api/user/createusers");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
