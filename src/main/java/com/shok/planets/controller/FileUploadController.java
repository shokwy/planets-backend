package com.shok.planets.controller;

import com.shok.planets.common.BaseResponse;
import com.shok.planets.common.ResultUtils;
import com.shok.planets.utils.TencentOssUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/file")
public class FileUploadController {
    @Autowired
    private TencentOssUtil tencentOssUtil;
    @ApiOperation( "文件上传")
    @PostMapping("upload")
    public BaseResponse upload(@ApiParam MultipartFile file){
        String upload = tencentOssUtil.upload(file);
        return ResultUtils.success(upload);
    }
}
