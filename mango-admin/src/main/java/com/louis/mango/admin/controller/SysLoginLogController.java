package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.SysLoginLog;
import com.louis.mango.admin.service.SysLoginLogService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登录日志控制器
 */
@RestController
@RequestMapping("loginlog")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;

    // 分页查询
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysLoginLogService.findPage(pageRequest));
    }

    // 删除操作日志
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysLoginLog> records) {
        return HttpResult.ok(sysLoginLogService.delete(records));
    }
}
