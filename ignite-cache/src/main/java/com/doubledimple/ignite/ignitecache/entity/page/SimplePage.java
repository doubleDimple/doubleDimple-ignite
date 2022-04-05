package com.doubledimple.ignite.ignitecache.entity.page;

import com.doubledimple.ignite.ignitecache.entity.enums.PageSize;

public class SimplePage {
	private int page;
	private int countTotal;
	private int pageSize;
	private int pageTotal;
	private int start;
	private int end;

	public SimplePage() {
	};

	public SimplePage(Integer page, int countTotal, int pageSize) {
		if (null == page) {
			page = 0;
		}
		this.page = page;
		this.countTotal = countTotal;
		this.pageSize = pageSize;
		action();
	}

	public SimplePage(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void action() {
		if (this.pageSize <= 0) {
			this.pageSize = PageSize.SIZE20.getSize();
		}
		if (this.countTotal > 0) {
			this.pageTotal = this.countTotal % this.pageSize == 0 ? this.countTotal / this.pageSize
					: this.countTotal / this.pageSize + 1;
		} else {
			pageTotal = 1;
		}

		if (page <= 1) {
			page = 1;
		}
		if (page > pageTotal) {
			page = pageTotal;
		}
		this.start = (page - 1) * pageSize;
		this.end = this.pageSize;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCountTotal() {
		return countTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setCountTotal(int countTotal) {
		this.countTotal = countTotal;
		this.action();
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
