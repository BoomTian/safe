package com.example.safe.interceptor;


import com.example.safe.config.MyWebConfig;
import com.example.safe.filter.BloomFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;


/**
 * @Author: tbw
 * @Date: 2019/11/22 16:50
 */
public class URLInterceptor implements HandlerInterceptor {
    private final String[] URLS = {
            "http://www.csdn.net/",
            "http://www.baidu.com/",
            "http://www.cnblogs.com/",
            "http://www.zhihu.com/",
            "https://www.shiyanlou.com/",
            "https://www.shiyanlou.com/",
            "http://www.csdn.net/"
    };
    @Autowired
    private MyWebConfig myWebConfig;

    private static final Logger logger = LoggerFactory.getLogger(URLInterceptor.class);

    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）调用,
     *  返回true 则放行， false 则将直接跳出方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        BloomFilter filter = new BloomFilter();
        for (int i = 0; i < URLS.length; i++) {
            if (filter.contains(URLS[i])) {
                System.out.println("contain: " + URLS[i]);
                continue;
            }
            filter.add(URLS[i]);
        }

        Document document = Jsoup.connect("http://www.yiibai.com").get();
        String text = document.text();
        System.out.println(document.text());
        String blacktext = myWebConfig.getText();
        if(text.contains(blacktext)){
            System.out.println(text.replaceAll(blacktext,""));
        }
        String ip = getIpAddr(request);
        String ipStr = myWebConfig.getAd(); // 获取可以访问系统的白名单
        String[] ipArr = ipStr.split("\\|");
        List<String> ipList = java.util.Arrays.asList(ipArr);

        if (ipList.contains(ip)) {
            logger.info("the request ip is : " + ip);
            return true;
        } else {
            logger.error(ip + " is not contains ipWhiteList .......");
            response.setHeader("Content-type","text/html;charset=UTF-8");//向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
            String data = "您好，ip为" + ip + ",暂时没有访问权限，请联系管理员开通访问权限。";
            OutputStream stream = response.getOutputStream();
            stream.write(data.getBytes("UTF-8"));
            return false;
        }
    }

    /**
     * 获取访问的ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}

