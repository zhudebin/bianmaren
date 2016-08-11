package com.bianmaren.entity.dto;

import java.util.ArrayList;

/**
 * 
* @ClassName: CodeJson 
* @Description: 代码josn
* @author dengwenbing  
* @date 2015年3月19日 上午8:50:58 
*
 */
public class CodeJson {

	private String lang;							//代码语言
	private String category;						//代码分类
	private String code_title;						//代码一句话概述
	private String ta_code_outline;					//代码概述
	private ArrayList<CodeFragment> code_fragment;	//代码片段数组
	private ArrayList<String> code_pic;				//代码图片
	private ArrayList<CodeFileJson> code_files;		//代码文件
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCode_title() {
		return code_title;
	}
	public void setCode_title(String code_title) {
		this.code_title = code_title;
	}
	public String getTa_code_outline() {
		return ta_code_outline;
	}
	public void setTa_code_outline(String ta_code_outline) {
		this.ta_code_outline = ta_code_outline;
	}
	public ArrayList<CodeFragment> getCode_fragment() {
		return code_fragment;
	}
	public void setCode_fragment(ArrayList<CodeFragment> code_fragment) {
		this.code_fragment = code_fragment;
	}
	public ArrayList<String> getCode_pic() {
		return code_pic;
	}
	public void setCode_pic(ArrayList<String> code_pic) {
		this.code_pic = code_pic;
	}
	
	public ArrayList<CodeFileJson> getCode_files() {
		return code_files;
	}
	public void setCode_files(ArrayList<CodeFileJson> code_files) {
		this.code_files = code_files;
	}
	@Override
	public String toString() {
		return "CodeJson [lang=" + lang + ", category=" + category
				+ ", code_title=" + code_title + ", ta_code_outline="
				+ ta_code_outline + ", code_fragment=" + code_fragment
				+ ", code_pic=" + code_pic + ", code_files=" + code_files + "]";
	}
	
	
	
}
