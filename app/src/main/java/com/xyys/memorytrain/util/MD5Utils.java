package com.xyys.memorytrain.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			/**
			 * 获取128位的字节数组 加密信息
			 */
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法");
		}
		/**
		 * 128位的字节数组 转 为 32位的 字符串
		 */
		String md5code = new BigInteger(1, secretBytes).toString(16);
		/**
		 * 不够32位，前面添加 0
		 */
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
}
