package com.ikkong.dg.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.ikkong.common.constant.CommonConts.ItemStatus;
import com.ikkong.common.constant.CommonConts.MsgTempCode;
import com.ikkong.common.constant.CommonConts.MsgType;
import com.ikkong.common.constant.CommonConts.WorkStatus;
import com.ikkong.common.constant.SmsTempConts;
import com.ikkong.common.utils.SmsSender;
import com.ikkong.core.jfinal.ext.kit.JsonKit;
import com.ikkong.core.toolbox.Record;
import com.ikkong.dg.model.WorkOrder;
import com.ikkong.dg.service.DgUserService;
import com.ikkong.dg.service.MessagesService;
import com.ikkong.dg.service.WorkOrderService;
import com.ikkong.dg.service.impl.DgUserServiceImpl;
import com.ikkong.dg.service.impl.MessagesServiceImpl;
import com.ikkong.dg.service.impl.WorkOrderServiceImpl;
import com.ikkong.system.controller.base.UrlPermissController;
import com.jfinal.aop.Clear;

/**
 * Generated by Blade. 2017-09-24 12:44:38
 */
public class WorkOrderController extends UrlPermissController {
	private static String CODE = "workOrder";
	private static String PERFIX = "dg_work_order";
	private static String LIST_SOURCE = "WorkOrder.list";
	private static String BASE_PATH = "/dg/workOrder/";

	private WorkOrderService service = new WorkOrderServiceImpl();
	private MessagesService msgServ = new MessagesServiceImpl();
	private DgUserService userServ = new DgUserServiceImpl();

	public void index() {
		setAttr("code", CODE);
		render(BASE_PATH + "workOrder.html");
	}

	public void add() {
		setAttr("code", CODE);
		render(BASE_PATH + "workOrder_add.html");
	}

	public void edit() {
		String id = getPara(0);
		WorkOrder workOrder = service.findById(id);
		workOrder.setVersion(workOrder.getVersion() + 1);
		setAttr("model", JsonKit.toJson(workOrder));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "workOrder_edit.html");
	}

	public void view() {
		String id = getPara(0);
		WorkOrder workOrder = service.findById(id);
		setAttr("model", JsonKit.toJson(workOrder));
		setAttr("id", id);
		setAttr("code", CODE);
		render(BASE_PATH + "workOrder_view.html");
	}

	public void list() {
		Object grid = paginate(LIST_SOURCE);
		renderJson(grid);
	}

	public void save() {
		WorkOrder workOrder = mapping(PERFIX, WorkOrder.class);
		boolean temp = service.save(workOrder);
		if (temp) {
			renderJson(success(SAVE_SUCCESS_MSG));
		} else {
			renderJson(error(SAVE_FAIL_MSG));
		}
	}

	public void update() {
		WorkOrder workOrder = mapping(PERFIX, WorkOrder.class);
		workOrder.setVersion(workOrder.getVersion() + 1);
		boolean temp = service.update(workOrder);
		if (temp) {
			renderJson(success(UPDATE_SUCCESS_MSG));
		} else {
			renderJson(error(UPDATE_FAIL_MSG));
		}
	}

	public void remove() {
		String ids = getPara("ids");
		int cnt = service.deleteByIds(ids);
		if (cnt > 0) {
			renderJson(success(DEL_SUCCESS_MSG));
		} else {
			renderJson(error(DEL_FAIL_MSG));
		}
	}

	public void workorder() {
		String orderId = getPara(0);
		setAttr("orderId", orderId);
		setAttr("code", CODE);
		setAttr("workOrderCode", "workorder");
		render(BASE_PATH + "order_detail.html");
	}

	@Clear
	public void workorderList() {
		Object grid = paginate("WorkOrder.findByBackground");
		renderJson(grid);
	}

	public void audit() {// 审核
		String workOrderIds = getPara("ids");
		Integer status = getParaToInt("status");
		if (StringUtils.isEmpty(workOrderIds)) {
			renderJson(warn("审核选项不能为空！"));
			return;
		}
		try {
			String[] arr = workOrderIds.split(",");
			Record params = Record.create().set("ids", arr);
			List<WorkOrder> itemList = service.findBy(" where id in (#{join(ids)})", params);
			Date curTime = new Date();
			Set<Long> createIdList = new HashSet<Long>();
			for (WorkOrder vo : itemList) {
				vo.setVersion(vo.getVersion() + 1);
				if (WorkStatus.PASSED.getStatusVal() == status) {
					vo.setBiding_time(curTime);// 招标中
					vo.setStatus(ItemStatus.BIDDING.getStatusVal());
				} else {
					vo.setRevocation_time(curTime);// 审核失败暂定撤销
					vo.setStatus(ItemStatus.REVOCATION.getStatusVal());
				}

				createIdList.add(vo.getCreate_id());
			}
			int[] resList = service.updateBathById(itemList);
			int count = 0;
			for (int re : resList) {
				count += re;
			}
			
			String smsTemplate = null;
			if (count == arr.length) {
				if (WorkStatus.PASSED.getStatusVal() == status) { //通过审核
					msgServ.addMsgByUserList(createIdList, MsgType.EVENT_MSG.getStatusVal(),
							MsgTempCode.TMP_1014.getTmpVal());
					smsTemplate = SmsTempConts.ITEM_PASSED_AUDIT_TMP;
					renderJson(success("审核完成，工种单处于招标中！"));
				} else {
					msgServ.addMsgByUserList(createIdList, MsgType.EVENT_MSG.getStatusVal(),
							MsgTempCode.TMP_1015.getTmpVal());
					smsTemplate = SmsTempConts.ITEM_CREATOR_AUDIT_FAIL_TMP;
					renderJson(success("审核不通过，工种单已撤销！"));
				}
				SmsSender.sendTipSms(smsTemplate, StringUtils.join(createIdList, ","));
			} else {
				renderJson(fail("审核失败！"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("服务端审核异常！"));
		}
	}
	
	private void doResetUserRejects(WorkOrder wo) {
		
	}

	/**
	 * 工种单协调处理
	 */
	public void coordinate() {
		String workOrderIds = getPara("ids");
		if (StringUtils.isEmpty(workOrderIds)) {
			renderJson(warn("工种单选项不能为空！"));
			return;
		}
		try {
			String[] arr = workOrderIds.split(",");
			Record params = Record.create().set("ids", arr);
			Set<Long> acceptIdList = new HashSet<Long>();
			Set<Long> publishIdList = new HashSet<Long>();
			List<WorkOrder> itemList = service.findBy(" where id in (#{join(ids)})", params);
			// Date curTime = new Date();
			for (WorkOrder vo : itemList) {
				vo.setVersion(vo.getVersion() + 1);
				vo.setStatus(ItemStatus.INPROCCESS.getStatusVal());
				if (ItemStatus.HUNG_UP.getStatusVal() == vo.getStatus()) {// 解除挂起通知
					acceptIdList.add(vo.getAccept_user_id());
					publishIdList.add(vo.getCreate_id());
				}
				// 清零逻辑
				doResetUserRejects(vo);
			}
			int[] resList = service.updateBathById(itemList);
			int count = 0;
			for (int re : resList) {
				count += re;
			}
			if (count == arr.length) {// 接单人解除通知 和 发单人解除通知
				// 双方账户状态变更为正常
				userServ.resetUsersStatus(acceptIdList);
				userServ.resetUsersStatus(publishIdList);
				
				// 双方消息提醒
				msgServ.addMsgByUserList(acceptIdList, MsgType.EVENT_MSG.getStatusVal(),
							MsgTempCode.TMP_1010.getTmpVal());
				msgServ.addMsgByUserList(publishIdList, MsgType.EVENT_MSG.getStatusVal(),
						MsgTempCode.TMP_1011.getTmpVal());
				
				// 双方短信提醒
				List<String> accList = userServ.findPhonesByList(acceptIdList);
				List<String> pubList = userServ.findPhonesByList(publishIdList);				
				String phones = StringUtils.join(accList.addAll(pubList), ",");
				SmsSender.sendTipSms(SmsTempConts.RELIEVE_HUNGUP_TMP, phones);
				
				renderJson(success("协调处理完成，工种单处于中！"));
			} else {
				renderJson(fail("协调处理失败！"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("服务端协调处理异常！"));
		}
	}

	public void progress() {
		String workOrderId = getPara(0);
		setAttr("workOrderId", workOrderId);
		setAttr("code", "orderProgress");
		setAttr("progressCode", "workOrder");
		render("/dg/dgOrder/progress_detail.html");
	}

	public void apply() {
		String workOrderId = getPara(0);
		setAttr("workOrderId", workOrderId);
		setAttr("code", "orderApply");
		setAttr("applyCode", "workOrder");
		render("/dg/dgOrder/apply_detail.html");
	}

	@Clear
	public void applyList() {
		Object grid = paginate("OrderApply.findByWorkOrder");
		renderJson(grid);
	}

	@Clear
	public void progressList() {
		Object grid = paginate("OrderProgress.findByWorkOrder");
		renderJson(grid);
	}
}
