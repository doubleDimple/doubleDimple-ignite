package com.doubledimple.ignite.ignitecache.entity.page;
import java.util.ArrayList;
import java.util.List;


public class PaginationResult<T> {
	private SimplePage page;
	private List<T> list = new ArrayList<T>();

	public PaginationResult(SimplePage page, List<T> list) {
		this.list = list;
		this.page = page;
	}

	public PaginationResult(List<T> list) {
		this.list = list;
	}

	public PaginationResult() {

	}

	public SimplePage getPage() {
		return page;
	}

	public void setPage(SimplePage page) {
		this.page = page;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
