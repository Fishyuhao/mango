package com.louis.mango.admin.dao;

import com.louis.mango.admin.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    /**
     * 查询全部
     */
    List<SysUser> findAll();
    /**
     * 分页查询
     * @return 查询集合
     */
    List<SysUser> findPage();

    SysUser findByName(@Param(value="name") String name);

    List<SysUser> findPageByName(@Param(value="name") String name);

    List<SysUser> findPageByNameAndEmail(@Param(value="name") String name, @Param(value="email") String email);

}