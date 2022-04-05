package com.doubledimple.ignite.ignitecache.mapper.dao1;

import com.doubledimple.ignite.ignitecache.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 *  数据库操作接口
 * 
 */
public interface ProductMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据primaryKey更新
	 */
	 public Integer updateByPrimaryKey(@Param("bean") T t, @Param("id") Long id);


	/**
	 * 根据primaryKey删除
	 */
	 public Integer deleteByPrimaryKey(@Param("id") Long id);


	/**
	 * 根据primaryKey获取对象
	 */
	 public T selectByPrimaryKey(@Param("id") Long id);


}
