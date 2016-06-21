package Dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import Entity.User;
import Entity.UserProfile;

import com.ygh.HibernateUtil;

/* UserDaoImpl 用户持久化操作类实现
 * */

public class UserDaoImpl implements UserDao{
	private MongoTemplate mongoTemplate;
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	public User getUserByUsername(String username){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "from User where username=?";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameter(0, username,StandardBasicTypes.STRING);
		List<User> users = query.list();
		session.getTransaction().commit();	
		if (users != null && users.size() !=0){
			//存在
			return users.get(0);
		}else{
			//不存在
			return null;
		}
	}
	public User getUserById(long userId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user = (User)session.get(User.class, userId);
		session.getTransaction().commit();
		return user;
	}
	public void updateUser(User user){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery("update User user set user.coin = ?, user.mobile= ?, user.priority = ?, user.username=?, user.sex=? where id = ?");
		query.setDouble(0, user.getCoin());
		query.setLong(1, user.getMobile());
		query.setInteger(2, user.getPriority());
		query.setString(3, user.getUsername());
		query.setInteger(4, user.getSex());
		query.setLong(5, user.getId());
		query.executeUpdate();
		session.getTransaction().commit();
	}
	public void addUser(User user){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(user);
		String hql = "from User where username=? and password=?";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameter(0, user.getUsername(), StandardBasicTypes.STRING);
		query.setParameter(1, user.getPassword(), StandardBasicTypes.STRING);
		List<User> users = query.list();
		Hibernate.initialize(users.get(0).getOrders());
		user = users.get(0);
		session.getTransaction().commit();
	}
	public void removeUser(long userId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery("delete User as user where user.id=?");
		query.setParameter(0, userId, StandardBasicTypes.LONG);
		query.executeUpdate();
		session.getTransaction().commit();
	}
	public Map<String, Object> getAllUser(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery("from User");
		List list = query.list();
		User[] userArray = new User[list.size()];
		list.toArray(userArray);
		Arrays.sort(userArray);
		int[] orderNum = new int[list.size()];
		for (int i = 0; i < userArray.length; i++){
			orderNum[i] = userArray[i].getOrders().size();
		}
		Map<String, Object> ans = new HashMap();
		ans.put("userArray", userArray);
		ans.put("orderNum", orderNum);
		session.getTransaction().commit();	
		return ans;
	}
	public void updateUserPassword(User user){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery("update User user set user.password = ? where user.id = ?");
		query.setString(0, user.getPassword());
		query.setLong(1, user.getId());
		query.executeUpdate();
		session.getTransaction().commit();	
	}
	public User getUserWithOrder(long userId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user = (User)session.get(User.class, userId);
		Hibernate.initialize(user.getOrders());
		session.getTransaction().commit();
		return user;
	}
	public UserProfile getUserProfileByMysqlId(long mysqlId){
		Query query = new Query();
		Criteria criteria = Criteria.where("mysqlId").is(mysqlId);
		query.addCriteria(criteria);
		return mongoTemplate.findOne(query, UserProfile.class);
	}
	public void addUserProfile(UserProfile userProfile){
		mongoTemplate.insert(userProfile);
		userProfile.setId(getUserProfileByMysqlId(userProfile.getMysqlId()).getId());
	}
	public void updateUserProfile(UserProfile userProfile){
		Query query = new Query();  
		Criteria criteria = Criteria.where("id").is(userProfile.getId());
		query.addCriteria(criteria);  
        Update update = new Update();  
        update.set("mysqlId", userProfile.getMysqlId());
        update.set("profile", userProfile.getProfile());
        this.mongoTemplate.updateFirst(query, update, UserProfile.class);  
	}
}
