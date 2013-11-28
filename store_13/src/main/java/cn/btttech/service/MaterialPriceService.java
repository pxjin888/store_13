package cn.btttech.service;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import cn.btttech.entity.Material;
import cn.btttech.entity.MaterialFactory;
import cn.btttech.entity.MaterialPrice;


public interface MaterialPriceService {  
    
    public List<MaterialPrice> listByPage(final int firstResult, final int maxResult);
    
    public List<MaterialPrice> listByPage(Object condition, Object param, final int firstResult, final int maxResult);
    
    public List<MaterialPrice> listByPage(Object[] conditions, Object [] params, final int firstResult, final int maxResult);
    
    public int listByPageCount();
    
    public int listByPageCount(Object condition, Object param);
    
    public int listByPageCount(Object[] conditions, Object [] params);
    
    public MaterialPrice getById(int id);
    //返回可以合并的materialPrice项（规则 material， factory，inputprice 相同的）
    public MaterialPrice combineMaterialItem(Material material, MaterialFactory materialFactory, float inputPrice);
    
    public void save(MaterialPrice materialPrice);
    
    public void update(MaterialPrice materialPrice);
    
}  