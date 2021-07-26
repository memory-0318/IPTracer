package tw.com.demo.dao;

import tw.com.demo.model.entity.UrlShorteningCase;

import java.util.Optional;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/26
 */
public interface UrlShorteningCaseDAO {
    void applyUrlShorteningCase(UrlShorteningCase urlShorteningCase);

    Optional<UrlShorteningCase> getUrlShorteningCase(String shortenedUrlKey);

    boolean isShortenedUrlKeyExisted(String shortenedUrlKey);
}
