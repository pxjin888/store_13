package cn.btttech.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.dao.BaseDao;
import cn.btttech.entity.LogMaterial;
import cn.btttech.entity.Material;
import cn.btttech.entity.MaterialFactory;
import cn.btttech.entity.MaterialPrice;
import cn.btttech.service.MaterialService;
import cn.btttech.service.ModuleService;
import cn.btttech.service.OperationService;

@Service("materialService")
@Transactional(readOnly = true)
public class MaterialServiceImpl implements MaterialService {  
    @Autowired
    private BaseDao baseDao; 

  
    public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
    
    
  
	@Override
	public List<Material> listByPage(final int firstResult, final int maxResult) {
		return listByPage(new Object[]{}, new Object[]{}, firstResult, maxResult);
	}

	@Override
	public List<Material> listByPage(Object condition, Object param, final int firstResult, final int maxResult) {
		return listByPage(new Object[]{condition}, new Object[]{param}, firstResult, maxResult);
	}

	@Override
	public List<Material> listByPage(Object[] conditions, Object[] params, final int firstResult, final int maxResult) {
		
		String cond = "";
		if(conditions.length != 0){
			cond = " where";
		}
		for(int i=0;i<conditions.length;i++){
			cond+=" "+conditions[i]+" and";
		}
		if(cond.endsWith("and")){
			cond = cond.substring(0, cond.length()-3);
		}
		List<Material> list = baseDao.findByPage(
				"from Material "+cond, Material.class, params,firstResult,maxResult);
		return list;
	}

	@Override
	public int listByPageCount() {
		return listByPageCount(new Object[]{}, new Object[]{});
	}

	@Override
	public int listByPageCount(Object condition, Object param) {
		// TODO Auto-generated method stub
		return listByPageCount(new Object[]{condition}, new Object[]{param});
	}

	@Override
	public int listByPageCount(Object[] conditions, Object[] params) {
		String cond = "";
		for(int i=0;i<conditions.length;i++){
			cond+=" "+conditions[i];
		}
		int count = baseDao.findByPageCount("from Material "+cond, Material.class, params);
		return count;
	}

	@Override
	public Material getById(int id) {
		Material material = baseDao.get(Material.class, id);
		return material;
	}



	@Override
	@Transactional(readOnly = false)
	public void save(Material material) {
		baseDao.save(material);
	}
	@Override
	@Transactional(readOnly = false)
	public void update(Material material) {
		baseDao.update(material);
	}


	@Override
	public List<MaterialPrice> getMaterialFactories(Material material) {
		
		//先进先出
		TreeMap<MaterialFactory,MaterialPrice> map = new TreeMap<MaterialFactory, MaterialPrice>();
		List<MaterialPrice> prices = baseDao.findAll("from MaterialPrice price where price.material.materialId = ? order by price.materialPriceTime asc ", MaterialPrice.class, 
				material.getMaterialId());
//		for (MaterialPrice materialPrice : prices) {
//			MaterialFactory factory = materialPrice.getMaterialFactory();
//			map.put(factory, materialPrice);
//		}
		return prices;
	}
	
	//一级物料入库
	@Override
	@Transactional(readOnly = false)
	public void firstMaterialInput(LogMaterial logMaterial, int materialId, int materialFactoryId, float materialNum, float price){
		Material material = baseDao.get(Material.class, materialId);
		Float preNum = material.getMaterialNum();
		Set<LogMaterial> logMaterials = new HashSet<LogMaterial>();
		logMaterials.add(logMaterial);
		
		MaterialPrice materialPrice = new MaterialPrice();
		materialPrice.setMaterial(material);
		
		MaterialFactory materialFactory = baseDao.get(MaterialFactory.class, materialFactoryId); 
		
		materialPrice.getMaterialFactories().add(materialFactory);
		materialPrice.setMaterialPartNum(materialNum);
		materialPrice.setMaterialPriceInputprice(price);
		materialPrice.setMaterialPriceTime(new Date());
		
		material.getMaterialPrices().add(materialPrice);
		material.setMaterialNum(preNum+materialNum);
		material.setLogs(logMaterials);
		
		logMaterial.setMaterialFactory(materialFactory);
		logMaterial.setMaterial(material);
		
		materialFactory.getMaterialPrices().add(materialPrice);
		
		baseDao.update(material);
	
	}
	
	
	//物料出库
	@Override
	@Transactional(readOnly = false)
	public void materialOutput(LogMaterial logMaterial, int materialId, String []materialFactoryIds,  String [] materialPriceIds, String [] materialNums, Float totalMaterialNum){
		
		Material material = baseDao.get(Material.class, materialId);
		HashSet<MaterialPrice> materialPrices = new HashSet<MaterialPrice>();
		for (int i = 0; i < materialPriceIds.length; i++) {
			MaterialPrice materialPrice = baseDao.get(MaterialPrice.class, Integer.parseInt(materialPriceIds[i]));
			materialPrice.setMaterialPartNum(materialPrice.getMaterialPartNum() - Float.parseFloat(materialNums[i]));
			materialPrices.add(materialPrice);
		}
		
		material.setMaterialPrices(materialPrices);
		material.setMaterialNum(material.getMaterialNum() - totalMaterialNum);
		Set<LogMaterial> logMaterials = new HashSet<LogMaterial>();
		logMaterials.add(logMaterial);
		material.setLogs(logMaterials);
		
		logMaterial.setMaterial(material);
		
		baseDao.update(material);
		
	}
	//二级物料入库，即物料拼合
	
	
	
}  