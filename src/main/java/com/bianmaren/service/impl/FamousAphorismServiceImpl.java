package com.bianmaren.service.impl;

import com.bianmaren.dao.FamousAphorismDao;
import com.bianmaren.entity.FamousAphorism;
import com.bianmaren.service.FamousAphorismService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by bianmaren on 2016-07-23.
 * QQ:441889070
 */
@Service("famousAphorismServiceImpl")
public class FamousAphorismServiceImpl extends BaseServiceImpl<FamousAphorism, Long> implements FamousAphorismService {

    @Resource(name = "famousAphorismDaoImpl")
    public FamousAphorismDao famousAphorismDao;

    @Resource(name = "famousAphorismDaoImpl")
    public void setBaseDao(FamousAphorismDao famousAphorismDao) {
        super.setBaseDao(famousAphorismDao);
    }

}
