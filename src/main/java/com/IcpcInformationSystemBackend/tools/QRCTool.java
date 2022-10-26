package com.IcpcInformationSystemBackend.tools;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Component
public class QRCTool {
    private static final Logger log= LoggerFactory.getLogger(QRCTool.class);

    //CODE_WIDTH：二维码宽度，单位像素
    private static final int CODE_WIDTH = 400;
    //CODE_HEIGHT：二维码高度，单位像素
    private static final int CODE_HEIGHT = 400;
    //FRONT_COLOR：二维码前景色，0x000000 表示黑色
    private static final int FRONT_COLOR = 0x000000;
    //BACKGROUND_COLOR：二维码背景色，0xFFFFFF 表示白色
    //演示用 16 进制表示，和前端页面 CSS 的取色是一样的，注意前后景颜色应该对比明显，如常见的黑白
    private static final int BACKGROUND_COLOR = 0xFFFFFF;

    public void createCodeToFile(String content, File codeImgFileSaveDir, String fileName) {
        try {
            if (Objects.equals(content, "") || Objects.equals(fileName, "")) {
                return;
            }
            content = content.trim();
            if (codeImgFileSaveDir==null || codeImgFileSaveDir.isFile()) {
                //二维码图片存在目录为空，默认放在桌面...
                codeImgFileSaveDir = FileSystemView.getFileSystemView().getHomeDirectory();
            }
            if (!codeImgFileSaveDir.exists()) {
                //二维码图片存在目录不存在，开始创建...
                codeImgFileSaveDir.mkdirs();
            }

            //核心代码-生成二维码
            BufferedImage bufferedImage = getBufferedImage(content);

            File codeImgFile = new File(codeImgFileSaveDir, fileName);
            ImageIO.write(bufferedImage, "png", codeImgFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void createCodeToResponse(HttpServletResponse response, String content) {
        try {
            if (Objects.equals(content, "")) {
                return;
            }
            content = content.trim();
            //核心代码-生成二维码
            BufferedImage bufferedImage = getBufferedImage(content);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            ImageIO.write(bufferedImage, "png", out);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("image/jpeg;charset=UTF-8");
            response.setContentLength(out.size());
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(out.toByteArray());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //核心代码-生成二维码
    private BufferedImage getBufferedImage(String content) throws WriterException {

        //com.google.zxing.EncodeHintType：编码提示类型,枚举类型
        Map<EncodeHintType, Object> hints = new HashMap();

        //EncodeHintType.CHARACTER_SET：设置字符编码类型
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        //EncodeHintType.ERROR_CORRECTION：设置误差校正
        //ErrorCorrectionLevel：误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
        //不设置时，默认为 L 等级，等级不一样，生成的图案不同，但扫描的结果是一样的
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        //EncodeHintType.MARGIN：设置二维码边距，单位像素，值越小，二维码距离四周越近
        hints.put(EncodeHintType.MARGIN, 1);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, CODE_WIDTH, CODE_HEIGHT, hints);
        BufferedImage bufferedImage = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < CODE_WIDTH; x++) {
            for (int y = 0; y < CODE_HEIGHT; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
            }
        }
        return bufferedImage;
    }
}
