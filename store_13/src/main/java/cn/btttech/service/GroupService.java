package cn.btttech.service;

import java.util.List;

import cn.btttech.entity.Group;


public interface GroupService {  
    
    public List<Group> listByPage(final int firstResult, final int maxResult);
    
    public List<Group> listByPage(Object condition, Object param, final int firstResult, final int maxResult);
    
    public List<Group> listByPage(Object[] conditions, Object [] params, final int firstResult, final int maxResult);
    
    public int listByPageCount();
    
    public int listByPageCount(Object condition, Object param);
    
    public int listByPageCount(Object[] conditions, Object [] params);
    
    public Group getById(int id);
    
    public void save(String group_name,String[] module_id,String[] operation_id);
    
}  