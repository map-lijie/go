package com.yn.go.common;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 全局序列号生成
 * yyyyMMddHHmmss +3位自增的序列号
 *
 */
public class SerialNoBuilder {
	
	private static final String DATE_FORMAT = "yyyyMMddHHmmss";
	
	private static final Integer DEFAULT_SEQUENCE_LENGTH=3;
	
	private static final DateFormat dateFormat =new  SimpleDateFormat(DATE_FORMAT);
	
	private static final NumberFormat numberFormat =NumberFormat.getInstance();
	
	private   Lock lock = new  ReentrantLock();
	
	private int bizNo = 1;
	
	private long lastTime;

	public String serializeNumInOneSecond(){
		return numberFormat.format(bizNo);
	}
	
	public  String format(Object date) {
		return dateFormat.format(date);
	}
	
	static{
		numberFormat.setGroupingUsed(false);
		numberFormat.setMaximumIntegerDigits(DEFAULT_SEQUENCE_LENGTH);  
		numberFormat.setMinimumIntegerDigits(DEFAULT_SEQUENCE_LENGTH);
	}
	
	public String getSerialNo() {
		try {
			lock.lock();
			long time =System.currentTimeMillis()/1000;
			if(lastTime==time){
				++bizNo;
			}else{
				bizNo =1;
			}
			lastTime = time;
			return  format(time)  + serializeNumInOneSecond();
		}finally{
			lock.unlock();
		}
		
	}
}
