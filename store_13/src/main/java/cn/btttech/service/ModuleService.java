package cn.btttech.service;

import java.util.List;

import cn.btttech.entity.Module;


public interface ModuleService {  
    
    public List<Module> listByPage(final int firstResult, final int maxResult);
    
    public List<Module> listByPage(Object condition, Object param, final int firstResult, final int maxResult);
    
    public List<Module> listByPage(Object[] conditions, Object [] params, final int firstResult, final int maxResult);
    
    public int listByPageCount();
    
    public int listByPageCount(Object condition, Object param);
    
    public int listByPageCount(Object[] conditions, Object [] params);
    
    public Module getById(int id);
    
    public List<Module> findAll();
    
}  