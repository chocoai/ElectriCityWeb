package com.ikkong.dg.android;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.ikkong.common.constant.CommonConts;
import com.ikkong.common.constant.CommonConts.UserWorkTypeStatus;
import com.ikkong.common.constant.CommonConts.WorkStatus;
import com.ikkong.core.base.BaseController;
import com.ikkong.core.dao.Blade;
import com.ikkong.core.dao.Db;
import com.ikkong.core.shiro.DgUserToken;
import com.ikkong.core.toolbox.Record;
import com.ikkong.core.toolbox.kit.UUIDKit;
import com.ikkong.core.toolbox.support.BladePage;
import com.ikkong.dg.model.UserWorktype;
import com.ikkong.dg.model.WorktypeCheck;
import com.ikkong.dg.service.UserWorktypeService;
import com.ikkong.dg.service.WorktypeCheckService;
import com.ikkong.dg.service.impl.UserWorktypeServiceImpl;
import com.ikkong.dg.service.impl.WorktypeCheckServiceImpl;
import com.jfinal.kit.PropKit;

/**
 * 工种审核API类
 * 
 * @author skybiran
 *
 */
public class WorktypeCheckClientController extends BaseController {

	private static final String PAGING_QUERY = "WorkTypeCheck.findByPage";

	private WorktypeCheckService checkServ = new WorktypeCheckServiceImpl();

	private UserWorktypeService userWorkTypeServ = new UserWorktypeServiceImpl();

	private static final String filesBasePath = PropKit.use("config.properties").get("image.authPath");

	public void addCheck() {
		Integer workId = getParaToInt("workId");
		Long userWorkId = getParaToLong("userWorktypeId");
		String idCode = getPara("idCode");
		String idCardUp = getPara("idCardUp");
		String idCardDown = getPara("idCardDown");
		String qualifCert = getPara("qualifCert");

		if (workId == null) {
			renderJson(error("工种类型Id不能为空！"));
			return;
		}
		if (userWorkId == null) {
			renderJson(error("电工工种Id不能为空！"));
			return;
		}

		if (StringUtils.isBlank(idCode)) {
			renderJson(error("身份证号不能为空！"));
			return;
		} else {
			String idRegx = "[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]";
			if (StringUtils.isNotBlank(idCode) && !idCode.matches(idRegx)) {
				renderJson(error("身份证号格式不正确!"));
				return;
			}
		}

		if (StringUtils.isBlank(idCardUp) || StringUtils.isBlank(idCardDown)) {
			renderJson(error("身份证正反面图片不能为空！"));
			return;
		}

		String picName = null;
		WorktypeCheck check = new WorktypeCheck();
		check.setVersion(1);
		check.setStatus(WorkStatus.WAITING.getStatusVal());
		check.setId_code(idCode);
		DgUserToken cacheToken = dgUserToken();
		check.setDg_user_id(cacheToken.getUserId());
		check.setWork_id(workId);
		check.setUser_worktype_id(userWorkId);

		UserWorktype userWorktype = userWorkTypeServ.findById(userWorkId);
		userWorktype.setStatus(UserWorkTypeStatus.AUDITING.getStatusVal());
		userWorktype.setVersion(userWorktype.getVersion() + 1);
		userWorkTypeServ.update(userWorktype);

		try {
			DiskFileItemFactory dff = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(dff);
			List<FileItem> items = sfu.parseRequest(getRequest());
			if (items == null || items.isEmpty()) {
				renderJson(error("文件或文件流为空，服务端未获取到文件流数据！"));
				return;
			}
			for (FileItem item : items) {
				// 更改文件名为唯一的
				String filename = item.getName();
				if (filename != null) {
					picName = UUIDKit.uuid() + "." + FilenameUtils.getExtension(filename);
				}
				// 生成存储路径
				File file = new File(filesBasePath);
				if (!file.exists()) {
					file.mkdir();
				}
				// 处理文件的上传
				item.write(new File(filesBasePath + picName));
				if (filename.equals(idCardUp)) {// 正面
					check.setIdcard_up(picName);
				} else if (filename.equals(idCardDown)) { // 反面
					check.setIdcard_down(picName);
				} else if (filename.equals(qualifCert)) { // 资格证
					check.setQualif_cert(picName);
				}
			}

			check.setCreate_tm(new Date());
			if (checkServ.save(check)) {
				renderJson(success("身份证及证件资料上传成功!"));
			} else {
				renderJson(error("身份证及证件资料上传失败!"));
			}
			return;
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("服务端异常!"));
			return;
		}
	}

	public void query() {
		Integer page = getParaToInt("page");
		Integer workId = getParaToInt("workId");
		String status = getPara("status");
		String createStart = getPara("createStart");
		String createEnd = getPara("createEnd");
		String updateStart = getPara("updateStart");
		String updateEnd = getPara("updateEnd");

		Long dgUserId = dgUserToken().getUserId();
		Record paraMap = Record.create().set("dgUserId", dgUserId);
		String sql = Blade.dao().getScript(PAGING_QUERY).getSql();
		StringBuffer sqlBuf = new StringBuffer(sql).append(" where 1=1 ");
		sqlBuf.append("and t.dg_user_id = #{dgUserId} ");

		if (workId != null && workId > 0) {
			paraMap.put("workId", workId);
			sqlBuf.append(" and t.work_id = #{workId} ");
		}
		if (StringUtils.isNotBlank(status) && !"-1".equals(status)) {
			paraMap.set("status", status.split(","));// 多状态查询
			sqlBuf.append(" and t.status in (#{join(status)})");// join
		}

		if (StringUtils.isNotBlank(createStart)) {
			paraMap.put("createStart", createStart);
			sqlBuf.append(" and t.create_tm >= #{createStart}");
		}

		if (StringUtils.isNotBlank(createEnd)) {
			paraMap.put("createEnd", createEnd);
			sqlBuf.append(" and t.create_tm < DATE_ADD(#{createEnd},INTERVAL 1 DAY)");
		}

		if (StringUtils.isNotBlank(updateStart)) {
			paraMap.put("updateStart", updateStart);
			sqlBuf.append(" and t.update_tm >= #{updateStart}");
		}

		if (StringUtils.isNotBlank(updateEnd)) {
			paraMap.put("updateEnd", updateEnd);
			sqlBuf.append(" and t.update_tm < DATE_ADD(#{updateEnd},INTERVAL 1 DAY)");
		}

		String headUrl = PropKit.get("access.auth");
		paraMap.put("headUrl", headUrl);

		if (page == null) {// 默认第一页
			page = 1;
		}

		sqlBuf.append(" order by t.create_tm desc");
		int start = (page - 1) * CommonConts.CLIENT_PAGE_SIZE + 1;

		try {
			BladePage<Object> resultPage = Db.init().paginate(sqlBuf.toString(), paraMap, start,
					CommonConts.CLIENT_PAGE_SIZE);
			if (resultPage == null || resultPage.getTotal() == 0) {
				renderJson(success("暂无对应记录!"));
			} else {
				renderJson(success("查询到结果！").setData(resultPage));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(error("获取失败，服务端异常！"));
		}
	}
}
