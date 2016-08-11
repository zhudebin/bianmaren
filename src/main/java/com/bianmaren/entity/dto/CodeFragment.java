package com.bianmaren.entity.dto;

/**
 * 
* @ClassName: CodeFragment 
* @Description: 保存解析的Json里面的代码片段
* @author dengwenbing  
* @date 2015年3月19日 上午8:50:07 
*
 */
public class CodeFragment {

	private String code_lang;		//代码语言
	private String code_content;		//代码内容
	
	public String getCode_lang() {
		return code_lang;
	}
	public void setCode_lang(String code_lang) {
		this.code_lang = code_lang;
	}
	public String getCode_content() {
		return code_content;
	}
	public void setCode_content(String code_content) {
		this.code_content = code_content;
	}
	
	@Override
	public String toString() {
		return "CodeFragment [code_lang=" + code_lang + ", code_content="
				+ code_content + "]";
	}
	
}
