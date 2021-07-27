package tw.com.demo.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tw.com.demo.model.entity.UrlShorteningCase;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/26
 */
@Component
public class SimpleUrlShorteningCaseDAOImpl implements UrlShorteningCaseDAO {
    private final Map<String, UrlShorteningCase> shortenedKeyToCaseMap = new HashMap<>();

    @Override
    public void applyUrlShorteningCase(UrlShorteningCase urlShorteningCase) {
        this.shortenedKeyToCaseMap.put(urlShorteningCase.getShortenedUrlKey(), urlShorteningCase);
    }

    @Override
    public void saveUrlShorteningCase(UrlShorteningCase urlShorteningCase) {
        this.shortenedKeyToCaseMap.put(urlShorteningCase.getShortenedUrlKey(), urlShorteningCase);
    }

    @Override
    public Optional<UrlShorteningCase> getUrlShorteningCaseByShortenedUrlKey(String shortenedUrlKey) {
        return Optional.ofNullable(this.shortenedKeyToCaseMap.get(shortenedUrlKey));
    }

    @Override
    public Optional<UrlShorteningCase> getUrlShorteningCaseByCaseId(String caseId) {
        return this.shortenedKeyToCaseMap.values()
            .stream()
            .filter(urlShorteningCase -> StringUtils.equals(urlShorteningCase.getCaseId(), caseId))
            .findAny();
    }

    @Override
    public boolean isShortenedUrlKeyExisted(String shortenedUrlKey) {
        return this.shortenedKeyToCaseMap.containsKey(shortenedUrlKey);
    }

    @Override
    public boolean isCaseIdExisted(String caseId) {
        return false;
    }
}
