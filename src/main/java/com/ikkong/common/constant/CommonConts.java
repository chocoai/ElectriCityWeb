package com.ikkong.common.constant;

public class CommonConts {

	public static final String PhoneRegex = "1(?:[38]\\d|4[4579]|5[0-35-9]|7[235678])\\d{8}";

	/**
	 * 手机客户端分页每页数据条数
	 */
	public static final int CLIENT_PAGE_SIZE = 10;

	/**
	 * Session 过期天数
	 */
	public static final int CLIENT_SESSION_TIMEOUT = 7;

	/**
	 * 电工用户状态
	 * 
	 * @author skybiran
	 *
	 */
	public static enum UserStatus {
		// 0正常，1挂起，2冻结,3注销
		NORMAL(0), HANGUP(1), FREEZE(2), DISABLE(3);

		private final int statusVal;

		UserStatus(int statusVal) {
			this.statusVal = statusVal;
		}

		public int getStatusVal() {
			return statusVal;
		}
	}

	/**
	 * 工种审核状态
	 * 
	 * @author skybiran
	 *
	 */
	public static enum WorkStatus {
		// 0待审核，1审核通过，2审核失败
		WAITING(0), PASSED(1), FAILURE(2);

		private final int statusVal;

		WorkStatus(int statusVal) {
			this.statusVal = statusVal;
		}

		public int getStatusVal() {
			return statusVal;
		}
	}

	/**
	 * 项目进度状态
	 * 
	 * @author sparker
	 *
	 */
	public static enum ProgressStatus {
		// 0提交，1确认，2默认，3拒绝
		SUBMIT(0), CONFIRM(1), DEFAULT(2), REFUSED(3);//新增
		private final int statusVal;

		ProgressStatus(int statusVal) {
			this.statusVal = statusVal;
		}

		public int getStatusVal() {
			return statusVal;
		}
	}

	/**
	 * 申请状态
	 * 
	 * @author sparker
	 *
	 */
	public static enum ApplyStatus {
		// 状态，0申请中，1同意，2拒绝，3挂起
		APPLYING(0), AGREE(1), REFUSED(2), HANGUP(3);

		private final int statusVal;

		ApplyStatus(int statusVal) {
			this.statusVal = statusVal;
		}

		public int getStatusVal() {
			return statusVal;
		}
	}

	/**
	 * 用户工种状态
	 * 对应表Dg_User_Worktype
	 * 
	 * @author Administrator
	 *
	 */
	public static enum UserWorkTypeStatus {
//		0  正常 1  待审核 2 审核中 3 审核通过 4  审核失败
		NORMAL(0), WAITING(1), AUDITING(2), AUDIT_PASSD(3), AUDIT_FAIL(4);

		private final int statusVal;

		UserWorkTypeStatus(int statusVal) {
			this.statusVal = statusVal;
		}

		public int getStatusVal() {
			return statusVal;
		}
	}

	/**
	 * 招标申请类型
	 * 0空，1招标申请，2项目终止申请，3项目加班申请,4完成申请
	 * 
	 * @author sparker
	 *
	 */
	public static enum ApplyType {
		NULL(0), APPLY(1), ABORT(2), OVERTIME(3), FINISH(4);

		private final int typeVal;

		ApplyType(int typeVal) {
			this.typeVal = typeVal;
		}

		public int getTypeVal() {
			return typeVal;
		}
	}

	public static enum ItemStatus {
		// 项目状态，0保存,1发布中，2招标中，3已招标，4项目进行中，5完成，6终止，7撤销，8挂起
		SAVE(0), PUBLISH(1), BIDDING(2), OVER_BIDED(3), INPROCCESS(4), FINISHED(5), TERMINATE(6), REVOCATION(7), HUNG_UP(8);

		private final int statusVal;

		ItemStatus(int statusVal) {
			this.statusVal = statusVal;
		}

		public int getStatusVal() {
			return statusVal;
		}
	}

	/**
	 * 消息状态0未读，1已读
	 * 
	 * @author sparker
	 *
	 */
	public static enum MsgStatus {
		UNREAD(0), READED(1);

		private final int statusVal;

		MsgStatus(int statusVal) {
			this.statusVal = statusVal;
		}

		public int getStatusVal() {
			return statusVal;
		}
	}

	/**
	 * 消息类型0用户消息，1系统消息
	 * 
	 * @author sparker
	 *
	 */
	public static enum MsgType {
		USER_MSG(0), SYS_MSG(1);

		private final int statusVal;

		MsgType(int statusVal) {
			this.statusVal = statusVal;
		}

		public int getStatusVal() {
			return statusVal;
		}
	}

	/**
	 * 系统配置的时效类型
	 * 
	 * @author sparker
	 *
	 */
	public static enum AgingType {
		AGE_801("801"), AGE_802("802"), AGE_803("803"), AGE_804("804"), AGE_805("805"), AGE_806("806"), AGE_807("807"), AGE_808("808"), AGE_809("809"), AGE_810("810"), AGE_811("811"), AGE_812("812");

		private final String ageVal;

		AgingType(String ageVal) {
			this.ageVal = ageVal;
		}

		public String getStatusVal() {
			return ageVal;
		}
	}
}
