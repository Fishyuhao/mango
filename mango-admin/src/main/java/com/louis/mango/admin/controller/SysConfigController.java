package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.SysConfig;
import com.louis.mango.admin.service.SysConfigService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统配置控制器
 */
@RestController
@RequestMapping("config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    // 保存配置
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody SysConfig record) {
        return HttpResult.ok(sysConfigService.save(record));
    }

    // 删除字典
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysConfig> records) {
        return HttpResult.ok(sysConfigService.delete(records));
    }

    // 分页查询
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysConfigService.findPage(pageRequest));
    }

    // 根据标签查询
    @GetMapping(value="/findByLable")
    public HttpResult findByLable(@RequestParam String lable) {
        return HttpResult.ok(sysConfigService.findByLable(lable));
    }
}
