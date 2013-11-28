package cn.btttech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.dao.BaseDao;
import cn.btttech.entity.Material;
import cn.btttech.entity.MaterialFactory;
import cn.btttech.entity.MaterialPrice;
import cn.btttech.service.MaterialPriceService;

@Service("materialPriceService")
@Transactional(readOnly = true)
public class MaterialPriceServiceImpl implements MaterialPriceService {  
    @Autowired
    private BaseDao baseDao; 

    public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
    
    
  
	@Override
	public List<MaterialPrice> listByPage(final int firstResult, final int maxResult) {
		return listByPage(new Object[]{}, new Object[]{}, firstResult, maxResult);
	}

	@Override
	public List<MaterialPrice> listByPage(Object condition, Object param, final int firstResult, final int maxResult) {
		return listByPage(new Object[]{condition}, new Object[]{param}, firstResult, maxResult);
	}

	@Override
	public List<MaterialPrice> listByPage(Object[] conditions, Object[] params, final int firstResult, final int maxResult) {
		
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
		List<MaterialPrice> list = baseDao.findByPage(
				"from MaterialPrice "+cond, MaterialPrice.class, params,firstResult,maxResult);
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
		int count = baseDao.findByPageCount("from MaterialPrice "+cond, MaterialPrice.class, params);
		return count;
	}

	@Override
	public MaterialPrice getById(int id) {
		MaterialPrice materialPrice = baseDao.get(MaterialPrice.class, id);
		return materialPrice;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(MaterialPrice materialPrice) {
		baseDao.save(materialPrice);
	}


	@Override
	public MaterialPrice combineMaterialItem(Material material,
			MaterialFactory materialFactory, float inputPrice) {
		
		List<MaterialPrice> list = baseDao.findAll("from MaterialPrice materialPrice where materialPrice.material = ? and materialPrice.materialFactory = ? and materialPrice.materialPriceInputprice = ?", MaterialPrice.class,
				new Object[]{material, materialFactory, inputPrice});
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}



	@Override
	@Transactional(readOnly = false)
	public void update(MaterialPrice materialPrice) {
		baseDao.update(materialPrice);
	}

}  