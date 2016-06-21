package Biz;

import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONObject;
import Entity.Order;
import Entity.User;

/* OrderBiz 订单服务层，处理获取订单数据的相关服务
 * */

public interface OrderBiz {
	//user将shopCart下单，json操作状态
	public boolean createOrderFromShopCart(HashMap shopCart, User user, JSONObject json);
	public Order[] getOrderByDate(Date startDate, Date endDate);
}
