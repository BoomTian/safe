package com.example.safe.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @Author: tbw
 * @Date: 2019/11/22 17:14
 */
@Component
@ConfigurationProperties(prefix = "ip")
@PropertySource(value = "classpath:ip.properties")
public class MyWebConfig{
    @Value("${ad}")
    private String ad;
    @Value("${text}")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAd() {
        return ad;
    }
    public void setAd(String ad){
        this.ad=ad;
    }
}

