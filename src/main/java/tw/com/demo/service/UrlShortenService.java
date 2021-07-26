package tw.com.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tw.com.demo.generator.id.CaseIdGenerator;
import tw.com.demo.generator.shorten.key.UrlEncoder;
import tw.com.demo.mapper.UrlShortenCaseMapper;
import tw.com.demo.model.entity.TriggeredClientInfo;
import tw.com.demo.model.entity.UrlShorteningCase;
import tw.com.demo.model.vo.ClientInfoVO;
import tw.com.demo.model.vo.UrlShortenCaseApplyVO;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/13
 */
@RequiredArgsConstructor
@Service
public class UrlShortenService {
    private final CaseIdGenerator caseIdGenerator;
    private final UrlEncoder urlEncoder;

    private final Map<String, UrlShorteningCase> shortenedKeyToCaseMap = new HashMap<>();

    public UrlShorteningCase applyUrlShorteningCase(UrlShortenCaseApplyVO urlShortenCaseApplyVO) {
        this.checkShortenKeySpecified(urlShortenCaseApplyVO.getShortenedUrlKey());

        String caseId = this.caseIdGenerator.genCaseId();
        String shortenedUrlKey = this.generateShortenedUrlKey(urlShortenCaseApplyVO);
        String shortenedUrl = this.getShortenedUrl(shortenedUrlKey);

        UrlShorteningCase urlShorteningCase = UrlShortenCaseMapper.INSTANCE.toBO(urlShortenCaseApplyVO)
            .toBuilder()
            .setCaseId(caseId)
            .setOriginalUrl(urlShortenCaseApplyVO.getOriginalUrl())
            .setShortenedUrlKey(shortenedUrlKey)
            .setShortenedUrl(shortenedUrl)
            .build();

        this.shortenedKeyToCaseMap.put(shortenedUrlKey, urlShorteningCase);

        return urlShorteningCase;
    }

    public String retrieveOriginalUrl(String shortenedKey) {
        return Optional.ofNullable(this.shortenedKeyToCaseMap.get(shortenedKey))
            .map(UrlShorteningCase::getOriginalUrl)
            .orElseThrow(() -> new RuntimeException("The shortened url key is not existed"));
    }

    public void recordClientInfo(String shortenedKey, ClientInfoVO clientInfoVO) {
        UrlShorteningCase shorteningCase = this.shortenedKeyToCaseMap.get(shortenedKey);
        shorteningCase.addUrlShortenCaseDetail(TriggeredClientInfo.builder()
            .setTriggeredDateTime(clientInfoVO.getTriggeredDateTime())
            .setHostname(clientInfoVO.getHostname())
            .setIpAddress(clientInfoVO.getIpAddress())
            .setUserAgent(clientInfoVO.getUserAgent())
            .build());
    }

    protected String generateShortenedUrlKey(UrlShortenCaseApplyVO urlShortenCaseApplyVO) {
        String result = urlShortenCaseApplyVO.getShortenedUrlKey();

        if (result == null) {
            result = this.urlEncoder.encodeUrl(urlShortenCaseApplyVO.getOriginalUrl());
        }

        return result;
    }

    protected boolean isShortenedKeyExisted(String shortenedKey) {
        return this.shortenedKeyToCaseMap.containsKey(shortenedKey);
    }

    protected void checkShortenedKeyExisted(String shortenedKey) {
        if (this.isShortenedKeyExisted(shortenedKey)) {
            throw new IllegalArgumentException("The shortened key is not existed");
        }
    }

    protected String getShortenedUrl(String caseKey) {
        return String.format("%s/%s", this.getCurrentHost(), caseKey);
    }

    protected String getCurrentHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Cannot get host address");
        }
    }

    protected void checkShortenKeySpecified(String shortenedKey) {
        if (this.isShortenedKeyExisted(shortenedKey)) {
            throw new IllegalArgumentException("The shortened key has been specified");
        }
    }
}
