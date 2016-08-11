package com.bianmaren.service.impl;

import com.bianmaren.dao.NavigationChildDao;
import com.bianmaren.entity.NavigationChild;
import com.bianmaren.service.NavigationChildService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by bianmaren on 2016-07-01.
 * QQ:441889070
 */
@Service("navigationChildServiceImpl")
public class NavigationChildServiceImpl extends BaseServiceImpl<NavigationChild, Long> implements NavigationChildService {

    @Resource(name = "navigationChildDaoImpl")
    private NavigationChildDao navigationChildDao;

    @Resource(name = "navigationChildDaoImpl")
    public void setBaseDao(NavigationChildDao navigationChildDao) {
        super.setBaseDao(navigationChildDao);
    }


    @Override
    @Transactional
    @CacheEvict(value = "navigations", allEntries = true)
    public void save(NavigationChild navigation) {
        super.save(navigation);
    }

    @Override
    @Transactional
    @CacheEvict(value = "navigations", allEntries = true)
    public NavigationChild update(NavigationChild navigation) {
        return super.update(navigation);
    }

    @Override
    @Transactional
    @CacheEvict(value = "navigations", allEntries = true)
    public NavigationChild update(NavigationChild navigation, String... ignoreProperties) {
        return super.update(navigation, ignoreProperties);
    }

    @Override
    @Transactional
    @CacheEvict(value = "navigations", allEntries = true)
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "navigations", allEntries = true)
    public void delete(Long... ids) {
        super.delete(ids);
    }

    @Override
    @Transactional
    @CacheEvict(value = "navigations", allEntries = true)
    public void delete(NavigationChild navigation) {
        super.delete(navigation);
    }


}
