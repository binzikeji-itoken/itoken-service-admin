package com.binzikeji.itoken.service.admin.controller;

import com.binzikeji.itoken.common.domain.TbPostsPost;
import com.binzikeji.itoken.common.domain.TbSysUser;
import com.binzikeji.itoken.common.dto.BaseRestult;
import com.binzikeji.itoken.common.utils.MapperUtils;
import com.binzikeji.itoken.service.admin.service.AdminService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Author Bin
 * @Date 2019/4/10 18:09
 **/
@RestController
@RequestMapping(value = "v1/admin")
public class AdminController {

    @Autowired
    private AdminService<TbSysUser> adminService;

    @ApiOperation(value = "根据 userCode 查询文章")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "userCode", value = "userCode 文章id", required = true, dataTypeClass = String.class, paramType = "path")
    )
    @RequestMapping(value = "{userCode}", method = RequestMethod.GET)
    public BaseRestult get(@PathVariable(value = "userCode") String userCode){
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUserCode(userCode);
        TbSysUser obj = adminService.selectOne(tbSysUser);
        return BaseRestult.ok(obj);
    }

    @ApiOperation(value = "保存文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tbAdminJson", value = "文章对象", required = true, dataTypeClass = String.class, paramType = "json"),
            @ApiImplicitParam(name = "optsBy", value = "保存人", required = true, dataTypeClass = String.class)
    })
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public BaseRestult save(
            @RequestParam(required = true) String tbAdminJson,
            @RequestParam(required = true) String optsBy
    ){
        int result = 0;

        TbSysUser tbSysUser = null;

        try {
            tbSysUser = MapperUtils.json2pojo(tbAdminJson, TbSysUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (tbSysUser != null){
            // 主键为空,表示新增
            if (StringUtils.isBlank(tbSysUser.getUserCode())){
                tbSysUser.setUserCode(UUID.randomUUID().toString());
                result = adminService.insert(tbSysUser, optsBy);
            }

            // 编辑
            else {
                result = adminService.update(tbSysUser, optsBy);
            }

            if (result > 0){
                return BaseRestult.ok("保存文章成功");
            }
        }
        return BaseRestult.ok("保存文章失败");
    }

    @ApiOperation(value = "文章分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "tbAdmainJson", value = "文章对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseRestult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String tbAdmainJson
    ){
        TbSysUser tbSysUser = null;
        if (StringUtils.isNotBlank(tbAdmainJson)){
            try {
                tbSysUser = MapperUtils.json2pojo(tbAdmainJson, TbSysUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PageInfo<TbSysUser> pageInfo = adminService.page(pageNum, pageSize, tbSysUser);
        List<TbSysUser> list = pageInfo.getList();
        BaseRestult.Cursor cursor = new BaseRestult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());

        return BaseRestult.ok(list, cursor);

    }
}
