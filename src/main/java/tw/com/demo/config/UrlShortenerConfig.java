package tw.com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tw.com.demo.generator.id.CaseIdGenerator;
import tw.com.demo.generator.id.SimpleCaseIdGenerator;
import tw.com.demo.generator.shorten.key.UrlEncoder;
import tw.com.demo.generator.shorten.key.UrlUUIDEncoderImpl;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/14
 */
@Configuration
public class UrlShortenerConfig {
    @Bean
    public CaseIdGenerator getCaseIdGenerator() {
        return new SimpleCaseIdGenerator();
    }

    @Bean
    public UrlEncoder getUrlEncoder() {
        return new UrlUUIDEncoderImpl();
    }
}
