package tw.com.demo;

import tw.com.demo.generator.id.CaseIdGenerator;
import tw.com.demo.generator.shorten.key.UrlEncoder;
import tw.com.demo.mapper.UrlShortenCaseMapper;
import tw.com.demo.model.entity.UrlShorteningCase;
import tw.com.demo.model.vo.UrlShortenCaseApplyVO;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/14
 */
public class UrlShortener {
    private CaseIdGenerator caseIdGenerator;
    private UrlEncoder urlEncoder;

    private final Map<String, UrlShorteningCase> shortenedKeyToCaseMap = new HashMap<>();

    public UrlShorteningCase applyUrlShortenCase(UrlShortenCaseApplyVO urlShortenCaseApplyVO) {
        this.checkShortenedKeyExisted(urlShortenCaseApplyVO.getShortenedUrlKey());

        String caseId = this.caseIdGenerator.genCaseId();
        String shortenedUrlKey = this.generateShortenedUrlKey(urlShortenCaseApplyVO);
        String shortenedUrl = this.getShortenedUrl(shortenedUrlKey);

        UrlShorteningCase urlShorteningCase = UrlShortenCaseMapper.INSTANCE.toBO(urlShortenCaseApplyVO)
            .toBuilder()
            .setCaseId(caseId)
            .setShortenedUrlKey(shortenedUrlKey)
            .setShortenedUrl(shortenedUrl)
            .build();

        this.shortenedKeyToCaseMap.put(shortenedUrlKey, urlShorteningCase);

        return urlShorteningCase;
    }

    protected String generateShortenedUrlKey(UrlShortenCaseApplyVO urlShortenCaseApplyVO) {
        String result = urlShortenCaseApplyVO.getShortenedUrlKey();

        if (result == null) {
            result = this.urlEncoder.encodeUrl(urlShortenCaseApplyVO.getUrlToShorten());
        }

        return result;
    }

    protected String getShortenedUrl(String caseKey) {
        return String.format("%s/%s", this.getCurrentHost(), caseKey);
    }

    protected String getCurrentHost() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Cannot get host address");
        }
    }

    protected boolean isShortenedKeyExisted(String shortenedKey) {
        return this.shortenedKeyToCaseMap.containsKey(shortenedKey);
    }

    protected void checkShortenedKeyExisted(String shortenedKey) {
        if (this.isShortenedKeyExisted(shortenedKey)) {
            throw new IllegalArgumentException("The shortened key has been specified");
        }
    }

    public void setCaseIdGenerator(CaseIdGenerator caseIdGenerator) {
        this.caseIdGenerator = caseIdGenerator;
    }

    public void setUrlShortenKeyGenerator(UrlEncoder urlEncoder) {
        this.urlEncoder = urlEncoder;
    }
}
