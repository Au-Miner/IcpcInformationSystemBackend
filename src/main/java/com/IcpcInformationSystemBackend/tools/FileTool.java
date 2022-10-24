package com.IcpcInformationSystemBackend.tools;

import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.security.FileTypeChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class FileTool {
    //存放文件的路径
    @Value("${upload.path}")
    private String directory;

    public String uploadImg(MultipartFile file) throws AllException {
        String directoryNeed = directory;
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

    // public void previewFile(HttpServletRequest request,
    //                         HttpServletResponse response,
    //                         String fileAddress) throws IOException, AllException{
    //     File file = new File(fileAddress);
    //     if (file.exists()) {
    //         byte[] data = null;
    //         FileInputStream input = null;
    //         input = new FileInputStream(file);
    //         data = new byte[input.available()];
    //         input.read(data);
    //         response.getOutputStream().write(data);
    //         if(input != null) {
    //             input.close();
    //         }
    //     } else {
    //         throw new AllException(EmAllException.BAD_REQUEST, "请求文件不存在");
    //     }
    // }
}
