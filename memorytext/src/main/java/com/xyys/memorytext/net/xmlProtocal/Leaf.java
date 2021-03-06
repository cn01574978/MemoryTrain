package com.xyys.memorytext.net.xmlProtocal;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlSerializer;

/**
 * 简单叶子
 * 
 * @author Administrator
 * 
 */
public class Leaf {
	// <agenterid>889931</agenterid>
	// 处理的思路
	// ①包含的内容
	// ②序列化xml
	private String tagName;
	private String tagValue;

	// 每个叶子需要指定标签名称
	public Leaf(String tagName) {
		super();
		this.tagName = tagName;
	}

	// 处理常量
	public Leaf(String tagName, String tagValue) {
		super();
		this.tagName = tagName;
		this.tagValue = tagValue;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	/**
	 * 序列化叶子
	 * 
	 * @param serializer
	 */
	public void serializerLeaf(XmlSerializer serializer) {
		try {
			serializer.startTag(null, tagName);
			if (tagValue == null) {
				tagValue = "";
			}
			serializer.text(tagValue);
			serializer.endTag(null, tagName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
