package com.lizhou.service.inter;

import java.util.List;


import com.lizhou.entity.Notice;
import com.lizhou.entity.PageBean;

/**
 * @author bojiangzhou
 *
 */
public interface NoticeServiceInter {
	

	List<Notice> getNoticeList(PageBean pageBean);
	

	Notice getNotice(int id);
	
}
