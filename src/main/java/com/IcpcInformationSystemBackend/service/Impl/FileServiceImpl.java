package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.FileService;
import com.IcpcInformationSystemBackend.tools.FileTool;
import com.IcpcInformationSystemBackend.tools.QRCTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private QRCTool qrcTool;

    @Value("${files.competitionCertificatePath}")
    private String competitionCertificateDirectory;

    @Value("${files.schoolImgPath}")
    private String schoolImgDirectory;

    @Value("${files.teamScoresPath}")
    private String teamScoresDirectory;

    @Resource
    private FileTool fileTool;

    private static final String ImgFormat=".png";

    private static final ThreadLocal<SimpleDateFormat> LOCALDATEFORMAT=ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmss"));


    @Override
    public String generateQRCodeToFile(String content) {
        String fileName = "";
        try {
            fileName = LOCALDATEFORMAT.get().format(new Date());
            qrcTool.createCodeToFile(content, new File(competitionCertificateDirectory),fileName + ImgFormat);
        }catch (Exception e){
            return "";
        }
        return competitionCertificateDirectory + "/" + fileName + ImgFormat;
    }

    @Override
    public void generateQRCodeToResponse(HttpServletResponse servletResponse, String content) {
        try {
            qrcTool.createCodeToResponse(servletResponse, content);
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }

    @Override
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileAddress) {
        try {
            try {
                fileTool.downloadFile(request, response, fileAddress);
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        } catch (AllException e) {
            log.info(e.getMsg());
        }
    }

    @Override
    public void downloadStaticFile(HttpServletRequest request, HttpServletResponse response, String fileAddress, String fileName) {
        try {
            try {
                fileTool.downloadStaticFile(request, response, fileAddress, fileName);
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        } catch (AllException e) {
            log.info(e.getMsg());
        }
    }

    @Override
    public Result uploadExcel(MultipartFile file) {
        try {
            return ResultTool.success(fileTool.uploadExcel(file, teamScoresDirectory));
        } catch (AllException e) {
            return ResultTool.error(e.getErrCode(), e.getMsg());
        }
    }

    @Override
    public Result deleteFile(String filePath) {
        try {
            fileTool.deleteFile(filePath);
        } catch (AllException e) {
            return ResultTool.error(e.getErrCode(), e.getMsg());
        }
        return ResultTool.success();
    }

    @Override
    public Result uploadSchoolImg(@RequestBody MultipartFile file) {
        try {
            return ResultTool.success(fileTool.uploadImg(file, schoolImgDirectory));
        } catch (AllException e) {
            return ResultTool.error(e.getErrCode(), e.getMsg());
        }
    }

    @Override
    public void downloadCompetitionCertificate(HttpServletRequest request, HttpServletResponse response, String competitionId, String teamId) {
        String competitionCertificatePath = "";
        try {
            competitionCertificatePath = fileTool.generateCompetitionCertificate(competitionId, teamId);
        }catch (IOException | DocumentException e) {
            log.info(e.getMessage());
        }
        try {
            fileTool.downloadFile(request, response, competitionCertificatePath);
        } catch (IOException e) {
            log.info(e.getMessage());
        } catch (AllException e) {
            log.info(e.getMsg());
        }
        // log.info("competitionCertificatePath:" + competitionCertificatePath);
        try {
            fileTool.deleteFile(competitionCertificatePath);
        } catch (AllException e) {
            log.info(e.getMsg());
        }
    }
}
