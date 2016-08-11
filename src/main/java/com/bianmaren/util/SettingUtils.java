
package com.bianmaren.util;

import com.bianmaren.CommonAttributes;
import com.bianmaren.EnumConverter;
import com.bianmaren.Setting;
import com.bianmaren.entity.SysSetting;
import com.bianmaren.service.SysSettingService;
import net.sf.ehcache.CacheManager;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public final class SettingUtils {

	private static final CacheManager cacheManager = CacheManager.create();

	private static final BeanUtilsBean beanUtils;

	static {
		ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean() {
			@Override
			public String convert(Object value) {
				if (value != null) {
					Class<?> type = value.getClass();
					if (type.isEnum() && super.lookup(type) == null) {
						super.register(new EnumConverter(type), type);
					} else if (type.isArray() && type.getComponentType().isEnum()) {
						if (super.lookup(type) == null) {
							ArrayConverter arrayConverter = new ArrayConverter(type, new EnumConverter(type.getComponentType()), 0);
							arrayConverter.setOnlyFirstToString(false);
							super.register(arrayConverter, type);
						}
						Converter converter = super.lookup(type);
						return ((String) converter.convert(String.class, value));
					}
				}
				return super.convert(value);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public Object convert(String value, Class clazz) {
				if (clazz.isEnum() && super.lookup(clazz) == null) {
					super.register(new EnumConverter(clazz), clazz);
				}
				return super.convert(value, clazz);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public Object convert(String[] values, Class clazz) {
				if (clazz.isArray() && clazz.getComponentType().isEnum() && super.lookup(clazz.getComponentType()) == null) {
					super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
				}
				return super.convert(values, clazz);
			}

			@SuppressWarnings("rawtypes")
			@Override
			public Object convert(Object value, Class targetType) {
				if (super.lookup(targetType) == null) {
					if (targetType.isEnum()) {
						super.register(new EnumConverter(targetType), targetType);
					} else if (targetType.isArray() && targetType.getComponentType().isEnum()) {
						ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
						arrayConverter.setOnlyFirstToString(false);
						super.register(arrayConverter, targetType);
					}
				}
				return super.convert(value, targetType);
			}
		};

		DateConverter dateConverter = new DateConverter();
		dateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
		convertUtilsBean.register(dateConverter, Date.class);
		beanUtils = new BeanUtilsBean(convertUtilsBean);
	}

	private SettingUtils() {
	}

	public static Setting get(){
		Setting setting = new Setting();
		try {
			SysSettingService sysSettingService = SpringUtils.getBean("sysSettingServiceImpl",SysSettingService.class);
			List<SysSetting> sysSettingList = sysSettingService.getAllSetting();
			for (SysSetting element : sysSettingList) {
				String name = element.getName();
				String value = element.getValue();
				try {
					beanUtils.setProperty(setting, name, value);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setting;
	}


	public static void set(String name,String key) {
		try {
			SysSettingService sysSettingService = SpringUtils.getBean("sysSettingServiceImpl",SysSettingService.class);
			SysSetting sysSetting = sysSettingService.getSettingByName(name);
			if(null != sysSetting){
				sysSetting.setValue(key);
				sysSettingService.update(sysSetting);
			}else{
				sysSetting = new SysSetting();
				sysSetting.setSettingType(SysSetting.SettingType.setting);
				sysSetting.setIs_system(false);
				sysSetting.setName(name);
				sysSetting.setValue(key);
				sysSettingService.save(sysSetting);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}