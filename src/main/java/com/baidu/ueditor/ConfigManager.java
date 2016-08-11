package com.baidu.ueditor;

import com.alibaba.fastjson.JSON;
import com.baidu.ueditor.define.ActionMap;
import com.bianmaren.util.SettingUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置管理器
 * @author hancong03@baidu.com
 *
 */
public final class ConfigManager {

	private final String rootPath;
	private final String originalPath;
	private final String contextPath;
	private static final String configFileName = "config.json";
	private String parentPath = null;
	private JSONObject jsonConfig = null;
	// 涂鸦上传filename定义
	private final static String SCRAWL_FILE_NAME = "scrawl";
	// 远程图片抓取filename定义
	private final static String REMOTE_FILE_NAME = "remote";
	
	/*
	 * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
	 */
	private ConfigManager ( String rootPath, String contextPath, String uri ) throws FileNotFoundException, IOException {
		
		rootPath = rootPath.replace( "\\", "/" );
		
		this.contextPath = contextPath;
		
		this.rootPath = rootPath;
		
		this.originalPath = this.rootPath + uri;
		
//		this.initEnv();

		this.initEnvNew();
	}
	
	/**
	 * 配置管理器构造工厂
	 * @param rootPath 服务器根路径
	 * @param contextPath 服务器所在项目路径
	 * @param uri 当前访问的uri
	 * @return 配置管理器实例或者null
	 */
	public static ConfigManager getInstance ( String rootPath, String contextPath, String uri ) {
		
		try {
			return new ConfigManager(rootPath, contextPath, uri);
		} catch ( Exception e ) {
			return null;
		}
		
	}
	
	// 验证配置文件加载是否正确
	public boolean valid () {
		return this.jsonConfig != null;
	}
	
	public JSONObject getAllConfig () {
		
		return this.jsonConfig;
		
	}
	
	public Map<String, Object> getConfig ( int type ) {
		
		Map<String, Object> conf = new HashMap<String, Object>();
		String savePath = null;
		
		switch ( type ) {
		
			case ActionMap.UPLOAD_FILE:
				conf.put( "isBase64", "false" );
				conf.put( "maxSize", this.jsonConfig.getLong( "fileMaxSize" ) );
				conf.put( "allowFiles", this.getArray( "fileAllowFiles" ) );
				conf.put( "fieldName", this.jsonConfig.getString( "fileFieldName" ) );
				savePath = this.jsonConfig.getString( "filePathFormat" );
				break;
				
			case ActionMap.UPLOAD_IMAGE:
				conf.put( "isBase64", "false" );
				conf.put( "maxSize", this.jsonConfig.getLong( "imageMaxSize" ) );
				conf.put( "allowFiles", this.getArray( "imageAllowFiles" ) );
				conf.put( "fieldName", this.jsonConfig.getString( "imageFieldName" ) );
				savePath = this.jsonConfig.getString( "imagePathFormat" );
				break;
				
			case ActionMap.UPLOAD_VIDEO:
				conf.put( "maxSize", this.jsonConfig.getLong( "videoMaxSize" ) );
				conf.put( "allowFiles", this.getArray( "videoAllowFiles" ) );
				conf.put( "fieldName", this.jsonConfig.getString( "videoFieldName" ) );
				savePath = this.jsonConfig.getString( "videoPathFormat" );
				break;
				
			case ActionMap.UPLOAD_SCRAWL:
				conf.put( "filename", ConfigManager.SCRAWL_FILE_NAME );
				conf.put( "maxSize", this.jsonConfig.getLong( "scrawlMaxSize" ) );
				conf.put( "fieldName", this.jsonConfig.getString( "scrawlFieldName" ) );
				conf.put( "isBase64", "true" );
				savePath = this.jsonConfig.getString( "scrawlPathFormat" );
				break;
				
			case ActionMap.CATCH_IMAGE:
				conf.put( "filename", ConfigManager.REMOTE_FILE_NAME );
				conf.put( "filter", this.getArray( "catcherLocalDomain" ) );
				conf.put( "maxSize", this.jsonConfig.getLong( "catcherMaxSize" ) );
				conf.put( "allowFiles", this.getArray( "catcherAllowFiles" ) );
				conf.put( "fieldName", this.jsonConfig.getString( "catcherFieldName" ) + "[]" );
				savePath = this.jsonConfig.getString( "catcherPathFormat" );
				break;
				
			case ActionMap.LIST_IMAGE:
				conf.put( "allowFiles", this.getArray( "imageManagerAllowFiles" ) );
				conf.put( "dir", this.jsonConfig.getString( "imageManagerListPath" ) );
				conf.put( "count", this.jsonConfig.getInt( "imageManagerListSize" ) );
				break;
				
			case ActionMap.LIST_FILE:
				conf.put( "allowFiles", this.getArray( "fileManagerAllowFiles" ) );
				conf.put( "dir", this.jsonConfig.getString( "fileManagerListPath" ) );
				conf.put( "count", this.jsonConfig.getInt( "fileManagerListSize" ) );
				break;
				
		}
		
		conf.put( "savePath", savePath );
		conf.put( "rootPath", this.rootPath );
		
		return conf;
		
	}
	
	/**
     * Get rootPath from request,if not,find it from conf map.
     * @param request
     * @param conf
     * @return
     * @author Ternence
     * @create 2015年1月31日
     */
    public static String getRootPath(HttpServletRequest request, Map<String, Object> conf) {
        Object rootPath = request.getAttribute("rootPath");
        if (rootPath != null) {
            return rootPath + "" + File.separatorChar;
        } else {
            return conf.get("rootPath") + "";
        }
    }

    private void initEnv () throws FileNotFoundException, IOException {
		
		File file = new File( this.originalPath );
		
		if ( !file.isAbsolute() ) {
			file = new File( file.getAbsolutePath() );
		}
		
		this.parentPath = file.getParent();
		
		String configContent = this.readFile( this.getConfigPath() );
		
		try{
			JSONObject jsonConfig = new JSONObject( configContent );

			this.jsonConfig = jsonConfig;
		} catch ( Exception e ) {
			this.jsonConfig = null;
		}
		
	}

	private void initEnvNew (){

		Map<String,Object> result = new HashMap<>();

        /* 上传图片配置项 */
		result.put("imageActionName","uploadimage"); /* 执行上传图片的action名称 */
		result.put("imageFieldName","imageFieldName"); /* 提交的图片表单名称 */
		result.put("imageMaxSize",2048000); /* 上传大小限制，单位B */
		result.put("imageAllowFiles",new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"}); /* 上传图片格式显示 */
		result.put("imageCompressEnable",true); /* 是否压缩图片,默认是true */
		result.put("imageCompressBorder",1600); /* 图片压缩最长边限制 */
		result.put("imageInsertAlign","none"); /* 插入的图片浮动方式 */
		result.put("imageUrlPrefix", SettingUtils.get().getSiteUrl()); /* 图片访问路径前缀 */
		result.put("imagePathFormat", "/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}"); /* 上传保存路径,可以自定义保存路径和文件名格式 */

        /* 涂鸦图片上传配置项 */
		result.put("scrawlActionName","uploadscrawl");/* 执行上传涂鸦的action名称 */
		result.put("scrawlFieldName","upfile");/* 提交的图片表单名称 */
		result.put("scrawlPathFormat","/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");/* 上传保存路径,可以自定义保存路径和文件名格式 */
		result.put("scrawlMaxSize",2048000);/* 上传大小限制，单位B */
		result.put("scrawlUrlPrefix",SettingUtils.get().getSiteUrl());/* 图片访问路径前缀 */
		result.put("scrawlInsertAlign","none");

        /* 截图工具上传 */
		result.put("snapscreenActionName","uploadimage");/* 执行上传截图的action名称 */
		result.put("snapscreenPathFormat","/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");/* 上传保存路径,可以自定义保存路径和文件名格式 */
		result.put("snapscreenUrlPrefix",SettingUtils.get().getSiteUrl()); /* 图片访问路径前缀 */
		result.put("snapscreenInsertAlign","none");

        /* 抓取远程图片配置 */
		result.put("catcherLocalDomain",new String[]{"127.0.0.1", "localhost", "img.baidu.com"});
		result.put("catcherActionName","catchimage"); /* 执行抓取远程图片的action名称 */
		result.put("catcherFieldName","source"); /* 提交的图片列表表单名称 */
		result.put("catcherPathFormat","/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}"); /* 上传保存路径,可以自定义保存路径和文件名格式 */
		result.put("catcherUrlPrefix",SettingUtils.get().getSiteUrl());/* 图片访问路径前缀 */
		result.put("catcherMaxSize",2048000);/* 上传大小限制，单位B */
		result.put("catcherAllowFiles",new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});/* 抓取图片格式显示 */


        /* 上传视频配置 */
		result.put("videoActionName","uploadvideo"); /* 执行上传视频的action名称 */
		result.put("videoFieldName","upfile"); /* 提交的视频表单名称 */
		result.put("videoPathFormat","/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}"); /* 上传保存路径,可以自定义保存路径和文件名格式 */
		result.put("videoUrlPrefix", SettingUtils.get().getSiteUrl()); /* 视频访问路径前缀 */
		result.put("videoMaxSize",102400000); /* 上传大小限制，单位B，默认100MB */
		result.put("videoAllowFiles",new String[]{".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
				".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"}); /* 上传视频格式显示 */


        /* 上传文件配置 */
		result.put("fileActionName","uploadfile"); /* controller里,执行上传视频的action名称 */
		result.put("fileFieldName","upfile"); /* 提交的文件表单名称 */
		result.put("filePathFormat","/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}"); /* 上传保存路径,可以自定义保存路径和文件名格式 */
		result.put("fileUrlPrefix", SettingUtils.get().getSiteUrl()); /* 文件访问路径前缀 */
		result.put("fileMaxSize", 51200000); /* 上传大小限制，单位B，默认50MB */
		result.put("fileAllowFiles",new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp",
				".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
				".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
				".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
				".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"});

         /* 列出指定目录下的图片 */
		result.put("imageManagerActionName", "listimage"); /* 执行图片管理的action名称 */
		result.put("imageManagerListPath", "/upload/image/"); /* 指定要列出图片的目录 */
		result.put( "imageManagerListSize", 20); /* 每次列出文件数量 */
		result.put("imageManagerUrlPrefix", SettingUtils.get().getSiteUrl()); /* 图片访问路径前缀 */
		result.put("imageManagerInsertAlign", "none"); /* 插入的图片浮动方式 */
		result.put("imageManagerAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"}); /* 列出的文件类型 */

        /* 列出指定目录下的文件 */
		result.put("fileManagerActionName", "listfile"); /* 执行文件管理的action名称 */
		result.put("fileManagerListPath", "/upload/file/"); /* 指定要列出文件的目录 */
		result.put("fileManagerUrlPrefix", SettingUtils.get().getSiteUrl()); /* 文件访问路径前缀 */
		result.put("fileManagerListSize", 20); /* 每次列出文件数量 */
		result.put("fileManagerAllowFiles", new String[]{
				".png", ".jpg", ".jpeg", ".gif", ".bmp",
				".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
				".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
				".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
				".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"
		}); /* 列出的文件类型 */


		String configContent = JSON.toJSONString(result);

		try{
			JSONObject jsonConfig = new JSONObject( configContent );

			this.jsonConfig = jsonConfig;
		} catch ( Exception e ) {
			this.jsonConfig = null;
		}
	}
	
	private String getConfigPath () {
//        String path = this.getClass().getResource("/").getPath() + ConfigManager.configFileName;
//        if (new File(path).exists()) {
//          return path;
//        }else {
//          return this.parentPath + File.separator + ConfigManager.configFileName;
//        }
		return this.rootPath + File.separator + "WEB-INF" + File.separator + "config.json";
	}

	private String[] getArray ( String key ) {
		
		JSONArray jsonArray = this.jsonConfig.getJSONArray( key );
		String[] result = new String[ jsonArray.length() ];
		
		for ( int i = 0, len = jsonArray.length(); i < len; i++ ) {
			result[i] = jsonArray.getString( i );
		}
		
		return result;
		
	}
	
	private String readFile ( String path ) throws IOException {
		
		StringBuilder builder = new StringBuilder();
		
		try {
			
			InputStreamReader reader = new InputStreamReader( new FileInputStream( path ), "UTF-8" );
			BufferedReader bfReader = new BufferedReader( reader );
			
			String tmpContent = null;
			
			while ( ( tmpContent = bfReader.readLine() ) != null ) {
				builder.append( tmpContent );
			}
			
			bfReader.close();
			
		} catch ( UnsupportedEncodingException e ) {
			// 忽略
		}
		
		return this.filter( builder.toString() );
		
	}
	
	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter ( String input ) {
		
		return input.replaceAll( "/\\*[\\s\\S]*?\\*/", "" );
		
	}
	
}
