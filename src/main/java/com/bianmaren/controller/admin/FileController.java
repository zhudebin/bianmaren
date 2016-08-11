package com.bianmaren.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bianmaren.FileInfo;
import com.bianmaren.FileInfo.FileType;
import com.bianmaren.FileInfo.OrderType;
import com.bianmaren.Message;
import com.bianmaren.controller.BaseController;
import com.bianmaren.service.FileService;
import com.bianmaren.util.JsonUtils;

/**
 * Controller - 文件处理
 */
@Controller("adminFileController")
@RequestMapping("/admin/file")
public class FileController extends BaseController {

	@Resource(name = "fileServiceImpl")
	private FileService fileService;

	/**
	 * 上传
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public void upload(FileType fileType, MultipartFile file, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (!fileService.isValid(fileType, file)) {
			data.put("message", Message.warn("admin.upload.invalid"));
		} else {
			String url = fileService.upload(fileType, file, false);
			if (url == null) {
				data.put("message", Message.warn("admin.upload.error"));
			} else {
				data.put("message", SUCCESS_MESSAGE);
				data.put("url", url);
			}
		}
		try {
			response.setContentType("text/html; charset=UTF-8");
			JsonUtils.writeValue(response.getWriter(), data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 浏览
	 */
	@RequestMapping(value = "/browser", method = RequestMethod.GET)
	@ResponseBody
	public String browser(String path, FileType fileType, OrderType orderType) {
		List<FileInfo> files = fileService.browser(path, fileType, orderType);
		return JSON.toJSONString(files);
	}

	
	@RequestMapping(value = "/ckeditUpload", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String ckeditUpload(FileType fileType, @RequestParam(value="upload",required=false) MultipartFile image, HttpServletResponse response,HttpServletRequest request) {
		
		String result="<p>upload failed!</p>";
		
		String callback = request.getParameter("CKEditorFuncNum"); 
		
		if (fileService.isValid(fileType, image)){
			
			String url = fileService.upload(fileType, image, false);
			
			StringBuffer string = new StringBuffer();
			string.append("<p>");
			string.append("upload success!");
			string.append("</p>");
			
			string.append("<script type=\"text/javascript\">");
			string.append("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'"+url+"','')"); 
			string.append("</script>");
			
			result = string.toString();
			
			try {
				String utf8 = new String(result.getBytes( "UTF-8"));  
				System.out.println(utf8);  
				String unicode = new String(utf8.getBytes(),"UTF-8");   
				System.out.println(unicode);  
				String gbk = new String(unicode.getBytes("GBK"));  
				result = gbk;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
}