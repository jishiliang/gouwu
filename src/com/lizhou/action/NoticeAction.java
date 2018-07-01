package com.lizhou.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lizhou.entity.Notice;
import com.lizhou.service.inter.NoticeServiceInter;
import com.lizhou.tools.NavTool;

/**
 * ����Action
 * @author bojiangzhou
 *
 */
@Controller("noticeAction")
@Scope(value="prototype")
public class NoticeAction {
	
	@Resource(name="noticeService")
	private NoticeServiceInter noticeService;
	
	private int noticeId;
	
	private Notice notice;
	
	private String navCode;
	
	private String mainPage;

	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public String getNavCode() {
		return navCode;
	}
	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}
	public String getMainPage() {
		return mainPage;
	}
	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	
	public String detail(){
		//��ȡ
		notice = noticeService.getNotice(noticeId);
		//������ҳ
		mainPage = "noticeDetails.jsp";
		//����
		navCode = NavTool.genNavCode("������Ϣ");
		return "detail";
	}
	
	
}
