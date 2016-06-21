package Biz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import com.ygh.HibernateUtil;

import net.sf.json.JSONObject;
import Dao.UserDao;
import Dao.UserDaoImpl;
import Entity.User;
import Entity.UserProfile;

/* UserBizImpl 用户服务层实现，处理获取用户数据的相关服务
 * */

public class UserBizImpl implements UserBiz{
	UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public UserBizImpl(){
		userDao = new UserDaoImpl();
	}
	public User getUserByUsernameAndPassword(String username, String password){
		User user = userDao.getUserByUsername(username);
		if (user == null){
			return null;
		}
		if (user.getPassword().equals(password)){
			user = userDao.getUserWithOrder(user.getId());
			return user;
		}else{
			return null;
		} 
	}
	public boolean isAvailable(String username){
		if (userDao.getUserByUsername(username) == null){
			return true;
		}else{
			return false;
		}
	}
	public void ModifyUser(Map<String, Object> parameters){
		Long userId = Long.parseLong(((String[])parameters.get("userId"))[0]);
		User user = userDao.getUserById(userId);
		if (parameters.get("username") != null){
			user.setUsername(((String[])parameters.get("username"))[0]);
		}
		if (parameters.get("sex") != null){
			user.setSex(Integer.parseInt(((String[])parameters.get("sex"))[0]));
		}
		if (parameters.get("coin") != null){
			user.setCoin(Double.parseDouble(((String[])parameters.get("coin"))[0]));
		}
		if (parameters.get("mobile") != null){
			user.setMobile(Long.parseLong(((String[])parameters.get("mobile"))[0]));
		}
		if (parameters.get("priority") != null){
			user.setPriority(Integer.parseInt(((String[])parameters.get("priority"))[0]));
		}
		userDao.updateUser(user);
	}
	public boolean addUser(User user){
		if (isAvailable(user.getUsername())){
			userDao.addUser(user);
			return true;
		}else{
			return false;
		}
	}
	public void removeUser(long userId){
		userDao.removeUser(userId);
	}
	//获取所有用户和其统计信息
	public Map<String, Object> getAllUser(){
		return userDao.getAllUser();
	}
	public boolean changePassword(User user, String originalPassword, String newPassword){
		//不从数据库中重新取出密码
		if (!user.getPassword().equals(originalPassword))
			return false;
		user.setPassword(newPassword);
		userDao.updateUserPassword(user);
		return true;
	}
	public void addUserProfileItem(User user, String key, String value){
		UserProfile userProfile = userDao.getUserProfileByMysqlId(user.getId());
		if (userProfile == null){
			userProfile = new UserProfile();
			userProfile.setMysqlId(user.getId());
			userDao.addUserProfile(userProfile);
		}
		String profile = userProfile.getProfile();
		JSONObject json;
		if (profile == null)
			json = new JSONObject();
		else
			json = JSONObject.fromObject(profile);
		json.put(key, value);
		userProfile.setProfile(json.toString());
		userDao.updateUserProfile(userProfile);
	}
	public UserProfile getUserProfileByMysqlId(long mysqlId){
		return userDao.getUserProfileByMysqlId(mysqlId);
	}
	public void removeUserProfileItem(User user, String key){
		UserProfile userProfile = userDao.getUserProfileByMysqlId(user.getId());
		if (userProfile == null){
			return;
		}
		String profile = userProfile.getProfile();
		JSONObject json;
		if (profile == null)
			return;
		else
			json = JSONObject.fromObject(profile);
		json.remove(key);
		userProfile.setProfile(json.toString());
		userDao.updateUserProfile(userProfile);
	}
}
