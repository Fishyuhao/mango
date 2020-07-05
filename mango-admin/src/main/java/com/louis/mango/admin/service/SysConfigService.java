package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysConfig;
import com.louis.mango.core.service.CurdService;

import java.util.List;

public interface SysConfigService extends CurdService<SysConfig> {
    /**
     * 根据名称查询
     * @param lable
     * @return
     */
    List<SysConfig> findByLable(String lable);
}
