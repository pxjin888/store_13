package cn.btttech.action;

import java.util.List;
import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import cn.btttech.entity.User;
import cn.btttech.service.UserService;


@Controller("UserAction")
public class UserAction extends ActionBase {  
    
    private static final long serialVersionUID = 1L;  
    
    private String user_id;
    private String username;  
    private String password;  
    private String validCode;
    private String firstResult;
    private String maxResult;
    @Resource(name="userService")
    private UserService userService;  

	public void setUserService(UserService userService) {
		this.userService = userService;
	}  
    
    public String login(){  
    	
    	if(!(validCode.trim().equalsIgnoreCase(session.get("validateCode").toString()))){
    		addActionError("code_error");
    		return ERROR;
    	}
    	
        User user = new User(username, password);
        User login = userService.login(user);  
        if (login != null) {  
            session.put("user", login);  
            return SUCCESS;  
        }else{
        	addActionError("user_error");
        	return ERROR;
        }
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
    	
    	List<User> list = userService.listByPage(firstResultInt, maxResultInt);
    	request.put("user_list", list);
    	System.out.println(list.get(0).getUserName());
    	
    	return SUCCESS;
    }
    
    public String modify(){
    	
    	User user = userService.getById(Integer.parseInt(user_id));
    	request.put("user_modify", user);
    	return SUCCESS;
    }
  
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }

	public UserService getUserService() {
		return userService;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

    
  
}  