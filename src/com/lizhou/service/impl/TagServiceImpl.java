package com.lizhou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lizhou.dao.inter.BaseDao;
import com.lizhou.entity.Tag;
import com.lizhou.service.inter.TagServiceInter;

/**
 * @author bojiangzhou
 *
 */
@Service("tagService")
public class TagServiceImpl implements TagServiceInter {
	
	@Resource(name="baseDao")
	private BaseDao<Tag> baseDao;
	
	@Override
	public List<Tag> getTagList() {
		
		return baseDao.find("FROM Tag");
	}

}
