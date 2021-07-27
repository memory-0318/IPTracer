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

    void saveUrlShorteningCase(UrlShorteningCase urlShorteningCase);

    Optional<UrlShorteningCase> getUrlShorteningCaseByShortenedUrlKey(String shortenedUrlKey);

    Optional<UrlShorteningCase> getUrlShorteningCaseByCaseId(String caseId);

    boolean isShortenedUrlKeyExisted(String shortenedUrlKey);

    boolean isCaseIdExisted(String caseId);
}
