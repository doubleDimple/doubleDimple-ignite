package com.doubledimple.ignite.ignitecache.service;



import com.doubledimple.ignite.ignitecache.entity.page.PaginationResult;
import com.doubledimple.ignite.ignitecache.entity.pojo.Product;
import com.doubledimple.ignite.ignitecache.entity.query.ProductQuery;

import java.util.List;

/**
 * 
 *  业务接口
 * 
 */
public interface ProductService {

	/**
	 * 根据条件查询列表
	 */
	public List<Product> findListByParam(ProductQuery param);

	/**
	 * 根据条件查询列表
	 */
	public Integer findCountByParam(ProductQuery param);

	/**
	 * 分页查询
	 */
	public PaginationResult<Product> findListByPage(ProductQuery param);

	/**
	 * 新增
	 */
	public Integer add(Product bean);

	/**
	 * 批量新增
	 */
	public Integer addBatch(List<Product> listBean);

	/**
	 * 根据主键修改
	 */
	public Integer updateByPrimaryKey(Product bean, Long id);


	/**
	 * 根据主键删除
	 */
	public Integer deleteByPrimaryKey(Long id);


	/**
	 * 根据primaryKey获取对象
	 */
	 public Product getProductByPrimaryKey(Long id);

}