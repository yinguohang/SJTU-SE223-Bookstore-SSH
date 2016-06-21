package com.ygh;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import Biz.BookBiz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* AddBookToShopCartAction 将书籍添加至购物车
 * */

public class AddBookToShopCartAction extends ActionSupport{
	private String content;
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
		if (parameters.get("bookId") == null){
			json.put("status", "Error");
			json.put("message", "参数为空！");
			content = json.toString();
			return SUCCESS;
		}
		int bookId = Integer.parseInt(((String[])parameters.get("bookId"))[0]);
		int bookNum;
		if (parameters.get("bookNum") == null){
			bookNum = 1;
		}else{
			bookNum = Integer.parseInt(((String[])parameters.get("bookNum"))[0]);
		}
		HashMap<Integer, Integer> hashMap;
		if (hSession.get("shopCart") == null){
			hashMap = new HashMap();
		}else{
			hashMap = (HashMap)hSession.get("shopCart");
		}
		if (hashMap.containsKey(bookId)){
			int num = hashMap.get(bookId) + bookNum;
			if (num > 0)
				hashMap.put(bookId, hashMap.get(bookId) + bookNum);
			else
				hashMap.remove(bookId);
		}else{
			hashMap.put(bookId, bookNum);
		}
		hSession.put("shopCart", hashMap);
		json.put("status", "Success");
		json.put("message", "添加成功！");
		setContent(json.toString());
		return SUCCESS;
	}
}
