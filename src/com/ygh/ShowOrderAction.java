package com.ygh;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.hibernate.Session;

import Entity.Order;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* ShowOrderAction 显示用户订单
 * */
public class ShowOrderAction extends ActionSupport{
	private String content;
	List<Order> orderList;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String execute() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> parameters = context.getParameters();
		Map<String, Object> hSession = context.getSession();
		JSONObject json = new JSONObject();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user = (User)hSession.get("user");
		if (user.getOrders() == null){
			content = "当前无订单!";
			return "finish";
		}
		if (user.getOrders().size() == 0){
			content = "当前无订单!";
			return "finish";
		}
		Order[] orderArray = new Order[user.getOrders().size()];
		session.getTransaction().commit();	
		user.getOrders().toArray(orderArray);
		Arrays.sort(orderArray);
		orderList = Arrays.asList(orderArray);
		return "continue";
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
}
