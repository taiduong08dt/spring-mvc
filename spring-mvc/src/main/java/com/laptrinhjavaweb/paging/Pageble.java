package com.laptrinhjavaweb.paging;


public interface Pageble {
	public Integer getPage();
	public Integer getOffset();
	public Integer getLimit();
	public Sorter getSorter();
}
