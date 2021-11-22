package com.xyys.memorytext.Engine;


import com.xyys.memorytext.net.xmlProtocal.Message;

/**
 * 公共数据处理
 * @author Administrator
 *
 */
public interface CommonInfoEngine {
	/**
	 * 获取当前销售期信息
	 * @param integer：彩种的标示
	 * @return
	 */
	Message getCurrentIssueInfo(Integer integer);

}
