package cn.btttech.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.btttech.entity.Operation;
import cn.btttech.service.OperationService;
import cn.btttech.util.Page;
import cn.btttech.util.Result;

public class OperationAction extends ActionBase {

	private static final long serialVersionUID = 1208247597092648008L;
	private String operationId;
	private String operationName;
	
    private Page pager;
    private int pageNum=1;
    private int numPerPage;
	
	@Resource(name="operationService")
	private OperationService operationService;

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}
	
	public String list(){
    	if(numPerPage == 0){
    		numPerPage = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("maxPageResult"));
    	}
		pager = new Page(pageNum, numPerPage); 
    	Result<Operation> result = operationService.listByPage(pager);
    	request.put("page", result.getPage());
    	System.out.println(result.getPage().getTotalCount());
    	request.put("operation_list", result.getList());
    	
    	return SUCCESS;
    }
    
    public String modify(){
    	
    	Operation operation = operationService.getById(Integer.parseInt(operationId));
    	request.put("operation_modify", operation);
    	return SUCCESS;
    }

	public String getOperation_id() {
		return operationId;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String OperationName) {
		this.operationName = OperationName;
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
	
}
