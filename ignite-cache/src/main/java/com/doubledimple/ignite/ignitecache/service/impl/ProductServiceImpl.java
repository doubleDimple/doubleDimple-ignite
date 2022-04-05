package com.doubledimple.ignite.ignitecache.service.impl;

import com.doubledimple.ignite.ignitecache.entity.enums.PageSize;
import com.doubledimple.ignite.ignitecache.entity.page.PaginationResult;
import com.doubledimple.ignite.ignitecache.entity.page.SimplePage;
import com.doubledimple.ignite.ignitecache.entity.pojo.Product;
import com.doubledimple.ignite.ignitecache.entity.query.ProductQuery;
import com.doubledimple.ignite.ignitecache.mapper.dao1.ProductMapper;
import com.doubledimple.ignite.ignitecache.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 
 *  业务接口实现
 * 
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductMapper<Product, ProductQuery> productMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<Product> findListByParam(ProductQuery param) {
		List<Product> list = this.productMapper.selectList(param);
		return list;
	}

	/**
	 * 根据条件查询列表
	 */
	public Integer findCountByParam(ProductQuery param) {
		Integer count = this.productMapper.selectCount(param);
		return count;
	}

	/**
	 * 分页查询方法
	 */
	public PaginationResult<Product> findListByPage(ProductQuery param) {
		int count = this.productMapper.selectCount(param);
		int pageSize = param.getPageSize()==null? PageSize.SIZE15.getSize():param.getPageSize();
		int pageNo = 0;
		if (null != param.getPageNo()) {
			pageNo=param.getPageNo();
		}
		SimplePage page = new SimplePage(pageNo, count, pageSize);
		param.setPage(page);
		List<Product> list = this.productMapper.selectList(param);
		PaginationResult<Product> result = new PaginationResult<Product>(page, list);
		return result;
	}

	/**
	 * 新增
	 */
	public Integer add(Product bean){
		return this.productMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	public Integer addBatch(List<Product> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.productMapper.insertBatch(listBean);
	}

	/**
	 * 修改
	 */
	public Integer updateByPrimaryKey(Product bean,Long id){
		return this.productMapper.updateByPrimaryKey(bean,id);
	}

	/**
	 * 删除
	 */
	public Integer deleteByPrimaryKey(Long id){
		return this.productMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据primaryKey获取对象
	 */
	public Product getProductByPrimaryKey(Long id){
		return this.productMapper.selectByPrimaryKey(id);
	}

}