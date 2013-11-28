package cn.btttech.service;

import java.util.List;
import cn.btttech.entity.MaterialFactory;


public interface MaterialFactoryService {  
    
    public List<MaterialFactory> listByPage(final int firstResult, final int maxResult);
    
    public List<MaterialFactory> listByPage(Object condition, Object param, final int firstResult, final int maxResult);
    
    public List<MaterialFactory> listByPage(Object[] conditions, Object [] params, final int firstResult, final int maxResult);
    
    public int listByPageCount();
    
    public int listByPageCount(Object condition, Object param);
    
    public int listByPageCount(Object[] conditions, Object [] params);
    
    public MaterialFactory getById(int id);
    
    public void save(MaterialFactory materialFactory);
    
    public List<MaterialFactory> list();
}  