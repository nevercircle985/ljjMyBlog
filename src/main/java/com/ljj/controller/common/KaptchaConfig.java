package com.ljj.controller.common;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 设置边框
        properties.put("kaptcha.border", "no");
        // 设置字体颜色
        properties.put("kaptcha.textproducer.font.color", "black");
        // 设置图片宽度
        properties.put("kaptcha.image.width", "150");
        // 设置图片高度
        properties.put("kaptcha.image.height", "40");
        // 设置字体尺寸
        properties.put("kaptcha.textproducer.font.size", "30");
        // 设置session key
        properties.put("kaptcha.session.key", "verifyCode");
        // 设置验证码长度
        properties.put("kaptcha.textproducer.char.length", "5");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}