package tw.com.demo.dao;

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
    public Optional<UrlShorteningCase> getUrlShorteningCase(String shortenedUrlKey) {
        return Optional.ofNullable(this.shortenedKeyToCaseMap.get(shortenedUrlKey));
    }

    @Override
    public boolean isShortenedUrlKeyExisted(String shortenedUrlKey) {
        return this.shortenedKeyToCaseMap.containsKey(shortenedUrlKey);
    }
}
