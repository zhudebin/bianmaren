package com.bianmaren.util;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlUtils{
	@SuppressWarnings("unchecked")
	public static<T> T decode(Class<T> c,String xml){
		T obj=null;
		try {
			JAXBContext jc = JAXBContext.newInstance(c);
			Unmarshaller u = jc.createUnmarshaller();
			obj = (T) u.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public static<T> String encode(Class<T> c,Object obj){
		String xml=null;
		try {
			JAXBContext jc = JAXBContext.newInstance(c);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");//编码格式
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//是否格式化生成的xml串
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);//是否省略xml头信息
			StringWriter sw = new StringWriter();
			m.marshal((T) obj, sw);
			xml = sw.getBuffer().toString();
			sw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}


	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		Map m = new HashMap();
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();

		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			m.put(k, v);
		}
		//关闭流
		in.close();
		return m;
	}


	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		return sb.toString();
	}

}
