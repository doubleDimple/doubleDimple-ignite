package com.doubledimple.ignite.ignitecache.entity.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Price implements Serializable {


	/**
	 * 主键
	 */
	private Long id;

	private Long productId;

	/**
	 * 价格
	 */
	private BigDecimal price;

	/**
	 * 
	 */
	private Date createTime;

	/**
	 * 
	 */
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Price{" +
				"id=" + id +
				", productId=" + productId +
				", price=" + price +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
