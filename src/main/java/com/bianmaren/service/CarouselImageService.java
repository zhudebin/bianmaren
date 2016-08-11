package com.bianmaren.service;

import com.bianmaren.entity.CarouselImage;

import java.util.List;

/**
 * Created by bianmaren on 2016-06-18.
 * QQ:441889070
 */
public interface CarouselImageService extends BaseService<CarouselImage, Long> {
    List<CarouselImage> getAllEnableCarouselImage();
}
