package cn.btttech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.dao.BaseDao;
import cn.btttech.entity.User;
import cn.btttech.service.UserService;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {  
    @Autowired
    private BaseDao baseDao;  
  
    public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
  
    @Override 
    public User login(User user) {  
        List<User> list = baseDao.findAll(  
                "from User where user_name = ? and password = ?", User.class,  
                new Object[] { user.getUserName(), user.getPassword() });  
        if (list.size() == 1) {  
            return list.get(0);  
        }  
        return null;  
    }

	@Override
	public List<User> listByPage(final int firstResult, final int maxResult) {
		return listByPage(new Object[]{}, new Object[]{}, firstResult, maxResult);
	}

	@Override
	public List<User> listByPage(Object condition, Object param, final int firstResult, final int maxResult) {
		return listByPage(new Object[]{condition}, new Object[]{param}, firstResult, maxResult);
	}

	@Override
	public List<User> listByPage(Object[] conditions, Object[] params, final int firstResult, final int maxResult) {
		
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
		List<User> list = baseDao.findByPage(
				"from User "+cond, User.class, params,firstResult,maxResult);
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
		int count = baseDao.findByPageCount("from User "+cond, User.class, params);
		return count;
	}

	@Override
	public User getById(int id) {
		User user = baseDao.get(User.class, id);
		return user;
	}

}  