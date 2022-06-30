package vn.com.smartpay.proccess.imlp;

import vn.com.smartpay.proccess.BaseSerlet;
import vn.com.smartpay.utils.ErrorPro;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class GetImageFileProccess extends BaseSerlet {
    @Override
    public Object proccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return null;
    }

    @Override
    public Object get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //TODO Get body
        String image = req.getParameter("image");

        File file = new File(UPLOAD_PATH+image);

        if(file.isFile()){
            code = ErrorPro.OK.name();
        } else {
            code = ErrorPro.Fall.name();
        }

        return file;
    }
}
