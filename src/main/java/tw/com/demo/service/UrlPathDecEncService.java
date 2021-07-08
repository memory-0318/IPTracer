package tw.com.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/8
 */
@Service
public class UrlPathDecEncService {

    public String decodeUrlPath(String encodedUrlPath) {
        String decodedUrl = "http://www.google.com.tw";
        return decodedUrl;
    }
}
