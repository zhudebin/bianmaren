package com.bianmaren.service.impl;

import com.bianmaren.Filter;
import com.bianmaren.dao.CarouselImageDao;
import com.bianmaren.entity.CarouselImage;
import com.bianmaren.service.CarouselImageService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bianmaren on 2016-06-18.
 * QQ:441889070
 */
@Service("carouselImageServiceImpl")
public class CarouselImageServiceImpl extends BaseServiceImpl<CarouselImage, Long> implements CarouselImageService {

    @Resource(name = "carouselImageDaoImpl")
    public CarouselImageDao carouselImageDao;

    @Resource(name = "carouselImageDaoImpl")
    public void setBaseDao(CarouselImageDao carouselImageDao) {
        super.setBaseDao(carouselImageDao);
    }

    @Override
    @Transactional
    @CacheEvict(value = "carouselImage", allEntries = true)
    public void save(CarouselImage carouselImage) {
        super.save(carouselImage);
    }

    @Override
    @Transactional
    @CacheEvict(value = "carouselImage", allEntries = true)
    public CarouselImage update(CarouselImage carouselImage) {
        return super.update(carouselImage);
    }

    @Override
    @Transactional
    @CacheEvict(value = "carouselImage", allEntries = true)
    public CarouselImage update(CarouselImage carouselImage, String... ignoreProperties) {
        return super.update(carouselImage, ignoreProperties);
    }

    @Override
    @Transactional
    @CacheEvict(value = "carouselImage", allEntries = true)
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "carouselImage", allEntries = true)
    public void delete(Long... ids) {
        super.delete(ids);
    }

    @Override
    @Transactional
    @CacheEvict(value = "carouselImage", allEntries = true)
    public void delete(CarouselImage carouselImage) {
        super.delete(carouselImage);
    }

    @Cacheable("carouselImage")
    public List<CarouselImage> getAllEnableCarouselImage(){

        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("is_enable",true));
        List<CarouselImage> carouselImageList = findList(null,filters,null);
        return carouselImageList;

    }


}
