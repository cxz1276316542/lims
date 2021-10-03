package com.ruoyi.review.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.detection.service.IReportService;
import com.ruoyi.review.service.IReviewService;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zjccc
 * @date 2021/8/29
 * @Email:648629522@qq.com
 */
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IReviewService reviewService;

    /**
     * 上传电子签章
     */
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadSignature")
    public AjaxResult uploadSignature(@RequestParam("signature") MultipartFile file) throws IOException {
        if(null != file){
            String signature = FileUploadUtils.upload(RuoYiConfig.getElectronicSignatures(),file);
            SysUser user = new SysUser();
            //更新用户信息
            user.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
            user.setElectronicSignature(signature);
            sysUserService.updateUserProfile(user);
            return AjaxResult.success("上传电子签章成功",signature);
        }
        return AjaxResult.error("上传电子签章异常");
    }

    /**
     * 获取当前用户电子签章
     */
    @GetMapping("/getUserElectronicSignature")
    public AjaxResult getUserElectronicSignature()
    {
        return AjaxResult.success("",sysUserService.selectUserById(SecurityUtils.getLoginUser().getUser().getUserId()).getElectronicSignature());
    }

    /**
     * 获取已审核信息
     */
    @GetMapping(value = "/getReviewInfo/{reviewID}")
    public AjaxResult getReviewInfo(@PathVariable("reviewID") Long reviewID)
    {
        return AjaxResult.success(reviewService.selectReviewUpdateInfoById(reviewID));
    }

    /**
     * 查询系统用户列表
     */
    @GetMapping("/userList")
    public AjaxResult getUserList()
    {
        SysUser user = new SysUser();
        //user.set  用户类型为00 系统用户
        Map<Long,String> map = new HashMap<>();
        List<SysUser> userList = sysUserService.selectUserList(user);
        for (SysUser user1:userList){
            map.put(user1.getUserId(),user1.getNickName());
        }
        return AjaxResult.success(map);
    }

}
