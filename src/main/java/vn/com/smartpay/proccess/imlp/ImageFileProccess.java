package vn.com.smartpay.proccess.imlp;

import org.eclipse.jetty.server.Request;
import vn.com.smartpay.model.ErrorPro;
import vn.com.smartpay.model.FileUpload;
import vn.com.smartpay.proccess.BaseSerlet;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;


public class ImageFileProccess extends BaseSerlet {

    private static final String UPLOAD_PATH = System.getProperty("user.dir")+"\\file\\";
    @Override
    public Object proccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO body
        MultipartConfigElement multipartConfigElement= new MultipartConfigElement("");
        req.setAttribute(Request.MULTIPART_CONFIG_ELEMENT, multipartConfigElement);

        Part part = req.getPart("file");

        File  dir =new File("file");
        if(!dir.exists()) {
            dir.mkdirs();
        }

        FileUpload fileUpload=null;

        try {
            String fileName = part.getSubmittedFileName();

            InputStream is= part.getInputStream();
            BufferedImage bi = ImageIO.read(is);
            ImageIO.write(bi, "png", new File(UPLOAD_PATH+fileName));

            fileUpload = new FileUpload();
            fileUpload.setFileName(part.getSubmittedFileName());
            fileUpload.setContentType(part.getContentType());

            code = ErrorPro.OK.name();
        }catch (Exception e){
            code = ErrorPro.Fall.name();
        }

        //TODO out
        return fileUpload;
    }

}
