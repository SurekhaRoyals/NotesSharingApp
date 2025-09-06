package com.nsa.base.ShareDtos;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PagedResponse<T> {

	
	private List<T> content;
	  private int currentPage;
	  private int totalPages;
	  private long totalElements;
	  private boolean last;
	
	
	
	
	public List<T> getContent() {
		return content;
	}




	public void setContent(List<T> content) {
		this.content = content;
	}




	public int getCurrentPage() {
		return currentPage;
	}




	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}




	public int getTotalPages() {
		return totalPages;
	}




	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}




	public long getTotalElements() {
		return totalElements;
	}




	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}




	public boolean isLast() {
		return last;
	}




	public void setLast(boolean last) {
		this.last = last;
	}




	public PagedResponse(List<T> content, int currentPage, int totalPages, long totalElements, boolean last) {
		super();
		this.content = content;
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.last = last;
	}




	public PagedResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
	  
}
