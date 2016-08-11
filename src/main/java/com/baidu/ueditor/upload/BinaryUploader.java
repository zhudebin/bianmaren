package com.baidu.ueditor.upload;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BinaryUploader {

	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
		try {
			String rootPath = request.getSession().getServletContext().getRealPath("/");

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());

			String savePath = (String) conf.get("savePath");
			String originFileName = multipartFile.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			String saveFullPath = rootPath + savePath;

			State storageState = StorageManager.saveFileByInputStream(multipartFile.getInputStream(),saveFullPath, maxSize);


			if (storageState.isSuccess()) {
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
