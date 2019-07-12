package com.ipa.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.ipa.demo.dao.ImageRepository;
import com.ipa.demo.model.Image;
import com.ipa.demo.service.ImageService;
import com.jhlabs.image.*;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/image")
public class ImageController {

    private static String ROOT = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image";
    private static String FilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image";
    private static String USER = "";
    private static String VISIT = "";

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;
    //查找所有图片

    //登入登出操作
    @RequestMapping(value = "/login")
    public String Login(@RequestParam(value = "user") String user) {
        FilePath = ROOT;
        createFolder(user);
        FilePath = ROOT + "\\" + user;
        USER = user;
        VISIT = user;
        return "success";
    }

    @RequestMapping(value = "/visit")
    public void Visit(@RequestParam(value = "user") String user) {

        FilePath = ROOT + "\\" + user;
        VISIT = user;
    }

    @RequestMapping(value = "/logout")
    public void Logout() {
        FilePath = ROOT;
        USER = "";
    }

    //文件夹访问
    @RequestMapping(value = "/access")
    public void Access(@RequestParam(value = "name") String name) {
        FilePath = FilePath + "\\" + name;
    }


    @RequestMapping(value = "/return")
    public void Return(@RequestParam(value = "name") String name) {
        FilePath = FilePath.replace("\\" + name, "");
    }

    //图片数据库操作
    //上传图片
    @RequestMapping(value = "/doUpload")
    public String imageUpload(@RequestParam("file") MultipartFile file) {
        //判断是否为空

        if (file.isEmpty()) return "fail";
        String path = null;
        String cpath = getCpath(path);
        imageService.upload(file, cpath);
        System.out.println(FilePath + "\\" + file.getOriginalFilename());
        return FilePath + "\\" + file.getOriginalFilename();
    }

    @PostMapping("/moveImage")
    public Object doAnalyze (@RequestParam(value = "path")String path){
        File file = new File(path);
        String name = file.getName();
        File file1 = new File(path);
        String name1 = file1.getName();
        JSONObject fileItem = new JSONObject();
        fileItem.put("name", name);
        fileItem.put("folder", name1);
        return fileItem;
    }


    @PostMapping(value = "/uploads")
    public String imageUpload(@RequestParam("file") MultipartFile[] file) {
        //判断是否为空
        for (int i = 0; i < file.length; i++) {
            if (file[i].isEmpty()) return "fail to save for empty";
            String path = null;
            String cpath = getCpath(path);
            imageService.upload(file[i], cpath);
        }
        return "success";
    }

    //下载图片
    @RequestMapping(value = "/download")
    public void downloadImage(@RequestParam(value = "path") String path, HttpServletResponse response) {
        String cpath = ROOT.replace("\\image", path.replace("/", "\\"));
        GrayscaleFilter filter = new GrayscaleFilter();
        File f = new File(cpath);
        String name = f.getName();
        cpath = cpath.replace("\\" + f.getName(), "");
        Image image = imageRepository.findByNameAndUrl(name, cpath);
        try {
            imageService.downloadImage(cpath, name, response);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //查询操作
    @GetMapping(value = "/findAll")
    public List<Image> girlList() {
        return imageRepository.findAll();
    }

    //查看目录下的文件和图片
    @RequestMapping("/list")
    public Object list(/*@RequestBody JSONObject json*//*@RequestParam(value = "path") String path*/) throws ServletException {
        try {
            String cpath = FilePath;
            // 返回的结果集
            List<JSONObject> fileItems = new ArrayList<>();
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(cpath))) {
                for (Path pathObj : directoryStream) {
                    // 获取文件基本属性
                    BasicFileAttributes attrs = Files.readAttributes(pathObj, BasicFileAttributes.class);
                    cpath = cpath.replace(ROOT, "");
                    // 封装返回JSON数据
                    JSONObject fileItem = new JSONObject();
                    fileItem.put("name", pathObj.getFileName().toString());
                    fileItem.put("date", getTime());
                    fileItem.put("path", cpath.replace("\\", "/"));
                    fileItem.put("size", attrs.size());
                    fileItem.put("type", attrs.isDirectory() ? "dir" : "img");
                    fileItems.add(fileItem);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", fileItems);
            return jsonObject;
        } catch (Exception e) {
            System.err.println("获取文件列表失败！");
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/listRoot")
    public Object listRoot(/*@RequestBody JSONObject json*/@RequestParam(value = "path") String path) throws ServletException {
        try {
            String cpath = ROOT + "\\" + USER;
            // 返回的结果集
            List<JSONObject> fileItems = new ArrayList<>();
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(cpath))) {
                for (Path pathObj : directoryStream) {
                    // 获取文件基本属性
                    BasicFileAttributes attrs = Files.readAttributes(pathObj, BasicFileAttributes.class);
                    // 封装返回JSON数据
                    JSONObject fileItem = new JSONObject();
                    fileItem.put("name", pathObj.getFileName().toString());
                    fileItem.put("date", getTime());
                    fileItem.put("path", cpath);
                    fileItem.put("size", attrs.size());
                    fileItem.put("type", attrs.isDirectory() ? "dir" : "img");
                    fileItems.add(fileItem);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", fileItems);
            return jsonObject;
        } catch (Exception e) {
            System.err.println("获取文件列表失败！");
            e.printStackTrace();
            return e.getMessage();
        }
    }

    //通过图片名字查找
    @GetMapping(value = "/findBuName/{name}")
    public List<Image> girlFindOne(@PathVariable("name") String name) {
        return imageRepository.findByName(name);
    }

    //图片管理
    //创建新文件夹
    @RequestMapping("/createFolder")
    public String createFolder(@RequestParam String folderName) {
        try {
//            String path = "792989118@qq.com";
            File newDir = new File(FilePath + "\\" + folderName);
            if (!newDir.mkdir()) {

            } else {
                Image image = new Image();
                image.setUrl(FilePath);
                image.setName(folderName);
                image.setType("dir");
                image.setCreatedDate(getTime());
                imageRepository.save(image);
            }
            return folderName;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //复制
    @RequestMapping("copy")
    public String copy(@RequestParam(value = "path") String path, @RequestParam(value = "name") String name, @RequestParam(value = "newPath") String newPath, HttpServletRequest request) {

        String cpath = getCpath(path);
        try {
            if (imageRepository.findByNameAndUrl(name, FilePath + "\\" + newPath) == null) {
                File srcFile = new File(cpath, name);
                File destFile = new File(FilePath + "\\" + newPath, name);
                if (srcFile.isFile()) {
                    FileUtils.copyFile(srcFile, destFile);
                    Image image1 = imageRepository.findByNameAndUrl(name, cpath);
                    Image image2 = new Image();
                    image2.setName(image1.getName());
                    image2.setSize(image1.getSize());
                    image2.setUrl(FilePath + "\\" + newPath);
                    image2.setType(image1.getType());
                    image2.setCreatedDate(getTime());
                    imageRepository.save(image2);
                } else {
                    FileUtils.copyDirectory(srcFile, destFile);
                    Image dir1 = imageRepository.findByNameAndUrl(name, cpath);
                    Image dir2 = new Image();
                    dir2.setType(dir1.getType());
                    dir2.setName(dir1.getName());
                    dir2.setUrl(FilePath + "\\" + newPath);
                    dir2.setCreatedDate(getTime());
                    imageRepository.save(dir2);
                    List<Image> list = imageRepository.findByUrl(cpath + "\\" + name);
                    for (int i = 0; i < list.size(); i++) {
                        Image image1 = list.get(i);
                        Image image2 = new Image();
                        image2.setName(image1.getName());
                        image2.setSize(image1.getSize());
                        image2.setType(image1.getType());
                        image2.setUrl(FilePath + "\\" + newPath + "\\" + name);
                        image2.setCreatedDate(getTime());
                        imageRepository.save(image2);
                    }
                }
                return "success";
            } else
                return "fail to copy";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //移动
    @RequestMapping("move")
    public String move(@RequestParam(value = "path") String path, @RequestParam(value = "name") String name, @RequestParam(value = "newPath") String newPath) {
        String cpath = getCpath(path);
        try {
            if (imageRepository.findByNameAndUrl(name, FilePath + "\\" + newPath) == null) {
                File srcFile = new File(FilePath + "\\" + path, name);
                File destFile = new File(FilePath + "\\" + newPath, name);
                if (srcFile.isFile()) {
                    FileUtils.moveFile(srcFile, destFile);
                    Image image = imageRepository.findByNameAndUrl(name, cpath);
                    image.setUrl(FilePath + "\\" + newPath);
                    image.setCreatedDate(getTime());
                    imageRepository.save(image);
                } else {
                    FileUtils.moveDirectory(srcFile, destFile);
                    Image dir = imageRepository.findByNameAndUrl(name, cpath);
                    dir.setUrl(FilePath + "\\" + newPath);
                    dir.setCreatedDate(getTime());
                    imageRepository.save(dir);

                    List<Image> list = imageRepository.findByUrl(cpath + "\\" + name);
                    for (int i = 0; i < list.size(); i++) {
                        Image image = list.get(i);
                        image.setUrl(FilePath + "\\" + newPath + "\\" + name);
                        image.setCreatedDate(getTime());
                        imageRepository.save(image);
                    }
                }
                return "success";
            } else {
                return "fail to move";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //删除
    @RequestMapping("remove")
    public String remove(@RequestParam String path) {
        String cpath = ROOT.replace("\\image", path.replace("/","\\"));
        GrayscaleFilter filter = new GrayscaleFilter();
        File f = new File(cpath);
        String name = f.getName();
        cpath = cpath.replace("\\" + f.getName(), "");
        try {
            File file = new File(cpath, name);
            if (file.isFile()) {
                Image image = imageRepository.findByNameAndUrl(name, cpath);
                imageRepository.delete(image);
            } else {
                List<Image> list = imageRepository.findByUrl(cpath + "\\" + name);
                for (int i = 0; i < list.size(); i++) {
                    Image image = list.get(i);
                    imageRepository.delete(image);
                }
                Image dir = imageRepository.findByNameAndUrl(name, cpath);
                imageRepository.delete(dir);
            }
            if (!FileUtils.deleteQuietly(file)) {
                throw new Exception("删除失败: " + file.getAbsolutePath());
            }
            return "success to remove";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //重命名
    @RequestMapping("/rename")
    public Object rename(@RequestParam(value = "path") String path, @RequestParam(value = "name") String name, @RequestParam(value = "newName") String newName) {
        String cpath = getCpath(path);
        try {
            File srcFile = new File(cpath, name);
            File destFile = new File(cpath, newName);
            if (srcFile.isFile()) {
                Image image = imageRepository.findByNameAndUrl(name, cpath);
                image.setName(newName);
                imageRepository.save(image);
                FileUtils.moveFile(srcFile, destFile);

            } else {
                Image dir = imageRepository.findByNameAndUrl(name, cpath);
                dir.setName(newName);
                imageRepository.save(dir);

                List<Image> list = imageRepository.findByUrl(cpath + "\\" + name);
                for (int i = 0; i < list.size(); i++) {
                    Image image = list.get(i);
                    image.setUrl(cpath + "\\" + newName);
                    imageRepository.save(image);
                }
                FileUtils.moveDirectory(srcFile, destFile);

            }
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //图片操作
    //压缩图片
    @PostMapping(value = "/compress")
    public String compress(@RequestParam(value = "path") String path, @RequestParam(value = "name") String name) {
        String cpath = getCpath(path);
        try {
            if (imageRepository.findByNameAndUrl("crop_" + name, cpath) == null) {
                Thumbnails.of(cpath + "\\" + name).scale(1f).outputQuality(0.25f).toFile(cpath + "\\" + "crop_" + name);
                File file = new File(cpath, "crop_" + name);
                saveImage(file);
                return "success to compress";
            } else {
                return "fail to compress for have one";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //缩放
    @PostMapping(value = "/zoomBySize")
    public String zoomBySize(@RequestParam(value = "path") String path, @RequestParam(value = "name") String name,
                             @RequestParam(value = "width") Integer width, @RequestParam(value = "hidth") Integer hidth) {

        String cpath = getCpath(path);
        try {
            if (imageRepository.findByNameAndUrl("zoom_" + width.toString() + "_" + hidth.toString() + name, cpath) == null) {
                Thumbnails.of(cpath + "\\" + name).forceSize(width, hidth).toFile(
                        cpath + "\\" + "zoom_" + width.toString() + "_" + hidth.toString() + "_" + name);
                File file = new File(cpath + "\\" + "zoom_" + width.toString() + "_" + hidth.toString() + "_" + name);
                saveImage(file);
                return "success to zoom";
            } else {
                return "fail to zoom for have one";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @PostMapping(value = "/zoomByScale")
    public String zoomByScale(@RequestParam(value = "path") String path, @RequestParam(value = "name") String name,
                              @RequestParam(value = "scale") double scale) {

        String cpath = getCpath(path);
        try {
            if (imageRepository.findByNameAndUrl("zoom_" + scale + "_" + name, cpath) == null) {
                Thumbnails.of(cpath + "\\" + name).scale(scale).toFile(
                        cpath + "\\" + "zoom_" + scale + "_" + name);
                File file = new File(cpath + "\\" + "zoom_" + scale + "_" + name);
                saveImage(file);
                return "success to zoom";
            } else {
                return "fail to zoom for have one";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    //旋转
    @PostMapping(value = "/rotate")
    public String rotate(@RequestParam(value = "path") String path, @RequestParam(value = "name") String name,
                         @RequestParam(value = "degrees") double degrees) {

        String cpath = getCpath(path);
        try {
            if (imageRepository.findByNameAndUrl("rotate_" + degrees + "_" + name, cpath) == null) {
                Thumbnails.of(cpath + "\\" + name).scale(1).rotate(degrees).toFile(
                        cpath + "\\" + "rotate_" + degrees + "_" + name);
                File file = new File(cpath + "\\" + "rotate_" + degrees + "_" + name);
                saveImage(file);
                return "success to rotate";
            } else {
                return "fail to zoom for have one";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    //裁剪
    @PostMapping(value = "/crop")
    public String crop(@RequestParam(value = "path") String path,
                       @RequestParam(value = "x") String x, @RequestParam(value = "y") String y,
                       @RequestParam(value = "width") String width, @RequestParam(value = "hidth") String hidth) {

        Double d3=Double.parseDouble(x);
        Integer x1 = d3.intValue();
        Double d4=Double.parseDouble(y);
        Integer y1 = d4.intValue();
        Double d1=Double.parseDouble(width);
        Integer width1 = d1.intValue();
        Double d2=Double.parseDouble(hidth);
        Integer hidth1 = d2.intValue();
        System.out.println(x1);
        String cpath = ROOT.replace("\\image", path.replace("/", "\\"));
        GrayscaleFilter filter = new GrayscaleFilter();
        File f = new File(cpath);
        String name = f.getName();
        cpath = cpath.replace("\\" + f.getName(), "");
        try {
            if (imageRepository.findByNameAndUrl("crop_" + x.toString() + "_" + y.toString() + "_" + width.toString() + "_" + hidth.toString() + "_" + name, cpath) == null) {
                Thumbnails.of(cpath + "\\" + name).sourceRegion(x1, y1, width1, hidth1).size(width1, hidth1).keepAspectRatio(false).toFile(
                        cpath + "\\" + "crop_" + x.toString() + "_" + y.toString() + "_" + width.toString() + "_" + hidth.toString() + "_" + name);
                File file = new File(cpath, "crop_" + x.toString() + "_" + y.toString() + "_" + width.toString() + "_" + hidth.toString() + "_" + name);
                saveImage(file);
                String nfile = cpath + "\\" + "waterMark" + "_" + name;
                nfile = nfile.replace(ROOT, "\\image");
                System.out.println(nfile);
                nfile = nfile.replace("\\", "/");
                System.out.println(nfile);
                return nfile;
            } else {
                return "fail to zoom for have one";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }


    //水印
    @PostMapping(value = "/waterMark")
    public String waterMark(@RequestParam(value = "path") String path) {

        String cpath = ROOT.replace("\\image", path.replace("/", "\\"));
        GrayscaleFilter filter = new GrayscaleFilter();
        File f = new File(cpath);
        String name = f.getName();
        cpath = cpath.replace("\\" + f.getName(), "");
        try {
            if (imageRepository.findByNameAndUrl("waterMark" + "_" + name, cpath) == null) {


                Thumbnails.of(cpath + "\\" + name).scale(1f).watermark(Positions.TOP_LEFT, ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image\\shuiyin.png")), 1f)
                        .toFile(cpath + "\\" + "waterMark" + "_" + name);
                File file = new File(cpath + "\\" + "waterMark" + "_" + name);
                saveImage(file);
                String nfile = cpath + "\\" + "waterMark" + "_" + name;
                nfile = nfile.replace(ROOT, "\\image");
                System.out.println(nfile);
                nfile = nfile.replace("\\", "/");
                System.out.println(nfile);
                return nfile;
            } else {
                return "fail to watermark for have one";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    //滤镜处理
    //黑白滤镜
    @PostMapping(value = "/grayFilter")
    public String gray(@RequestParam(value = "path") String path) throws IOException {

        // 定义过滤器
        String cpath = ROOT.replace("\\image", path.replace("/", "\\"));
        GrayscaleFilter filter = new GrayscaleFilter();
        File f = new File(cpath);
        String name = f.getName();
        cpath = cpath.replace("\\" + f.getName(), "");

        BufferedImage fromImage = ImageIO.read(new File(cpath, name));
        int width = fromImage.getWidth();
        int height = fromImage.getHeight();
        BufferedImage toImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        filter.filter(fromImage, toImage);

        ImageIO.write(toImage, "jpg", new File(cpath, "grayF_" + name));
        File file = new File(cpath, "grayF_" + name);
        saveImage(file);

        String nfile = cpath + "\\" + "grayF_" + name;
        nfile = nfile.replace(ROOT, "\\image");
        System.out.println(nfile);
        nfile = nfile.replace("\\", "/");
        System.out.println(nfile);
        return nfile;

    }


    @PostMapping(value = "/invertFilter")
    public String invertFilter(@RequestParam(value = "path") String path) throws IOException {

        // 定义过滤器
        String cpath = ROOT.replace("\\image", path.replace("/", "\\"));
        InvertFilter filter = new InvertFilter();
        File f = new File(cpath);
        String name = f.getName();
        cpath = cpath.replace("\\" + f.getName(), "");

        BufferedImage fromImage = ImageIO.read(new File(cpath, name));
        int width = fromImage.getWidth();
        int height = fromImage.getHeight();
        BufferedImage toImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        filter.filter(fromImage, toImage);

        ImageIO.write(toImage, "jpg", new File(cpath, "InvertFilter" + name));
        File file = new File(cpath, "InvertFilter" + name);
        saveImage(file);

        String nfile = cpath + "\\" + "InvertFilter" + name;
        nfile = nfile.replace(ROOT, "\\image");
        System.out.println(nfile);
        nfile = nfile.replace("\\", "/");
        System.out.println(nfile);
        return nfile;

    }


    @PostMapping(value = "/gaussianFilter")
    public String gaussianFilter(@RequestParam(value = "path") String path) throws IOException {

        // 定义过滤器
        String cpath = ROOT.replace("\\image", path.replace("/", "\\"));
        GaussianFilter filter = new GaussianFilter();
        filter.setRadius(8);

        File f = new File(cpath);
        String name = f.getName();
        cpath = cpath.replace("\\" + f.getName(), "");

        BufferedImage fromImage = ImageIO.read(new File(cpath, name));
        int width = fromImage.getWidth();
        int height = fromImage.getHeight();
        BufferedImage toImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        filter.filter(fromImage, toImage);

        ImageIO.write(toImage, "jpg", new File(cpath, "gaussianFilter" + name));
        File file = new File(cpath, "gaussianFilter" + name);
        saveImage(file);

        String nfile = cpath + "\\" + "gaussianFilter" + name;
        nfile = nfile.replace(ROOT, "\\image");
        System.out.println(nfile);
        nfile = nfile.replace("\\", "/");
        System.out.println(nfile);
        return nfile;

    }


    private String getCpath(String path) {
        String cpath;
        //判断path是否为空
        if (path == null || path.length() == 0) {
            cpath = FilePath;
        } else {
            cpath = FilePath + "\\" + path;
        }
        return cpath;
    }

    private String getTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String time = dateFormat.format(date);
        return time;
    }

    private void saveImage(File file) {
        Image image = new Image();
        image.setUrl(file.getParent());
        image.setName(file.getName());
        image.setType("img");
        image.setSize(file.length());
        image.setCreatedDate(getTime());
        imageRepository.save(image);
    }
}
