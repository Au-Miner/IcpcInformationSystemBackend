package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.model.request.TeamScoreInfo;
import com.IcpcInformationSystemBackend.model.response.CompetitionAdmissionTicketResponse;
import com.IcpcInformationSystemBackend.model.response.CompetitionEntryListResponse;
import com.IcpcInformationSystemBackend.model.response.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public interface FileService {
    String generateQRCodeToFile(String content);

    void generateQRCodeToResponse(HttpServletResponse servletResponse, String content);

    void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileAddress);

    void downloadStaticFile(HttpServletRequest request, HttpServletResponse response, String fileAddress, String fileName);

    Result uploadExcel(MultipartFile file);

    Result deleteFile(String filePath);

    Result uploadSchoolImg(@RequestBody MultipartFile file);

    void downloadCompetitionCertificate(HttpServletRequest request, HttpServletResponse response, String competitionId, String teamId);

    void downloadCompetitionEntryList(HttpServletResponse response, ArrayList<String> colHead, ArrayList<CompetitionEntryListResponse> competitionEntryList2);

    void downCompetitionAdmissionTicket(HttpServletRequest request, HttpServletResponse response, CompetitionAdmissionTicketResponse competitionAdmissionTicketResponse);
}
