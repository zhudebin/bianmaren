package com.bianmaren.controller.admin;

import com.bianmaren.Message;
import com.bianmaren.Pageable;
import com.bianmaren.controller.BaseController;
import com.bianmaren.entity.CarouselImage;
import com.bianmaren.service.CarouselImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Created by dengwenwu on 2016-06-20.
 * QQ:122011504
 */
@Controller("adminCarouselImageController")
@RequestMapping("/admin/carousel_images")
public class CarouselImageController extends BaseController {

    @Resource(name = "carouselImageServiceImpl")
    private CarouselImageService carouselImageService;

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        return "/admin/carousel_images/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(CarouselImage carouselImage, RedirectAttributes redirectAttributes) {
        if (!isValid(carouselImage)) {
            return ERROR_VIEW;
        }
        if(null==carouselImage.getIs_enable()){
            carouselImage.setIs_enable(false);
        }
        carouselImageService.save(carouselImage);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        model.addAttribute("carouselImage", carouselImageService.find(id));
        return "/admin/carousel_images/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(CarouselImage carouselImage, RedirectAttributes redirectAttributes) {
        if (!isValid(carouselImage)) {
            return ERROR_VIEW;
        }
        if(null==carouselImage.getIs_enable()){
            carouselImage.setIs_enable(false);
        }
        carouselImageService.update(carouselImage);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.html";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("page", carouselImageService.findPage(pageable));
        return "/admin/carousel_images/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids) {
        carouselImageService.delete(ids);
        return SUCCESS_MESSAGE;
    }


}
