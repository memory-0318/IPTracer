package tw.com.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tw.com.demo.service.UrlPathDecEncService;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/7
 */
@Controller
@RequiredArgsConstructor
public class RedirectController {
    private final UrlPathDecEncService urlPathDecEncService;

    @GetMapping(value = { "/{encodedUrlPath}" })
    public String redirectMiddlePage(@PathVariable("encodedUrlPath") String encodedUrlPath) {
        return "index";
    }

    @GetMapping("/reurl/{encodedUrlPath}/check")
    public ResponseEntity<Void> greeting(@PathVariable("encodedUrlPath") String encodedUrlPath) {
        try {
            URI realUri = new URI(this.urlPathDecEncService.decodeUrlPath(encodedUrlPath));

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(realUri);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Something went wrong when redirecting to requested url");
        }
    }
}
