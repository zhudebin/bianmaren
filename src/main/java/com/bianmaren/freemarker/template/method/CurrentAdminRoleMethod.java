package com.bianmaren.freemarker.template.method;

import com.bianmaren.entity.Admin;
import com.bianmaren.entity.Role;
import com.bianmaren.service.AdminService;
import com.bianmaren.util.SpringUtils;
import com.bianmaren.util.Tools;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 *
 * 查看当前用户的角色
 * Created by bianmaren on 2016-05-02.
 * QQ:441889070
 */
@Component("currentAdminRoleMethod")
public class CurrentAdminRoleMethod implements TemplateMethodModelEx {

    @SuppressWarnings("rawtypes")
    public Object exec(List arguments) throws TemplateModelException {
        String roles = "";

        AdminService adminService = SpringUtils.getBean("adminServiceImpl",AdminService.class);
        if(null == adminService ){
            return new SimpleScalar(roles);
        }

        Admin currentAdmin = adminService.getCurrent();
        if(null == currentAdmin){
            return new SimpleScalar(roles);
        }

        Set<Role> roleSet = currentAdmin.getRoles();

        for(Role role:roleSet){
            if(Tools.vaildeParam(role)){
                roles+=role.getName();
            }
        }

        return new SimpleScalar(roles);
    }

}
