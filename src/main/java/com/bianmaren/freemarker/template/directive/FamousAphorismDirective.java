package com.bianmaren.freemarker.template.directive;

import com.bianmaren.entity.FamousAphorism;
import com.bianmaren.service.FamousAphorismService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 获取最新一句名言
 * Created by bianmaren on 2016-07-24.
 * QQ:441889070
 */
@Component("famousAphorismDirective")
public class FamousAphorismDirective extends BaseDirective {

    /** 变量名称 */
    private static final String VARIABLE_NAME = "famousAphorism";

    @Resource(name = "famousAphorismServiceImpl")
    private FamousAphorismService famousAphorismService;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {

        FamousAphorism famousAphorism = null;
        List<FamousAphorism> famousAphorisms = famousAphorismService.findList(1,null,null);

        if(null != famousAphorisms && famousAphorisms.size()>0){
            famousAphorism = famousAphorisms.get(0);
        }

        setLocalVariable(VARIABLE_NAME, famousAphorism, env, body);
    }


}
