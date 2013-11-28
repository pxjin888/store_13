package cn.btttech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.dao.BaseDao;
import cn.btttech.entity.Operation;
import cn.btttech.service.OperationService;
import cn.btttech.util.Page;
import cn.btttech.util.Result;

@Service("operationService")
@Transactional(readOnly = true)
public class OperationServiceImpl implements OperationService {  
    @Autowired
    private BaseDao baseDao;  
  
    public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
  
	@Override
	public Result<Operation> listByPage(Page page) {
		return listByPage(new Object[]{}, new Object[]{}, page);
	}

	@Override
	public Result<Operation> listByPage(Object condition, Object param, Page page) {
		return listByPage(new Object[]{condition}, new Object[]{param}, page);
	}

	@Override
	public Result<Operation> listByPage(Object[] conditions, Object[] params, Page page) {
		
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
		List<Operation> list = baseDao.findByPage(
				"from Operation "+cond, Operation.class, params,(page.getPageNum()-1)*page.getNumPerPage()+1,page.getNumPerPage());
		int totalCount = baseDao.findByPageCount("select count(*) from Operation "+cond, Operation.class, params);
		System.out.println("NumPerPage:"+page.getNumPerPage());
		page.setCount(totalCount);
		
		return new Result<Operation>(page, list);
	}

	@Override
	public Operation getById(int id) {
		Operation operation = baseDao.get(Operation.class, id);
		return operation;
	}
	
	public List<Operation> findAll(){
		List<Operation> list = baseDao.findAll("from Operation", Operation.class);
		return list;
	}
}  