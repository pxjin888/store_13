package cn.btttech.service;

import java.util.List;
import cn.btttech.entity.LogMaterial;


public interface MaterialLogService {  
    
    public List<LogMaterial> listByPage(final int firstResult, final int maxResult);
    
    public List<LogMaterial> listByPage(Object condition, Object param, final int firstResult, final int maxResult);
    
    public List<LogMaterial> listByPage(Object[] conditions, Object [] params, final int firstResult, final int maxResult);
    
    public int listByPageCount();
    
    public int listByPageCount(Object condition, Object param);
    
    public int listByPageCount(Object[] conditions, Object [] params);
    
    public LogMaterial getById(int id);
    
    public void save(LogMaterial logMaterial);
    
}  