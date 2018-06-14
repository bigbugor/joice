package org.joice.common.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 名称：MsgModel<br>
 * <p>
 * 描述：消息模型<br>
 *
 * @author frame<br>
 * 时间：<br>
 * 2017-09-07 15:44:59<br>
 * 版权：<br>
 * Copyright 2017 <a href="https://github.com/micyo202" target="_blank">https://github.com/micyo202</a>. All rights reserved.
 */
public class MsgModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态值<br>
     */
    private String status;

    /**
     * 消息<br>
     */
    private String msg;

    /**
     * 返回对象<br>
     */
    private Object res;

    /**
     * 获取状态值<br>
     *
     * @return String 状态值
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态值<br>
     *
     * @param status 状态值
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取消息<br>
     *
     * @return String 消息内容
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置消息<br>
     *
     * @param msg 消息内容
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取结果对象<br>
     *
     * @return Object 结果对象
     */
    public Object getRes() {
        return res;
    }

    /**
     * 设置结果对象<br>
     *
     * @param res 结果对象
     */
    public void setRes(Object res) {
        this.res = res;
    }

    /**
     * 无参构造方法，构建 MsgModel 对象<br>
     */
    public MsgModel() {
        super();
    }

    /**
     * 代参构造方法，构建 MsgModel 对象<br>
     *
     * @param status 状态值
     * @param msg    消息内容
     * @param res    结果对象
     */
    public MsgModel(String status, String msg, Object res) {
        super();
        this.status = status;
        this.msg = msg;
        this.res = res;
    }

    /**
     * 代参构造方法，构建 MsgModel 对象<br>
     *
     * @param status 状态值
     * @param msg    消息内容
     */
    public MsgModel(String status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    /**
     * 代参构造方法，构建 MsgModel 对象<br>
     *
     * @param msg 消息内容
     */
    public MsgModel(String msg) {
        super();
        this.msg = msg;
    }

	@Override
	public String toString() {
		String mapStr="";
		if(res instanceof HashMap){
			HashMap<String, Object> params=(HashMap<String, Object>) res;
			try {
				mapStr=	this.createLinkStringByGet(params);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "MsgModel [status=" + status + ", msg=" + msg + ", res=" + mapStr + "]";
	}
    
	public  String createLinkStringByGet(HashMap<String, Object> params) throws UnsupportedEncodingException {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Object value = (Object) params.get(key);
			//判断返回值的类型
			if(value instanceof String){ //如果是字符串
				String strValue =(String) value;
				strValue = URLEncoder.encode(strValue, "UTF-8");
				if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
					prestr = prestr + key + "=" + value;
				} else {
					prestr = prestr + key + "=" + value + "&";
				}
			}else if (value instanceof Integer||value instanceof Double||value instanceof Float){
				String strValue =value+"";
				strValue = URLEncoder.encode(strValue, "UTF-8");
				if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
					prestr = prestr + key + "=" + value;
				} else {
					prestr = prestr + key + "=" + value + "&";
				}
			}
			else if (value instanceof HashMap){
				HashMap<String, Object> valueMap = (HashMap<String, Object>) value;
				prestr +=createLinkStringByGet(valueMap);
			}
			
		}
		return prestr;
	}
    

}
