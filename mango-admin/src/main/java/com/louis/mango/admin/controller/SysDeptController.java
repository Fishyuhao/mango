package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.SysDept;
import com.louis.mango.admin.service.SysDeptService;
import com.louis.mango.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构构造器
 */
@RestController
@RequestMapping("dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    // 保存机构
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody SysDept record) {
        return HttpResult.ok(sysDeptService.save(record));
    }

    // 删除机构
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysDept> records) {
        return HttpResult.ok(sysDeptService.delete(records));
    }

    // 查询机构树
    @GetMapping(value="/findTree")
    public HttpResult findTree() {
        return HttpResult.ok(sysDeptService.findTree());
    }
}
