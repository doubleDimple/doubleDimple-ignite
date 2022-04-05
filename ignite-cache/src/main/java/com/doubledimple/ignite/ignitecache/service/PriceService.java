package com.doubledimple.ignite.ignitecache.service;




import com.doubledimple.ignite.ignitecache.entity.page.PaginationResult;
import com.doubledimple.ignite.ignitecache.entity.pojo.Price;
import com.doubledimple.ignite.ignitecache.entity.query.PriceQuery;

import java.util.List;

/**
 * 
 *  业务接口
 * 
 */
public interface PriceService {

	/**
	 * 根据条件查询列表
	 */
	public List<Price> findListByParam(PriceQuery param);

	/**
	 * 根据条件查询列表
	 */
	public Integer findCountByParam(PriceQuery param);

	/**
	 * 分页查询
	 */
	public PaginationResult<Price> findListByPage(PriceQuery param);

	/**
	 * 新增
	 */
	public Integer add(Price bean);

	/**
	 * 批量新增
	 */
	public Integer addBatch(List<Price> listBean);

	/**
	 * 根据主键修改
	 */
	public Integer updateByPrimaryKey(Price bean, Long id);


	/**
	 * 根据主键删除
	 */
	public Integer deleteByPrimaryKey(Long id);


	/**
	 * 根据primaryKey获取对象
	 */
	 public Price getPriceByPrimaryKey(Long id);

}