package com.salenkod.pagination;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pagin implements Serializable {

	protected static final long serialVersionUID = 1L;
	protected Integer currentPage;
	protected Integer pageSize;
	protected Long totalResults;

	protected String sortFields;
	protected String sortDirections;
	
	@XmlElement
	private List<Object> list;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}

	public String getSortFields() {
		return sortFields;
	}

	public void setSortFields(String sortFields) {
		this.sortFields = sortFields;
	}

	public String getSortDirections() {
		return sortDirections;
	}

	public void setSortDirections(String sortDirections) {
		this.sortDirections = sortDirections;
	}
	
	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	
}
