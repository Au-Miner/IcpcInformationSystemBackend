package com.IcpcInformationSystemBackend.tools;

import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.security.FileTypeChecker;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class FileTool {


    public String uploadImg(MultipartFile file, String directoryNeed) throws AllException {
        if (file.isEmpty()) {
            throw new AllException(EmAllException.FILE_EMPTY, "上传文件为空");
        }
        try {
            String fileType = FileTypeChecker.getFileTypeByStream(file.getBytes());
            if(!Objects.equals(fileType, "jpg") && !Objects.equals(fileType, "png")){
                throw new AllException(EmAllException.BAD_FILE_TYPE, "上传文件类型错误");
            }
        } catch (IOException e){
            throw new AllException(EmAllException.UNKNOWN_ERROR, "未知错误");
        }
        //文件存放的id名
        String fileId = UUID.randomUUID().toString();
        //源文件名
        String originalFileName = ChangeCharset.toUtf8(file.getOriginalFilename());
        //在指定的目录位置下存放文件
        String absolutePath = ChangeCharset.toUtf8(directoryNeed + File.separator + fileId + "---" + originalFileName);
        //如果存放文件的文件夹不存在，就创建文件夹
        File destDirectory = new File(directoryNeed);
        if (!destDirectory.exists()) {
            destDirectory.mkdirs();
        }

        try (OutputStream os = new FileOutputStream(absolutePath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            throw new AllException(EmAllException.FILE_EMPTY, "上传文件为空");
        }
        return absolutePath;
    }

    public void downloadFile(HttpServletRequest request,
                             HttpServletResponse response, String fileAddress) throws IOException, AllException {

        File downloadFile = new File(fileAddress);
        if (downloadFile.exists()) {
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String fileName = downloadFile.getName().substring(downloadFile.getName().indexOf("---") + 3);
            String headerValue = "attachment; filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            response.setHeader(headerKey, headerValue);
            response.setContentLength((int) downloadFile.length());

            InputStream myStream = new FileInputStream(fileAddress);
            OutputStream toClient = response.getOutputStream();
            IOUtils.copy(myStream, toClient);
            response.flushBuffer();
            myStream.close();
            toClient.close();
        } else {
            throw new AllException(EmAllException.BAD_REQUEST, "请求文件不存在");
        }
    }

    public String uploadExcel(MultipartFile file, String directoryNeed) throws AllException {
        if (file.isEmpty()) {
            throw new AllException(EmAllException.FILE_EMPTY, "上传文件为空");
        }
        try {
            String fileType = FileTypeChecker.getFileTypeByStream(file.getBytes());
            int index = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
            String postfix = file.getOriginalFilename().substring(index + 1);
            if (Objects.equals(fileType, "doc") && postfix.equals("xls")) ;
            else if (Objects.equals(fileType, "zip") && postfix.equals("xlsx")) ;
            else
                throw new AllException(EmAllException.BAD_FILE_TYPE, "上传文件类型错误");
        } catch (IOException e){
            throw new AllException(EmAllException.UNKNOWN_ERROR, "未知错误");
        }
        //文件存放的id名
        String fileId = UUID.randomUUID().toString();
        //源文件名
        String originalFileName = ChangeCharset.toUtf8(file.getOriginalFilename());
        //在指定的目录位置下存放文件
        String absolutePath = ChangeCharset.toUtf8(directoryNeed + File.separator + fileId + "---" + originalFileName);
        //如果存放文件的文件夹不存在，就创建文件夹
        File destDirectory = new File(directoryNeed);
        if (!destDirectory.exists()) {
            destDirectory.mkdirs();
        }

        try (OutputStream os = new FileOutputStream(absolutePath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            throw new AllException(EmAllException.FILE_EMPTY, "上传文件为空");
        }
        return absolutePath;
    }

    /**
     * 构造函数，初始化excel数据
     * @param filePath  excel路径
     * @param sheetName sheet表名
     */
    public XSSFSheet getExcelSheet(String filePath, String sheetName){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
            return sheets.getSheet(sheetName);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteFile(String filePath) throws AllException {
        try {
            Files.delete(Paths.get(filePath));
        } catch (IOException e) {
            throw new AllException(EmAllException.NO_SUCH_FILE);
        }
    }
}
