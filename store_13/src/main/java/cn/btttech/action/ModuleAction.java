package cn.btttech.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.btttech.entity.Module;
import cn.btttech.service.ModuleService;

public class ModuleAction extends ActionBase {

	private static final long serialVersionUID = 1208247597092648008L;
	private String monduleId;
	private String monduleName;
	private String firstResult;
    private String maxResult;
	
	@Resource(name="moduleService")
	private ModuleService moduleService;

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	public String list(){
    	int firstResultInt = 1;
    	if(firstResult != null){
    		firstResultInt = Integer.parseInt(firstResult);
    	}
    	int maxResultInt = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("maxPageResult"));
    	if(maxResult != null){
    		maxResultInt = Integer.parseInt(maxResult);
    	}
    	
    	List<Module> list = moduleService.listByPage(firstResultInt, maxResultInt);
    	request.put("Module_list", list);
    	
    	return SUCCESS;
    }
    
    public String modify(){
    	
    	Module module = moduleService.getById(Integer.parseInt(monduleId));
    	request.put("module_modify", module);
    	return SUCCESS;
    }

	public String getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}

	public String getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(String maxResult) {
		this.maxResult = maxResult;
	}

	public String getMonduleName() {
		return monduleName;
	}

	public void setMonduleName(String monduleName) {
		this.monduleName = monduleName;
	}

	public String getMonduleId() {
		return monduleId;
	}
	
}
