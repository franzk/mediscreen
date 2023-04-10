package com.abernathy.mediscreen.massessment.config;

import com.abernathy.mediscreen.massessment.exception.MyErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignErrorDecoderConfig {

    @Bean
    public MyErrorDecoder myErrorDecoder() {
        return new MyErrorDecoder();
    }

}
