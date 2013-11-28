package cn.btttech.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.btttech.entity.Group;
import cn.btttech.entity.Module;
import cn.btttech.entity.Operation;
import cn.btttech.service.GroupService;
import cn.btttech.service.ModuleService;
import cn.btttech.service.OperationService;
import cn.btttech.util.ArrayUtil;

public class GroupAction extends ActionBase {

	private static final long serialVersionUID = 1208247597092648008L;
	private String group_id;
	private String groupName;
	private String firstResult;
    private String maxResult;
    
    private String[] module_id;
    private String[] operation_ids;
    
    private InputStream inputStream;
    public InputStream getInputStream() {
        return inputStream;
    }
	
	@Resource(name="groupService")
	private GroupService groupService;
	
	@Resource(name="moduleService")
	private ModuleService moduleService;
	
	@Resource(name="operationService")
	private OperationService operationService;

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
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
    	
    	List<Group> list = groupService.listByPage(firstResultInt, maxResultInt);
    	request.put("group_list", list);
    	
    	return SUCCESS;
    }
	
	public String addDisplay(){
		
		List<Operation> operation_list = operationService.findAll();
		HashMap<Integer, String> operations = new HashMap<Integer, String>();
		for (Operation operation : operation_list) {
			operations.put(operation.getOperationId(), operation.getOperationName());
		}
		
		
		List<Module> module_list = moduleService.findAll();
		HashMap<Integer, String> modules = new HashMap<Integer, String>();
		for (Module module : module_list) {
			modules.put(module.getModuleId(), module.getModuleName());
		}
		
		request.put("operations", operations);
		request.put("modules", modules);
		System.out.println(operations);
		System.out.println(modules);
		return SUCCESS;
	}
    
	public String add(){
		operation_ids = ArrayUtil.escapeBlank(operation_ids);
		
		groupService.save(groupName, module_id, operation_ids);
		
		String result = "{\"statusCode\":\"200\",\"message\":\"操作成功\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"closeCurrent\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}";
			
		try {
			inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	
    public String modify(){
    	
    	Group group = groupService.getById(Integer.parseInt(group_id));
    	request.put("group_modify", group);
    	return SUCCESS;
    }

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public String[] getModule_id() {
		return module_id;
	}

	public void setModule_id(String[] module_id) {
		this.module_id = module_id;
	}

	public String[] getOperation_ids() {
		return operation_ids;
	}

	public void setOperation_ids(String[] operation_ids) {
		this.operation_ids = operation_ids;
	}
	
}
