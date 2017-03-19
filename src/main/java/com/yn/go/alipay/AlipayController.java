package com.yn.go.alipay;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.yn.go.alipay.config.AlipayConfig;
import com.yn.go.common.FrontInterceptor;
import com.yn.go.common.model.TournamentDetail;

public class AlipayController extends Controller{

	private static final Logger log =Logger.getLogger(AlipayController.class);
	
	/**
	 * 支付
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void pay(){
		//frontuser
	    String out_trade_no = getPara("orderId");
	    
	    Map<String,Object> frontuser = (Map)getSessionAttr("frontuser");
		Integer userId = (Integer) frontuser.get("id");
		
		// 订单名称，必填
	    String subject = "报名缴费";
		System.out.println(subject);
	    // 付款金额，必填
	    String total_amount=getPara("fee");
	    // 商品描述，可空
	    String body = "报名缴费";
	    // 超时时间 可空
	    String timeout_express="2m";
	    // 销售产品码 必填
	    String product_code="QUICK_WAP_PAY";
	    //调用RSA签名方式
	    AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
	    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
	    
	    // 封装请求支付信息
	    AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
	    model.setOutTradeNo(out_trade_no);
	    model.setSubject(subject);
	    model.setTotalAmount(total_amount);
	    model.setBody(body);
	    model.setTimeoutExpress(timeout_express);
	    model.setPassbackParams(userId+"");
	    model.setProductCode(product_code);
	    alipay_request.setBizModel(model);
	    // 设置异步通知地址
	    alipay_request.setNotifyUrl(AlipayConfig.notify_url);
	    // 设置同步地址
	    alipay_request.setReturnUrl(AlipayConfig.return_url);   
	    // form表单生产
	    String form = "";
		try {
			// 调用SDK生成表单
			form = client.pageExecute(alipay_request).getBody();
			log.info("pay===="+form);
			renderHtml(form);
			/*getResponse().setContentType("text/html;charset=" + AlipayConfig.CHARSET); 
			getResponse().getWriter().write(form);//直接将完整的表单html输出到页面 
			getResponse().getWriter().flush(); 
			getResponse().getWriter().close();*/
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * notify_url 地址
	 */
	@SuppressWarnings("rawtypes")
	@Clear(FrontInterceptor.class)
    public void callback(){
    	Map<String,String> params = new HashMap<String,String>();
    	Map requestParams = getRequest().getParameterMap();
    	log.info("callback====="+requestParams);
    	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
    		String name = (String) iter.next();
    		String[] values = (String[]) requestParams.get(name);
    		String valueStr = "";
    		for (int i = 0; i < values.length; i++) {
    			valueStr = (i == values.length - 1) ? valueStr + values[i]
    					: valueStr + values[i] + ",";
    		}
    		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
    		//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
    		params.put(name, valueStr);
    	}
    	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
    		//商户订单号
    		String out_trade_no = getPara("out_trade_no");
    		//支付宝交易号
    		//String trade_no = getPara("trade_no");
    		String passback_params = getPara("passback_params");//透传字段
    		String buyer_logon_id = getPara("buyer_logon_id");
    		//交易状态
    		String trade_status =getPara("trade_status");

    		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
    		//计算得出通知验证结果
    		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
			try {
				boolean  verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
				String result ="fail";
				if(verify_result){//验证成功
	    			if(trade_status.equals("TRADE_FINISHED")){
	    				//判断该笔订单是否在商户网站中已经做过处理
	    					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
	    					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
	    					//如果有做过处理，不执行商户的业务程序
	    					
	    				//注意：
	    				//如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
	    				//如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
	    			} else if (trade_status.equals("TRADE_SUCCESS")){
	    				 TournamentDetail td =new TournamentDetail();
	    				 td.setUpdateDatetime(new Date())
	    				.setOrderId(out_trade_no)
	    				.setFPayerId(Integer.valueOf(passback_params))
	    				.setPayAccount(buyer_logon_id)
	    				.setPayType(0)
	    				.setStatus(11);
	    				TournamentDetail.dao.update( new Record().setColumns(td.removeNullValueAttrs()));
	    			}
	    			result ="success";
	    			
	    		}
    			renderText(result);
			} catch (AlipayApiException e1) {
				e1.printStackTrace();
			}
	}
}
