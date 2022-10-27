// package com.IcpcInformationSystemBackend.tools;
//
// import lombok.extern.slf4j.Slf4j;
// import org.apache.poi.ss.usermodel.BorderStyle;
// import org.apache.poi.ss.usermodel.HorizontalAlignment;
// import org.apache.poi.ss.usermodel.IndexedColors;
// import org.apache.poi.ss.usermodel.VerticalAlignment;
// import org.apache.poi.ss.util.CellRangeAddress;
// import org.apache.poi.ss.util.RegionUtil;
// import org.apache.poi.xssf.usermodel.*;
// import org.springframework.stereotype.Component;
//
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.io.OutputStream;
// import java.io.UnsupportedEncodingException;
// import java.util.ArrayList;
// import java.util.List;
//
// @Slf4j
// @Component
// public class ExcelTool {
//     /**
//      * 数据写入
//      *
//      * @param title    表格标题
//      * @param colHead  列头（表头）
//      * @param dataList 数据列表
//      * @return 返回表格
//      */
//     public <T> XSSFWorkbook createExcel(String title, List<String> colHead, ArrayList<ArrayList<Object> > dataList) {
//         // 创建一个表格
//         XSSFWorkbook wb = new XSSFWorkbook();
//         // 创建工作页并赋名
//         XSSFSheet sheet = wb.createSheet("Sheet1");
//         // 创建标题
//         XSSFRow titleRow = sheet.createRow(0);
//         XSSFCell titleCell = titleRow.createCell(0);
//         // 设置标题高度
//         titleRow.setHeight((short) (20 * 25));
//         // 给标题设置样式
//         XSSFCellStyle titleStyle = this.getTitleStyle(wb);
//         // 给列头（表头）设置样式
//         XSSFCellStyle colHeadStyle = this.getColHeadStyle(wb);
//         // 给数据列表设置样式
//         XSSFCellStyle dataListStyle = this.getDataListStyle(wb);
//         // 合并单元格 起始行 截至列 起始列 截至行
//         //sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, dataList.get(0).length - 1));
//         CellRangeAddress titleMerge = new CellRangeAddress(0, 0, 0, dataList.get(0).size() - 1);
//         sheet.addMergedRegion(titleMerge);
//         RegionUtil.setBorderTop(0, titleMerge, sheet);
//         RegionUtil.setBorderRight(0, titleMerge, sheet);
//         // 给标题样式附上
//         titleCell.setCellStyle(titleStyle);
//         // 给标题赋值
//         titleCell.setCellValue(title);
//         // 创建表头行
//         XSSFRow colHeadRow = sheet.createRow(1);
//         // 设置表头行高度
//         colHeadRow.setHeight((short) (15 * 20));
//         // 给表头设置样式并赋值
//         for (int i = 0; i < colHead.size(); i++) {
//             XSSFCell colHeadCell = colHeadRow.createCell(i);
//             colHeadCell.setCellStyle(colHeadStyle);
//             colHeadCell.setCellValue(colHead.get(i));
//         }
//         // 将查询到的数据赋到列表
//         for (int i = 0; i < dataList.size(); i++) {
//             // 获取每一行的元素
//             List<Object> objects = dataList.get(i);
//             // 创建行
//             XSSFRow dataRow = sheet.createRow(i + 2);
//             // 循环当前行的列元素设置样式并赋值
//             for (int j = 0; j < objects.size(); j++) {
//                 XSSFCell dataRowCell = dataRow.createCell(j);
//                 dataRowCell.setCellStyle(dataListStyle);
//                 dataRowCell.setCellValue(objects.get(j).toString());
//                 // 自适应列宽
//                 sheet.autoSizeColumn(j);
//                 sheet.setColumnWidth(j, sheet.getColumnWidth(j));
//             }
//         }
//         return wb;
//     }
//
//     /**
//      * 外部接口调用出口
//      *
//      * @param response 响应头
//      * @param title    表格标题
//      * @param colHead  列头（表头）
//      * @param dataList 数据列表
//      */
//     public void exportExcel(HttpServletResponse response, String title, List<String> colHead, List<List<Object> > dataList) {
//         try {
//             XSSFWorkbook result = createExcel(title, colHead, dataList);
//             String fileName = title + System.currentTimeMillis() + ".xlsx";
//             setResponseHeader(response, fileName);
//             OutputStream output = response.getOutputStream();
//             result.write(output);
//             output.close();
//         } catch (IOException e) {
//             log.error(e.getMessage());
//         }
//     }
//
//     /**
//      * 发送响应流方法
//      *
//      * @param response HttpServletResponse
//      * @param fileName String
//      */
//     public static void setResponseHeader(HttpServletResponse response, String fileName) {
//         try {
//             String fileName1 = new String(fileName.getBytes("UTF-8"), "UTF-8");
//             response.setContentType("application/octet-stream;charset=UTF-8");
//             response.setHeader("Content-Disposition", "attachment;filename=" + fileName1);
//             response.addHeader("Pargam", "no-cache");
//             response.addHeader("Cache-Control", "no-cache");
//         } catch (UnsupportedEncodingException e) {
//             log.error(e.getMessage());
//         }
//     }
//
//     /**
//      * 设置标题样式
//      *
//      * @param workbook 表格
//      * @return 样式
//      */
//     public XSSFCellStyle getTitleStyle(XSSFWorkbook workbook) {
//         // 设置字体
//         XSSFFont font = workbook.createFont();
//         // 设置字体大小
//         font.setFontHeightInPoints((short) 15);
//         // 设置字体加粗
//         font.setBold(true);
//         // 设置字体样式
//         font.setFontName("Courier New");
//         // 设置样式;
//         XSSFCellStyle style = workbook.createCellStyle();
//         // 在样式用应用设置的字体;
//         style.setFont(font);
//         // 设置自动换行;
//         style.setWrapText(false);
//         // 设置水平居中
//         style.setAlignment(HorizontalAlignment.CENTER);
//         // 设置垂直居中
//         style.setVerticalAlignment(VerticalAlignment.CENTER);
//         // 设置单元格背景颜色
//         style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
//         return style;
//     }
//
//     /**
//      * 给列头设置样式
//      *
//      * @param workbook 表格
//      * @return 样式
//      */
//     public XSSFCellStyle getColHeadStyle(XSSFWorkbook workbook) {
//         // 设置字体
//         XSSFFont font = workbook.createFont();
//         // 设置字体大小
//         font.setFontHeightInPoints((short) 12);
//         // 设置字体样式
//         font.setFontName("Courier New");
//         // 设置字体加粗
//         font.setBold(true);
//         // 设置样式;
//         XSSFCellStyle style = workbook.createCellStyle();
//         // 在样式用应用设置的字体;
//         style.setFont(font);
//         // 设置水平居中
//         style.setAlignment(HorizontalAlignment.CENTER);
//         // 设置垂直居中
//         style.setVerticalAlignment(VerticalAlignment.CENTER);
//         // 上边框
//         style.setBorderTop(BorderStyle.THIN);
//         // 下边框
//         style.setBorderBottom(BorderStyle.THIN);
//         // 左边框
//         style.setBorderLeft(BorderStyle.THIN);
//         // 右边框
//         style.setBorderRight(BorderStyle.THIN);
//         // 设置自动换行;
//         style.setWrapText(false);
//         // 设置单元格背景颜色
//         style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
//         return style;
//     }
//
//     /**
//      * 给数据列表设置样式
//      *
//      * @param workbook 表格
//      * @return 样式
//      */
//     public XSSFCellStyle getDataListStyle(XSSFWorkbook workbook) {
//         // 设置字体
//         XSSFFont font = workbook.createFont();
//         // 设置字体大小
//         font.setFontHeightInPoints((short) 13);
//         // 设置字体样式
//         font.setFontName("Courier New");
//         // 设置样式;
//         XSSFCellStyle style = workbook.createCellStyle();
//         // 在样式用应用设置的字体;
//         style.setFont(font);
//         // 设置水平居中
//         style.setAlignment(HorizontalAlignment.CENTER);
//         // 设置垂直居中
//         style.setVerticalAlignment(VerticalAlignment.CENTER);
//         // 上边框
//         style.setBorderTop(BorderStyle.THIN);
//         // 下边框
//         style.setBorderBottom(BorderStyle.THIN);
//         // 左边框
//         style.setBorderLeft(BorderStyle.THIN);
//         // 右边框
//         style.setBorderRight(BorderStyle.THIN);
//         // 设置自动换行;
//         style.setWrapText(false);
//         // 设置单元格背景颜色
//         style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
//         return style;
//     }
// }
