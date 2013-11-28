package cn.btttech.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.dao.BaseDao;
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

    @Resource(name="moduleService")
	private ModuleService moduleService;
	
	@Resource(name="operationService")
	private OperationService operationService;
  
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


}  