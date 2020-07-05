package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.service.SysMenuService;
import com.louis.mango.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单控制器
 */
@RestController
@RequestMapping("menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    // 保存菜单
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody SysMenu record) {
        return HttpResult.ok(sysMenuService.save(record));
    }

    // 删除菜单
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysMenu> records) {
        return HttpResult.ok(sysMenuService.delete(records));
    }

    // 查询导航菜单树
    @GetMapping(value="/findNavTree")
    public HttpResult findNavTree(@RequestParam String userName) {
        return HttpResult.ok(sysMenuService.findTree(userName, 1));
    }

    // 查询菜单树
    @GetMapping(value="/findMenuTree")
    public HttpResult findMenuTree() {
        return HttpResult.ok(sysMenuService.findTree(null, 0));
    }
}
