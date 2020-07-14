package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.SysDict;
import com.louis.mango.admin.service.SysDictService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    // 保存字典
    @PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody SysDict record) {
        return HttpResult.ok(sysDictService.save(record));
    }

    // 删除字典
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysDict> records) {
        return HttpResult.ok(sysDictService.delete(records));
    }

    // 分页查询
    @PreAuthorize("hasAuthority('sys:dict:view')")
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(sysDictService.findPage(pageRequest));
    }

    //  根绝标签查询
    @PreAuthorize("hasAuthority('sys:dict:view')")
    @GetMapping(value="/findByLable")
    public HttpResult findByLable(@RequestParam String lable) {
        return HttpResult.ok(sysDictService.findByLable(lable));
    }
}
