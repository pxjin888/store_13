package cn.btttech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import cn.btttech.entity.LogMaterial;
import cn.btttech.entity.Material;
import cn.btttech.entity.MaterialFactory;
import cn.btttech.entity.MaterialPrice;


public interface MaterialService {  
    
    public List<Material> listByPage(final int firstResult, final int maxResult);
    
    public List<Material> listByPage(Object condition, Object param, final int firstResult, final int maxResult);
    
    public List<Material> listByPage(Object[] conditions, Object [] params, final int firstResult, final int maxResult);
    
    public int listByPageCount();
    
    public int listByPageCount(Object condition, Object param);
    
    public int listByPageCount(Object[] conditions, Object [] params);
    
    public Material getById(int id);
    
    public void save(Material material);
    
    public void update(Material material);
    
    public List<MaterialPrice> getMaterialFactories(Material material);
    
    public void firstMaterialInput(LogMaterial logMaterial, int materialId, int materialFactoryId, float materialNum, float price);
    
    public void materialOutput(LogMaterial logMaterial, int materialId, String []materialFactoryIds,  String [] materialPriceIds, String [] materialNums, Float totalMaterialNum);
    
}  