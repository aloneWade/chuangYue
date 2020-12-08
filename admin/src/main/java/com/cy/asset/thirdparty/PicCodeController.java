package com.cy.asset.thirdparty;

import com.cy.asset.common.exception.InvalidArgumentException;
import com.cy.asset.common.util.PictureCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by zxb on 2016/12/6.
 */
@RestController
@RequestMapping("api/v1")
public class PicCodeController {

    @Autowired
    private PictureCodeService pictureCodeService;

    /**
     * 获取图形验证码.
     * @return
     * @throws InvalidArgumentException
     */
    @GetMapping("pic/code")
    public Map getPicCode() throws InvalidArgumentException {
        return pictureCodeService.getPcrimg();
    }

}
