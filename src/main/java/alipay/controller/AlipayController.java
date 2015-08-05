package alipay.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import alipay.bean.Order;
import alipay.config.AlipayConfig;
import alipay.util.AlipayNotify;
import alipay.util.AlipaySubmit;

import com.sun.jersey.spi.resource.Singleton;

/**
 * 提交订单至支付宝付款
 * 
 * @author Administrator
 * 
 */
@Controller
@Singleton
//Spring会针对每一个request请求都生成新的Jersey服务类实例，此方法不需要配置Spring RequsetContextListener
@Scope("prototype")
@Path(value = "/alipay")
public class AlipayController {
    
    /**
     * <pre>
     * 建立请求，以表单HTML形式构造（默认）
     * 
     * [form id="alipaysubmit" name="alipaysubmit" action="https://mapi.alipay.com/gateway.do?_input_charset=gbk" method="get"]
     *     [input type="hidden" name="payment_type" value="1"/]
     *     [input type="hidden" name="_input_charset" value="gbk"/]
     *     [input type="hidden" name="service" value="create_direct_pay_by_user"/]
     *     [input type="hidden" name="sign" value="1ac2d93ba7132130e85c15abfbeeb0eb"/]
     *     [input type="hidden" name="return_url" value="http://商户网关地址/create_direct_pay_by_user-JAVA-GBK/return_url.jsp"/]
     *     [input type="hidden" name="notify_url" value="http://商户网关地址/create_direct_pay_by_user-JAVA-GBK/notify_url.jsp"/]
     *     [input type="hidden" name="sign_type" value="MD5"/]
     *     [input type="submit" value="确认" style="display:none;"]
     * [/form]
     * [script]document.forms['alipaysubmit'].submit();[/script]
     * 
     * </pre>
     */
    @Path(value = "/deposit")
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String deposit(@Context
    HttpServletRequest request, @Context
    HttpServletResponse response, @FormParam(value = "order")
    Order order) throws Exception {
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("partner", AlipayConfig.PARTNER);//支付宝PID
        sParaTemp.put("_input_charset", AlipayConfig.INPUT_CHARSET);//统一编码
        sParaTemp.put("seller_email", AlipayConfig.SELLER_EMAIL);//卖家支付宝账号
        
        sParaTemp.put("service", "create_direct_pay_by_user");//接口服务----即时到账
        sParaTemp.put("payment_type", "1");//支付类型----商品类型购买
        sParaTemp.put("notify_url", "http://localhost:8080/zhifubao/alipay/asyncNotify");//服务器异步通知页面路径 ，需http://格式的完整路径，不能加?id=123这类自定义参数
        sParaTemp.put("return_url", "http://localhost:8080/zhifubao/alipay/syncNotify");//页面跳转同步通知页面路径 ，需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
        sParaTemp.put("show_url", "http://localhost:8080/zhifubao/show.jsp");//商品展示地址，需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html
        sParaTemp.put("anti_phishing_key", AlipaySubmit.query_timestamp());// 防钓鱼时间戳 ，若要使用请调用类文件submit中的query_timestamp函数
        sParaTemp.put("exter_invoke_ip", "127.0.0.1");//客户端的IP地址，非局域网的外网IP地址，如：221.0.0.1
        
        sParaTemp.put("out_trade_no", order.getOrderId());//订单编号
        sParaTemp.put("subject", order.getOrderName());//订单名称
        sParaTemp.put("total_fee", order.getPrice().toString());//订单价格
        sParaTemp.put("body", order.getContent());//订单内容
        
        
        //建立请求
        try {
            String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
            return sHtmlText;
        } catch (Exception e) {
            String result = "{\"success\":false,\"msg\":\"跳转失败，请稍候再试！\"}";
            return result;
        }
    }
    
    /**
     * 同步通知的页面的Controller
     * 
     * @param request
     * @param response
     * @return
     */
    @Path(value = "syncNotify")
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String syncNotify(@Context
    HttpServletRequest request, @Context
    HttpServletResponse response) {
        return "return_url.jsp";
    }
    
    /**
     * 异步通知付款状态的Controller
     * 
     * @param request
     * @param response
     * @return
     */
    @Path(value = "asyncNotify")
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String asyncNotify(@Context
    HttpServletRequest request, @Context
    HttpServletResponse response) {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String)iter.next();
            String[] values = (String[])requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        String tradeNo = request.getParameter("out_trade_no");
        String tradeStatus = request.getParameter("trade_status");
        //String notifyId = request.getParameter("notify_id");  
        if (AlipayNotify.verify(params)) {
            //验证成功
            if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {
                //要写的逻辑。自己按自己的要求写
                System.out.println(">>>>>充值成功" + tradeNo);
            }
            return "notify_url.jsp";
        } else {
            //验证失败  
            return "fail.jsp";
        }
        
    }
}
