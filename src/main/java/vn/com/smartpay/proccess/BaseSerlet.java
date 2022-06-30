package vn.com.smartpay.proccess;

import vn.com.smartpay.model.ResponseData;
import vn.com.smartpay.utils.JsonUtils;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

public abstract class BaseSerlet extends HttpServlet {

    public String code;
    public JsonUtils jsonUtils = new JsonUtils();
    public static final String UPLOAD_PATH = System.getProperty("user.dir") + "\\file\\";
    public abstract Object proccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    public abstract Object get(HttpServletRequest req,HttpServletResponse resp) throws  IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object data = get(req,resp);
        File file = (File) data;
        if(file.exists()) {
            outImage(resp, file);
        } else {
            ResponseData writer = new ResponseData();
            writer.setCode(code);
            out(resp, JsonUtils.toString(writer));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String xRequestID=req.getHeader("X-Request-ID");
        String xCaller=req.getHeader("X-Caller");
        String xTimestamp=req.getHeader("X-Timestamp");

        resp.setHeader("X-Request-ID","timestamp");
        if( xRequestID.equals("timestamp") && xCaller.equals("MIS") && xTimestamp.equals("timestamp") ) {
            Object data = proccess(req, resp);
            ResponseData writer = new ResponseData();
            writer.setCode(code);
            writer.setData(data);
            out(resp, JsonUtils.toString(writer));
        }
    }

    protected String parseString(HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("UTF-8");

        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String tym;
        while ((tym = reader.readLine()) != null) {
            sb.append(tym);
        }
        return sb.toString();
    }


    protected void out (HttpServletResponse resp,String body) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = resp.getWriter();
        out.print(body);
        out.flush();
    }

    protected void outImage (HttpServletResponse resp, File file) throws IOException {
        resp.setContentType("image/png");
        BufferedImage bi = ImageIO.read(file);
        OutputStream out = resp.getOutputStream();

        ImageIO.write(bi, "png", out);
        out.close();
    }
}
