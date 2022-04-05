package com.doubledimple.ignite.ignitecache.entity.pojo;

import java.io.Serializable;


/**
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Product implements Serializable {


	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 
	 */
	private java.util.Date createTime;

	/**
	 * 
	 */
	private java.util.Date updateTime;


	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public String toString (){
		return "主键:"+(id == null ? "空" : id)+"，名称:"+(name == null ? "空" : name)+"，createTime:"+(createTime == null ? "空" : createTime)+"，updateTime:"+(updateTime == null ? "空" : updateTime);
	}
}
