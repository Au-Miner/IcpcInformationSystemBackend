package com.IcpcInformationSystemBackend.tools;

import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.security.FileTypeChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class FileTool {
    //存放文件的路径
    @Value("${upload.path}")
    private String directory;

    public String uploadImg(MultipartFile file) throws AllException {
        log.info("000");
        String directoryNeed = directory;
        log.info("111");
        if (file.isEmpty()) {
            throw new AllException(EmAllException.FILE_EMPTY, "上传文件为空");
        }
        log.info("222");
        try {
            String fileType = FileTypeChecker.getFileTypeByStream(file.getBytes());
            log.info("333");
            if(!Objects.equals(fileType, "jpg") && !Objects.equals(fileType, "png")){
                log.info("444");
                throw new AllException(EmAllException.BAD_FILE_TYPE, "上传文件类型错误");
            }
        } catch (IOException e){
            log.info("555");
            throw new AllException(EmAllException.UNKNOWN_ERROR, "未知错误");
        }
        log.info("666");
        //文件存放的id名
        String fileId = UUID.randomUUID().toString();
        //源文件名
        String originalFileName = ChangeCharset.toUtf8(file.getOriginalFilename());
        //在指定的目录位置下存放文件
        String absolutePath = ChangeCharset.toUtf8(directoryNeed + File.separator + fileId + "---" + originalFileName);
        log.info(absolutePath);
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
}
