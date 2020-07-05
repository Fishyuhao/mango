package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysDict;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;

import java.util.List;

/**
 * 字典管理
 */
public interface SysDictService {

    int save(SysDict record);
    int delete(SysDict record);
    int delete(List<SysDict> records);
    SysDict findById(Long id);
    PageResult findPage(PageRequest pageRequest);


    /**
     * 根据名称查询
     * @param lable
     * @return
     */
    List<SysDict> findByLable(String lable);
}
