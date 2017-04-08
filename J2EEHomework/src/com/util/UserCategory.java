package com.util;
/**
* @author 141250149吴秦月
* @date 创建时间：2017年3月4日 下午6:22:02
*/
public class UserCategory {
		public static final int MENBER = 0;// 会员
		public static final int WAITER = 1;// 客栈服务员
		public static final int MANAGER = 2;// Hostel World经理

		private static final String[] categoryStr = { "会员", "客栈服务员", "Hostel World经理" };

		public static String getStrOfUserCategory(final int category) {
			return (category >= 0 && category < categoryStr.length) ? categoryStr[category] : categoryStr[0];
	}
}
