/*
 * 
 */
package com.bianmaren;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Setting implements Serializable {

	private static final long serialVersionUID = -1478999889661796840L;
	public static final String CACHE_NAME = "setting";
	public static final Integer CACHE_KEY = 0;
	private static final String SEPARATOR = ",";


	private Integer uploadMaxSize;
	private String uploadImageExtension;
	private String uploadFlashExtension;
	private String uploadMediaExtension;
	private String uploadFileExtension;

	private String imageUploadPath;
	private String flashUploadPath;
	private String mediaUploadPath;
	private String fileUploadPath;

	private String siteName;
	private String siteUrl;
	private String systemDescription;
	private Boolean isSiteEnabled;
	private Boolean isRegisterEnabled;


	@NotNull
	@Min(0)
	public Integer getUploadMaxSize() {
		return uploadMaxSize;
	}
	public void setUploadMaxSize(Integer uploadMaxSize) {
		this.uploadMaxSize = uploadMaxSize;
	}


	@Length(max = 200)
	public String getUploadImageExtension() {
		return uploadImageExtension;
	}

	public void setUploadImageExtension(String uploadImageExtension) {
		if (uploadImageExtension != null) {
			uploadImageExtension = uploadImageExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadImageExtension = uploadImageExtension;
	}

	@Length(max = 200)
	public String getUploadFlashExtension() {
		return uploadFlashExtension;
	}

	public void setUploadFlashExtension(String uploadFlashExtension) {
		if (uploadFlashExtension != null) {
			uploadFlashExtension = uploadFlashExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadFlashExtension = uploadFlashExtension;
	}

	@Length(max = 200)
	public String getUploadMediaExtension() {
		return uploadMediaExtension;
	}

	public void setUploadMediaExtension(String uploadMediaExtension) {
		if (uploadMediaExtension != null) {
			uploadMediaExtension = uploadMediaExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadMediaExtension = uploadMediaExtension;
	}

	@Length(max = 200)
	public String getUploadFileExtension() {
		return uploadFileExtension;
	}

	public void setUploadFileExtension(String uploadFileExtension) {
		if (uploadFileExtension != null) {
			uploadFileExtension = uploadFileExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
		}
		this.uploadFileExtension = uploadFileExtension;
	}

	@NotEmpty
	@Length(max = 200)
	public String getImageUploadPath() {
		return imageUploadPath;
	}

	public void setImageUploadPath(String imageUploadPath) {
		if (imageUploadPath != null) {
			if (!imageUploadPath.startsWith("/")) {
				imageUploadPath = "/" + imageUploadPath;
			}
			if (!imageUploadPath.endsWith("/")) {
				imageUploadPath += "/";
			}
		}
		this.imageUploadPath = imageUploadPath;
	}

	@NotEmpty
	@Length(max = 200)
	public String getFlashUploadPath() {
		return flashUploadPath;
	}

	public void setFlashUploadPath(String flashUploadPath) {
		if (flashUploadPath != null) {
			if (!flashUploadPath.startsWith("/")) {
				flashUploadPath = "/" + flashUploadPath;
			}
			if (!flashUploadPath.endsWith("/")) {
				flashUploadPath += "/";
			}
		}
		this.flashUploadPath = flashUploadPath;
	}

	@NotEmpty
	@Length(max = 200)
	public String getMediaUploadPath() {
		return mediaUploadPath;
	}

	public void setMediaUploadPath(String mediaUploadPath) {
		if (mediaUploadPath != null) {
			if (!mediaUploadPath.startsWith("/")) {
				mediaUploadPath = "/" + mediaUploadPath;
			}
			if (!mediaUploadPath.endsWith("/")) {
				mediaUploadPath += "/";
			}
		}
		this.mediaUploadPath = mediaUploadPath;
	}

	@NotEmpty
	@Length(max = 200)
	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		if (fileUploadPath != null) {
			if (!fileUploadPath.startsWith("/")) {
				fileUploadPath = "/" + fileUploadPath;
			}
			if (!fileUploadPath.endsWith("/")) {
				fileUploadPath += "/";
			}
		}
		this.fileUploadPath = fileUploadPath;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getSystemDescription() {
		return systemDescription;
	}

	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}

	public Boolean getIsSiteEnabled() {
		return isSiteEnabled;
	}

	public void setIsSiteEnabled(Boolean isSiteEnabled) {
		this.isSiteEnabled = isSiteEnabled;
	}

	public Boolean getIsRegisterEnabled() {
		return isRegisterEnabled;
	}

	public void setIsRegisterEnabled(Boolean isRegisterEnabled) {
		this.isRegisterEnabled = isRegisterEnabled;
	}


	public String[] getUploadImageExtensions() {
		return StringUtils.split(uploadImageExtension, SEPARATOR);
	}

	public String[] getUploadFlashExtensions() {
		return StringUtils.split(uploadFlashExtension, SEPARATOR);
	}

	public String[] getUploadMediaExtensions() {
		return StringUtils.split(uploadMediaExtension, SEPARATOR);
	}

	public String[] getUploadFileExtensions() {
		return StringUtils.split(uploadFileExtension, SEPARATOR);
	}

}