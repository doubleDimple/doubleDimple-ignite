package com.doubledimple.ignite.ignitecache.entity.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Product implements Serializable {

	public Product(Long id, String name) {
		this.id = id;
		this.name = name;
	}

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
	private LocalDateTime createTime;

	/**
	 * 
	 */
	private LocalDateTime updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
