package Dao;

import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONObject;
import Entity.Order;
import Entity.User;

/* OrderDao 订单持久化操作类
 * */

public interface OrderDao {
	//查看一段时间内的订单
	public Order[] getOrderByDate(Date startDate, Date endDate);
	//添加Order
	public void addOrder(Order order);
}
