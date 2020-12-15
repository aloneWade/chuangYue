package com.cy.asset.thirdparty;


import com.cy.asset.common.BaseMessage;
import com.cy.asset.common.util.S3UploadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by zxb on 2016/10/19.
 */
@RestController
@RequestMapping("api/v1/upload")
public class UploadFile {

    @Autowired
    private S3UploadHelper s3UploadHelper;

    protected Logger logger = LoggerFactory.getLogger(UploadFile.class);

    @PostMapping("file")
    public BaseMessage uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String url = null;
        try {
            url = s3UploadHelper.uploadFile(file);
        } catch (IOException e) {
            logger.error("上传文件失败",e);
            e.printStackTrace();
        }
        return new BaseMessage(url);
    }


    @PostMapping("/mult-file")
    public BaseMessage uploadFile(@RequestParam("file") List<MultipartFile> file) throws IOException {
        try {
            if (file.size()>3)
                return new BaseMessage("最多上传三张图片");
            String url = null;
            if (file==null)
                return new BaseMessage("上传文件不能为空");
            for (int i = 0;i<file.size();i++){
                url = url + "," + s3UploadHelper.uploadFile(file.get(i));
            }
            if (url != null && url.length()>=5)
                return new BaseMessage(url.substring(5));
        } catch (IOException e) {
            logger.error("上传文件失败",e);
            e.printStackTrace();
        }
        return new BaseMessage("upload failed!");
    }
}
