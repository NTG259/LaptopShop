package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UpLoadService {

    private final ServletContext servletContext;
    
    public UpLoadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleSaveUploadFile(MultipartFile file, String targetFolder) {
        if (file.isEmpty()) {
            return "";
        }
        
        // Save image
        String finalName = "";
        try {
            byte[] bytes;
            bytes = file.getBytes();
            //relative path : absolute path
            String rootPath = this.servletContext.getRealPath("/resources/images");
            
            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            finalName = +System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalName;
    }

    public String handleGetSourceImg(String imgString) {
        String rootPath = this.servletContext.getRealPath("/resources/images");
        return rootPath + File.separator + imgString;
    }
}
