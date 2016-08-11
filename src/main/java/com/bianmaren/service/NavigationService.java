package com.bianmaren.service;

import com.bianmaren.entity.Navigation;

import java.util.List;

/**
 * Created by bianmaren on 2016-07-01.
 * QQ:441889070
 */
public interface NavigationService extends BaseService<Navigation, Long>{
    List<Navigation> getAllEnableNavigation();
}
