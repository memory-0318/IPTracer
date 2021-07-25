package tw.com.demo.generator.shorten.key;

import java.util.UUID;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/14
 */
public class UrlUUIDEncoderImpl implements UrlEncoder {
    @Override
    public String encodeUrl(String url) {
        return UUID.randomUUID().toString();
    }
}
