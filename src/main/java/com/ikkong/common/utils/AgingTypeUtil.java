package com.ikkong.common.utils;

import java.util.Date;

import org.joda.time.DateTime;

public class AgingTypeUtil {

	/**
	 * 截止时间计算
	 * 
	 * @param agingType 时效类型
	 * @param start 开始日期
	 * @param num 偏向值
	 * @return
	 */
	public static Date plusDate(int agingType, Date start, int num) {
		DateTime dt = DateTime.now();
		dt.withMillis(start.getTime());

		switch (agingType) {
			case 1: {// 天数
				dt = dt.plusDays(num);
				break;
			}
			case 2: {// 小时数
				dt = dt.plusHours(num);
				break;
			}
			case 6: {// 分钟数
				dt = dt.plusMinutes(num);
				break;
			}
		}
		return dt.toDate();
	}
	
	/**
	 * SQL 时间增加条件
	 * 
	 * @param agingType
	 * @param num
	 * @return
	 */
	public static String dateCondition(int agingType, String num) {
		String condition = "interval ";
		switch (agingType) {
			case 1: {// 天数
				condition += num.trim() + " day";
				break;
			}
			case 2: {// 小时数
				condition += num.trim() + " hour";
				break;
			}
			case 6: {// 分钟数
				condition += num.trim() + " minute";
				break;
			}
			case 7: {// 周数
				condition += num.trim() + " week";
				break;
			}
			case 8: {// 月数
				condition += num.trim() + " month";
				break;
			}
			case 9: {// 年数
				condition += num.trim() + " year";
				break;
			}
		}
		return condition;
	}
	
}
