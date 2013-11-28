package cn.btttech.service;

import java.util.List;

import cn.btttech.entity.User;


public interface UserService {  
    
    public User login(User user);  
    
    public List<User> listByPage(final int firstResult, final int maxResult);
    
    public List<User> listByPage(Object condition, Object param, final int firstResult, final int maxResult);
    
    public List<User> listByPage(Object[] conditions, Object [] params, final int firstResult, final int maxResult);
    
    public int listByPageCount();
    
    public int listByPageCount(Object condition, Object param);
    
    public int listByPageCount(Object[] conditions, Object [] params);
    
    public User getById(int id);
    
}  