package com.lizhou.dao.inter;

import java.io.Serializable;
import java.util.List;

import com.lizhou.entity.PageBean;

public interface BaseDao<T> {

	public Serializable save(T o);


	public void delete(T o);

	
	public void update(T o);


	public void saveOrUpdate(T o);
	

	public void merge(T o);

	/**
	 * 鏌ヨ
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 鏌ヨ闆嗗悎
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * 鏌ヨ闆嗗悎
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * 鏌ヨ闆嗗悎(甯﹀垎椤�
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            鏌ヨ绗嚑椤�
	 * @param rows
	 *            姣忛〉鏄剧ず鍑犳潯璁板綍
	 * @return
	 */
	public List<T> find(String hql, Object[] param, PageBean pageBean);

	/**
	 * 鏌ヨ闆嗗悎(甯﹀垎椤�
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, PageBean pageBean);
	
	/**
	 * 鍒嗛〉鏌ヨ
	 * @param hql
	 * @param pageBean
	 * @return
	 */
	public List<T> find(String hql, PageBean pageBean);

	/**
	 * 鑾峰緱涓�釜瀵硅薄
	 * 
	 * @param c
	 *            瀵硅薄绫诲瀷
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 鑾峰緱涓�釜瀵硅薄
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);

	/**
	 * 鑾峰緱涓�釜瀵硅薄
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);
	
	/**
	 * select count(*) from 绫�
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * select count(*) from 绫�
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param);

	/**
	 * select count(*) from 绫�
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * 鎵цHQL璇彞
	 * 
	 * @param hql
	 * @return 鍝嶅簲鏁扮洰
	 */
	public Integer executeHql(String hql);

	/**
	 * 鎵цHQL璇彞
	 * 
	 * @param hql
	 * @param param
	 * @return 鍝嶅簲鏁扮洰
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * 鎵цHQL璇彞
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, List<Object> param);

	/**
	 * 鎵цSQL璇彞
	 * @param sql
	 * @return
	 */
	public Integer executeSql(String sql);
}
