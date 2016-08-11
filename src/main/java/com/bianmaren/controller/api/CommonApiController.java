package com.bianmaren.controller.api;


import com.bianmaren.controller.BaseController;
import com.bianmaren.util.PropertiesUtils;
import com.bianmaren.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller("commonApiController")
@RequestMapping("/api/common")
public class CommonApiController extends BaseController implements ServletContextAware{

	private final String contentType = "image/png";

	private static Map<String, String> resultMap = new HashMap<>();
	
	/** servletContext */
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	

	/**
	 * Description: 上传图片接口
	 */
	@RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<String> uploadimage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ApiResult<String> result = new ApiResult<>();

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		String uploadDir =  servletContext.getRealPath("/upload/images");
		String storeName = "";
		String name = "";
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet()
				.iterator(); it.hasNext();) {

			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();

			//文件名后缀
			String oldName = mFile.getOriginalFilename();
			String prefix=oldName.substring(oldName.lastIndexOf(".")+1);

			//随机文件名
			name = StringUtils.randomString(10)+System.currentTimeMillis()+"."+prefix;
			storeName = uploadDir + "/" +name;

			File mfile = new File(storeName);
			if (!mfile.exists()) {
				File filedir = new File(uploadDir);
				if (filedir != null && !filedir.exists()) {
					filedir.mkdirs();
				}
				mfile.createNewFile();
			}
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(mfile);
				FileCopyUtils.copy(mFile.getInputStream(), out);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}

		}
		String fullUrl = PropertiesUtils.getValue("system.official.site")+"upload/images/"+name;
		result.setData(fullUrl);

		return result;
	}



}
