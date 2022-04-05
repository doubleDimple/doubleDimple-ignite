package com.doubledimple.ignite.ignitecache.entity.query;


import com.doubledimple.ignite.ignitecache.entity.page.SimplePage;

public class BaseParam {
	private SimplePage page;
	private Integer pageNo;
	private Integer pageSize;
	private String orderBy;
	public SimplePage getPage() {
		return page;
	}

	public void setPage(SimplePage page) {
		this.page = page;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setOrderBy(String orderBy){
		this.orderBy = orderBy;
	}
	
	public String getOrderBy(){
		return this.orderBy;
	}
}
