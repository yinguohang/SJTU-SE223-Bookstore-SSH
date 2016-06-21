package com.ygh;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.json.JSONObject;
import Entity.Book;
import Entity.Order;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* StatisticsPersonalAction 统计用户个人信息
 * */

public class StatisticsPersonalAction extends ActionSupport{
	private String content;
	Map<String, Integer[]> mapDay;
	Map<String, Integer[]> mapMonth;
	Map<String, Integer[]> mapYear;
	int[] typeBuy;
	public Map<String, Integer[]> getMapDay() {
		return mapDay;
	}
	public void setMapDay(Map<String, Integer[]> mapDay) {
		this.mapDay = mapDay;
	}
	public Map<String, Integer[]> getMapMonth() {
		return mapMonth;
	}
	public void setMapMonth(Map<String, Integer[]> mapMonth) {
		this.mapMonth = mapMonth;
	}
	public Map<String, Integer[]> getMapYear() {
		return mapYear;
	}
	public void setMapYear(Map<String, Integer[]> mapYear) {
		this.mapYear = mapYear;
	}
	public int[] getTypeBuy() {
		return typeBuy;
	}
	public void setTypeBuy(int[] typeBuy) {
		this.typeBuy = typeBuy;
	}
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
		User user = (User)hSession.get("user");
		Iterator iter = user.getOrders().iterator();
		SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		mapDay = new HashMap();
		mapMonth = new HashMap();
		mapYear = new HashMap();
		typeBuy = new int[4];
		while (iter.hasNext()){
			Order order = (Order)iter.next();
			Iterator iterator = order.getBooks().entrySet().iterator();
			while (iterator.hasNext()){
				Entry en = (Entry)iterator.next();
				Book bo = (Book)en.getKey();
				typeBuy[bo.getType()] += (int)en.getValue();
			}
			String day = sdfDay.format(order.getCreateDate());
			if (mapDay.containsKey(day)){
				Integer[] intArr = mapDay.get(day);
				intArr[0] += order.getAllBookNumber();
				intArr[1] += 1;
				mapDay.put(day, intArr);
			}else{
				Integer[] intArr = new Integer[2];
				intArr[0] = order.getAllBookNumber();
				intArr[1] = 1;
				mapDay.put(day, intArr);
			}
			String month = sdfMonth.format(order.getCreateDate());
			if (mapMonth.containsKey(month)){
				Integer[] intArr = mapMonth.get(month);
				intArr[0] += order.getAllBookNumber();
				intArr[1] += 1;
				mapMonth.put(month, intArr);
			}else{
				Integer[] intArr = new Integer[2];
				intArr[0] = order.getAllBookNumber();
				intArr[1] = 1;
				mapMonth.put(month, intArr);
			}
			String year = sdfYear.format(order.getCreateDate());
			if (mapYear.containsKey(year)){
				Integer[] intArr = mapYear.get(year);
				intArr[0] += order.getAllBookNumber();
				intArr[1] += 1;
				mapYear.put(year, intArr);
			}else{
				Integer[] intArr = new Integer[2];
				intArr[0] = order.getAllBookNumber();
				intArr[1] = 1;
				mapYear.put(year, intArr);
			}
		}
		return "continue";
	}

}
