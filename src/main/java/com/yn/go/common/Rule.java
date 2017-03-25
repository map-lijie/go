package com.yn.go.common;

import java.io.UnsupportedEncodingException;


public class Rule {

	private  String field;
	private  String op;
	private  String data;
	
	
	public Rule() {
		super();
	}
	public void setField(String field) {
        int len = field.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = field.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
		this.field = sb.toString();
	}
	public void setOp(String op) {
		this.op = op;
	}
	public void setData(String data) {
		try {
			this.data = new String(data.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Rule(String field, String op, String data) {
		super();
		this.field = field;
		this.op = op;
		this.data = data;
	}
	public String getField() {
		return field;
	}
	public String getOp() {
		return op;
	}
	public String getData() {
		return data;
	}
	@Override
	public String toString() {
		return "Rule [field=" + field + ", op=" + op + ", data=" + data + "]";
	}
	
}
