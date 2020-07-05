package com.louis.mango.admin.controller;

import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.service.SysUserService;
import com.louis.mango.admin.utils.PasswordUtils;
import com.louis.mango.common.utils.FileUtils;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    // 保存记录
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody SysUser record) {
        SysUser user = sysUserService.findById(record.getId());
        if(user != null) {
            if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
                return HttpResult.error("超级管理员不允许修改!");
            }
        }
        if(record.getPassword() != null) {
            String salt = PasswordUtils.getSalt();
            if(user == null) {
                // 新增用户
                if(sysUserService.findByName(record.getName()) != null) {
                    return HttpResult.error("用户名已存在!");
                }
                String password = PasswordUtils.encode(record.getPassword(), salt);
                record.setSalt(salt);
                record.setPassword(password);
            } else {
                // 修改用户, 且修改了密码
                if(!record.getPassword().equals(user.getPassword())) {
                    String password = PasswordUtils.encode(record.getPassword(), salt);
                    record.setSalt(salt);
                    record.setPassword(password);
                }
            }
        }
        return HttpResult.ok(sysUserService.save(record));
    }
    // 删除用户
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysUser> records) {
        for(SysUser record:records) {
            SysUser sysUser = sysUserService.findById(record.getId());
            if(sysUser != null && SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())) {
                return HttpResult.error("超级管理员不允许删除!");
            }
        }
        return HttpResult.ok(sysUserService.delete(records));
    }
    // 根据名称查询
    @GetMapping(value="/findByName")
    public HttpResult findByName(@RequestParam String name) {
        return HttpResult.ok(sysUserService.findByName(name));
    }

    // 查询用户权限
    @GetMapping(value="/findPermissions")
    public HttpResult findPermissions(@RequestParam String name) {
        return HttpResult.ok(sysUserService.findPermissions(name));
    }

    // 查询用户角色
    @GetMapping(value="/findUserRoles")
    public HttpResult findUserRoles(@RequestParam Long userId) {
        return HttpResult.ok(sysUserService.findUserRoles(userId));
    }
    @GetMapping(value="/findAll")
    public Object findAll(){
        return sysUserService.findAll();
    }
    // 分页查询
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(sysUserService.findPage(pageRequest));
    }
    @PostMapping(value="/exportExcelUser")
    public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) {
        File file = sysUserService.createUserExcelFile(pageRequest);
        FileUtils.downloadFile(res, file, file.getName());
    }
}
