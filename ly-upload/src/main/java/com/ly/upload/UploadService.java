package com.ly.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName: UploadService
 * @Author: Bolon
 * @Date: 2019/12/8 21:50
 * @Description: TODO
 */
@Service
public class UploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //校验文件是否是图片
        String contentType = file.getContentType();
        // if (!uploadProperties.getAllowTypes().contains(contentType)) {
        //     logger.info("上传的不是图片文件" + contentType);
        //     return null;
        // }
        try {
            //校验文件内容是否符合图片规范
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                logger.info("上传的文件不符合规范：{}", originalFilename);
                return null;
            }
            file.transferTo(new File("D:\\projects\\image\\" + originalFilename));
            return "http://image.leyou.com/" + originalFilename;
        } catch (IOException e) {
            logger.info("服务器内部错误：" + e);
            e.printStackTrace();
        }
        return null;
    }
}
