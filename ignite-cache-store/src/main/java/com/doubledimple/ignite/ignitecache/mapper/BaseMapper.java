package com.doubledimple.ignite.ignitecache.mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BaseMapper<T, P> {
	/**
	 * 
	 * insert:(插入). <br/>
	 *
	 * @author luohaili
	 * @param t
	 * @return
	 * @since JDK 1.7
	 */
	public Integer insert(T t);

	/**
	 * 
	 * insert:(批量插入). <br/>
	 *
	 * @author luohaili
	 * @param list
	 * @return
	 * @since JDK 1.7
	 */
	public Integer insertBatch(@Param("list") List<T> list);

	/**
	 * 
	 * insert:(更具参数查询集合). <br/>
	 *
	 * @author luohaili
	 * @param p
	 * @return
	 * @since JDK 1.7
	 */
	public List<T> selectList(P p);

	/**
	 * 
	 * insert:(根据集合查询数量). <br/>
	 *
	 * @author luohaili
	 * @param t
	 * @return
	 * @since JDK 1.7
	 */
	public Integer selectCount(P p);

}
