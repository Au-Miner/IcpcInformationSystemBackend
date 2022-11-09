package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.TeamScoreDoMapper;
import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.TeamScoreDo;
import com.IcpcInformationSystemBackend.model.response.CompetitionAdmissionTicketResponse;
import com.IcpcInformationSystemBackend.model.response.CompetitionEntryListResponse;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.FileService;
import com.IcpcInformationSystemBackend.tools.*;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Value("${files.teamPhotosPath}")
    private String teamPhotosDirectory;

    @Resource
    private FileTool fileTool;

    @Resource
    private CommonTool commonTool;

    @Resource
    private AuthTool authTool;

    @Resource
    private TeamScoreDoMapper teamScoreDoMapper;

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
    public void downloadCompetitionEntryList(HttpServletResponse response, ArrayList<String> colHead, ArrayList<CompetitionEntryListResponse> competitionEntryList2) {
        //创建XSSFWorkbook对象
        try {
            XSSFWorkbook result = new XSSFWorkbook();
            //创建HSSFSheet对象
            XSSFSheet sheet = result.createSheet("competitionEntryList");
            //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
            XSSFRow row1 = sheet.createRow(0);
            for (int i = 0; i < colHead.size(); i++)
                row1.createCell(i).setCellValue(colHead.get(i));
            for (int i = 0; i < competitionEntryList2.size(); i++) {
                XSSFRow tmp = sheet.createRow(i + 1);
                tmp.createCell(0).setCellValue(competitionEntryList2.get(i).getChiTeamName());
                tmp.createCell(1).setCellValue(competitionEntryList2.get(i).getEngTeamName());
                tmp.createCell(2).setCellValue(competitionEntryList2.get(i).getSchoolId());
                tmp.createCell(3).setCellValue(competitionEntryList2.get(i).getMember1chiName());
                tmp.createCell(4).setCellValue(competitionEntryList2.get(i).getMember2chiName());
                tmp.createCell(5).setCellValue(competitionEntryList2.get(i).getMember3chiName());
                tmp.createCell(6).setCellValue(competitionEntryList2.get(i).getCoach1chiName());
                tmp.createCell(7).setCellValue(competitionEntryList2.get(i).getCoach2chiName());
                tmp.createCell(8).setCellValue(competitionEntryList2.get(i).getType());
            }
            //输出Excel文件
            String fileName1 = new String("competitionEntryList.xlsx".getBytes("UTF-8"), "UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName1);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            OutputStream output = response.getOutputStream();
            result.write(output);
            output.close();
        } catch (Exception e) {
            log.error("导出统计模板生成excel异常");
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

    @Override
    public void downCompetitionAdmissionTicket(HttpServletRequest request, HttpServletResponse response, CompetitionAdmissionTicketResponse competitionAdmissionTicketResponse) {
        String competitionAdmissionTicketPath = "";
        try {
            competitionAdmissionTicketPath = fileTool.generateCompetitionAdmissionTicket(competitionAdmissionTicketResponse);
        }catch (IOException | DocumentException e) {
            log.info(e.getMessage());
        }
        // log.info(competitionAdmissionTicketPath);
        try {
            fileTool.downloadFile(request, response, competitionAdmissionTicketPath);
        } catch (IOException e) {
            log.info(e.getMessage());
        } catch (AllException e) {
            log.info(e.getMsg());
        }
        // log.info("competitionCertificatePath:" + competitionCertificatePath);
        try {
            fileTool.deleteFile(competitionAdmissionTicketPath);
        } catch (AllException e) {
            log.info(e.getMsg());
        }
    }

    @Override
    public Result uploadTeamPhoto(MultipartFile file, String competitionId, String teamId) {
        if (!commonTool.judgeTeamIfExists(competitionId, teamId))
            return ResultTool.error(EmAllException.NO_SUCH_TEAM);
        if (!commonTool.judgeCompetitionChairmanIdentityIfRight(competitionId, authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        String fileDirectory = teamPhotosDirectory + File.separator + competitionId + File.separator + teamId;
        try {
            String filePath = fileTool.uploadImg(file, fileDirectory);
            TeamScoreDo teamScoreDo = new TeamScoreDo();
            teamScoreDo.setTeamId(teamId);
            teamScoreDo.setCompetitionId(competitionId);
            teamScoreDo.setPhotos(filePath);
            if (teamScoreDoMapper.updateByPrimaryKeySelective(teamScoreDo) == 0)
                return ResultTool.error(EmAllException.DATABASE_ERR);
            return ResultTool.success();
        } catch (AllException e) {
            return ResultTool.error(e.getErrCode(), e.getMsg());
        }
    }
}
