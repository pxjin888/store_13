package cn.btttech.util;

public class Page {
	 //当前页数
	 private int pageNum = 1;
	 //每页显示数量
	 private int numPerPage = 5;
	 //总页数
	 private int totalPage;
	 //总数量
	 private int totalCount;
	 
	 private String [] conditions;
	 
	 private String [] params;
	 
	 //可以将dwz传过来的pageNum、numPerPage进行初始化
	 public Page(int pageNum , int numPerPage){
	  this.pageNum = pageNum;
	  this.numPerPage = numPerPage;
	 }
	 
	 public int getTotalPage() {
	  return totalPage;
	 }

	 public void setTotalPage(int totalPage) {
	  this.totalPage = totalPage;
	 }

	//设置总数量的同时，设置总页数

	 public void setCount(int totalCount){
	  this.totalCount = totalCount;
	  int temp = 0;
	  if(totalCount % this.numPerPage != 0){
	   temp++;
	  }
	  this.totalPage = totalCount / this.numPerPage + temp;
	 }

	 public int getTotalCount() {
	  return totalCount;
	 }

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String[] getConditions() {
		return conditions;
	}

	public void setConditions(String[] conditions) {
		this.conditions = conditions;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

} 