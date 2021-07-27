package tw.com.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tw.com.demo.model.dto.ClientIpInfoDTO;
import tw.com.demo.model.vo.ClientInfoVO;
import tw.com.demo.service.UrlShortenService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/7
 */
@Controller
@RequiredArgsConstructor
public class RedirectController {
    private final UrlShortenService urlShortenService;

    @GetMapping(value = { "/route/{shortenedUrlKey}" })
    public String redirectMiddlePage(@PathVariable("shortenedUrlKey") String shortenedUrlKey) {
        return "index";
    }

    @PostMapping("/route/{shortenedUrlKey}/forward")
    public ResponseEntity<Void> forwardToOriginalUrl(
        @PathVariable("shortenedUrlKey") String shortenedUrlKey,
        @RequestBody ClientIpInfoDTO clientIpInfoDTO,
        HttpServletRequest request
    ) {
        try {
            String originalUrl = this.urlShortenService.retrieveOriginalUrl(shortenedUrlKey);
            this.urlShortenService.recordClientInfo(
                shortenedUrlKey,
                ClientInfoVO.builder()
                    .setTriggeredDateTime(OffsetDateTime.now())
                    .setHostname(clientIpInfoDTO.getHostname())
                    .setIpAddress(clientIpInfoDTO.getIpAddress())
                    .setUserAgent(this.extractUserAgent(request))
                    .build()
            );

            URI realUri = new URI(originalUrl);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(realUri);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Something went wrong when redirecting to requested url");
        }
    }

    private String extractUserAgent(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.USER_AGENT.toString());
    }
}
