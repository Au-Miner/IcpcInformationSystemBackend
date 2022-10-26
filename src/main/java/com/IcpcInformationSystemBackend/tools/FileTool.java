package com.IcpcInformationSystemBackend.tools;

import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.TeamScoreInfoResponse;
import com.IcpcInformationSystemBackend.security.FileTypeChecker;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.service.FileService;
import com.IcpcInformationSystemBackend.service.TeamService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
    @Resource
    private TeamService teamService;

    @Value("${files.competitionCertificateDemo}")
    private String templatePath;

    @Value("${files.temporaryBin}")
    private String temporaryBin;

    @Resource
    private FileService fileService;


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

    public String generateCompetitionCertificate(String competitionId, String teamId) throws IOException, DocumentException {
        TeamScoreInfoResponse teamScoreInfoResponse = teamService.getCompetitionCertificateInfo2(competitionId, teamId);
        if (teamScoreInfoResponse == null)
            return "";
        String fileId = UUID.randomUUID().toString();
        String absolutePath = ChangeCharset.toUtf8(temporaryBin + File.separator + fileId + "---" + "competitionCertificate.pdf");

        FileOutputStream out = new FileOutputStream(absolutePath);// 输出流
        PdfReader reader = new PdfReader(templatePath);// 读取pdf模板
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // ByteArrayOutputStream: 对byte类型数据进行写入的类 相当于一个中间缓冲层，将类写入到文件等其他outputStream。它是对字节进行操作，属于内存操作流
        PdfStamper stamper = new PdfStamper(reader, bos);
        // PdfStamper: pdf编辑器类
        AcroFields form = stamper.getAcroFields();
        form.setField("certificateId", "编号/No." + teamScoreInfoResponse.getCompetitionId() + teamScoreInfoResponse.getTeamId());
        form.setField("schoolName", teamScoreInfoResponse.getChiSchoolName() + " / " + teamScoreInfoResponse.getEngSchoolName());
        form.setField("membersName",
                teamScoreInfoResponse.getMember1chiName() + " / " + teamScoreInfoResponse.getMember1engName() + "，" +
                        teamScoreInfoResponse.getMember2chiName() + " / " + teamScoreInfoResponse.getMember2engName() + "，" +
                        teamScoreInfoResponse.getMember3chiName() + " / " + teamScoreInfoResponse.getMember3engName()
        );
        String coachName = teamScoreInfoResponse.getCoach1chiName() + " / " + teamScoreInfoResponse.getCoach1engName();
        if (!Objects.equals(teamScoreInfoResponse.getCoach2chiName(), ""))
            coachName += "，" + teamScoreInfoResponse.getCoach2chiName() + " / " + teamScoreInfoResponse.getCoach2engName();
        form.setField("coachName", coachName);
        form.setField("medal", teamScoreInfoResponse.getChiMedal() + " / " + teamScoreInfoResponse.getEngMedal());
        form.setField("competitionChiName", teamScoreInfoResponse.getCompetitionChiName());
        form.setField("competitionEngName", teamScoreInfoResponse.getCompetitionEngName());
        form.setField("heldSchoolChiNameAndCompetitionChiTime", teamScoreInfoResponse.getChiSchoolName() + "，" + teamScoreInfoResponse.getCompetitionChiTime());
        form.setField("heldSchoolEngNameAndCompetitionEngTime", teamScoreInfoResponse.getEngSchoolName() + "，" + teamScoreInfoResponse.getCompetitionEngTime());

        String QRCode = fileService.generateQRCodeToFile(String.valueOf(teamScoreInfoResponse));
        //后面需要根据前端的选择改变QRCode~~~
        //后面需要根据前端的选择改变QRCode~~~
        //后面需要根据前端的选择改变QRCode~~~
        int pageNo = form.getFieldPositions("QRCode").get(0).page;
        Rectangle signRect = form.getFieldPositions("QRCode").get(0).position;
        float x = signRect.getLeft();
        float y = signRect.getBottom();
        // 读图片
        Image image = Image.getInstance(QRCode);
        // 获取操作的页面
        PdfContentByte under = stamper.getOverContent(pageNo);
        // 根据域的大小缩放图片
        image.scaleToFit(signRect.getWidth(), signRect.getHeight());
        // 添加图片
        image.setAbsolutePosition(x, y);
        under.addImage(image);

        stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
        stamper.close();

        Document doc = new Document();
        PdfCopy copy = new PdfCopy(doc, out);
        doc.open();
        PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
        copy.addPage(importPage);
        doc.close();
        out.close();

        return absolutePath;
    }
}
