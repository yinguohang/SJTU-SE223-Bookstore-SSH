package Dao;

import java.util.Map;

import Entity.User;
import Entity.UserProfile;

/* UserDao 用户持久化操作类
 * */

public interface UserDao {
	//通过username获取user
	public User getUserByUsername(String username);
	//通过ID获取user
	public User getUserById(long userId);
	//更新user基础信息
	public void updateUser(User user);
	//添加user
	public void addUser(User user);
	//通过ID删除user
	public void removeUser(long userId);
	//获取所有user列表,并获得其订单数
	public Map<String, Object> getAllUser();
	//更新User的Password
	public void updateUserPassword(User user);
	//获取加载了Order的User
	public User getUserWithOrder(long userId);
	//获取UserProfile
	public UserProfile getUserProfileByMysqlId(long mysqlId);
	//添加UserProfile
	public void addUserProfile(UserProfile userProfile);
	//更新UserProfile
	public void updateUserProfile(UserProfile userProfile);
}
