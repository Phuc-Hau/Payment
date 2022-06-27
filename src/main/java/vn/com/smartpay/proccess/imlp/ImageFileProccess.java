package vn.com.smartpay.proccess.imlp;

import org.eclipse.jetty.server.Request;
import vn.com.smartpay.utils.ErrorPro;
import vn.com.smartpay.model.FileUpload;
import vn.com.smartpay.proccess.BaseSerlet;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ImageFileProccess extends BaseSerlet {


    @Override
    public Object proccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO body
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("");
        req.setAttribute(Request.MULTIPART_CONFIG_ELEMENT, multipartConfigElement);

//        Part part = req.getPart("file");

        File dir = new File("file");
        if (!dir.exists()) {
            dir.mkdirs();
        }


        FileUpload fileUpload = null;
        List<String> name = new ArrayList<>();
        for (Part parts : req.getParts()) {
            try (InputStream is = parts.getInputStream()) {

                String fileName = parts.getSubmittedFileName();
                if (fileName != null) {
                    BufferedImage bi = ImageIO.read(is);
                    ImageIO.write(bi, "png", new File(UPLOAD_PATH + fileName));
                }

                name.add(fileName);
                code = ErrorPro.OK.name();
            }
        }
        fileUpload = new FileUpload();
        fileUpload.setFileName(name);
        fileUpload.setAmount(req.getParts().size());

        //TODO out
        return fileUpload;
    }

    @Override
    public Object get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return null;
    }

}
