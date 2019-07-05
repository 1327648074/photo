package com.ipa.demo.service;

import com.ipa.demo.dao.ImageRepository;
import com.ipa.demo.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;



    public String upload(MultipartFile file,String cpath){


        // 获取文件路径
        String fileName = file.getOriginalFilename();

        if(imageRepository.findByNameAndUrl(fileName,cpath)==null) {



            File dest = new File(cpath+"\\"+fileName);

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String time = dateFormat.format(date);


            try {
                file.transferTo(dest);
                Image image = new Image();
                image.setName(fileName);
                image.setUrl(cpath);
                image.setCreatedDate(time);
                image.setSize(file.getSize());
                image.setType("img");
                imageRepository.save(image);
                return "success to upload";
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "fail to save";

    }

    //文件下载相关代码
    public void downloadImage(String cpath,String name,HttpServletResponse response) throws IOException{
//        String imageName = "\\image8b3e8015-e2ba-4e6d-8da8-bc37478be199.jpg";
//      //  filePath.debug("the imageName is : "+imageName);
//        Image image = imageRepository.findByName(imageName);
//        String fileUrl = filePath + imageName;
        File file = new File(cpath ,name);

//        File file = new File(fileUrl);
        if (file.exists()) {
            response.setContentType("application/force-download");
           response.addHeader("Content-Disposition","attachment;fileName=" + name);
//                response.setHeader("Context-Type", "application/xmsdownload");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
//                            return "322";
                    } catch (IOException e) {
                        e.printStackTrace();
//                            return "3222";
                    }
                }
            }
        }


    }



}
