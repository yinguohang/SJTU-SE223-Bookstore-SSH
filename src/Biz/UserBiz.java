package Biz;

import java.util.Map;

import net.sf.json.JSONObject;
import Entity.User;
import Entity.UserProfile;

/* UserBiz 用户服务层，处理获取用户数据的相关服务
 * */

public interface UserBiz {
	public User getUserByUsernameAndPassword(String username, String password);
	public boolean isAvailable(String username);
	public void ModifyUser(Map<String, Object> parameters);
	public boolean addUser(User user);
	public void removeUser(long userId);
	public Map<String, Object> getAllUser();
	public boolean changePassword(User user, String originalPassword, String newPassword);
	public void addUserProfileItem(User user, String key, String value);
	public UserProfile getUserProfileByMysqlId(long mysqlId);
	public void removeUserProfileItem(User user, String key);
}
