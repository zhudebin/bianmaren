package com.bianmaren.service.impl;

import com.bianmaren.Filter;
import com.bianmaren.dao.NavigationDao;
import com.bianmaren.entity.Navigation;
import com.bianmaren.service.NavigationService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bianmaren on 2016-07-01.
 * QQ:441889070
 */
@Service("navigationServiceImpl")
public class NavigationServiceImpl extends BaseServiceImpl<Navigation, Long> implements NavigationService {

    @Resource(name = "navigationDaoImpl")
    private NavigationDao navigationDao;

    @Resource(name = "navigationDaoImpl")
    public void setBaseDao(NavigationDao navigationDao) {
        super.setBaseDao(navigationDao);
    }

    @Override
    @Transactional
    @CacheEvict(value = "navigations", allEntries = true)
    public void save(Navigation navigation) {
        super.save(navigation);
    }

    @Override
    @Transactional
    @CacheEvict(value = "navigations", allEntries = true)
    public Navigation update(Navigation navigation) {
        return super.update(navigation);
    }

    @Override
    @Transactional
    @CacheEvict(value = "navigations", allEntries = true)
    public Navigation update(Navigation navigation, String... ignoreProperties) {
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
    public void delete(Navigation navigation) {
        super.delete(navigation);
    }

    @Cacheable("navigations")
    public List<Navigation> getAllEnableNavigation(){
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("is_enable",true));
        List<Navigation> navigationList = findList(null,filters,null);
        return navigationList;
    }
    
}
