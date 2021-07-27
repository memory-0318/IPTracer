package tw.com.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tw.com.demo.dao.SimpleUrlShorteningCaseDAOImpl;
import tw.com.demo.generator.id.CaseIdGenerator;
import tw.com.demo.generator.shorten.key.UrlEncoder;
import tw.com.demo.mapper.UrlShortenCaseMapper;
import tw.com.demo.model.entity.TriggeredClientInfo;
import tw.com.demo.model.entity.UrlShorteningCase;
import tw.com.demo.model.vo.ClientInfoVO;
import tw.com.demo.model.vo.UrlShortenCaseApplyVO;
import tw.com.demo.model.vo.UrlShorteningCaseEditVO;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

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
    private final SimpleUrlShorteningCaseDAOImpl urlShorteningCaseDAO;

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
            .setClientInfos(new ArrayList<>())
            .build();

        this.urlShorteningCaseDAO.applyUrlShorteningCase(urlShorteningCase);

        return urlShorteningCase;
    }

    public void editUrlShorteningCase(String caseId, UrlShorteningCaseEditVO urlShorteningCaseEditVO) {
        UrlShorteningCase originalCase = this.urlShorteningCaseDAO.getUrlShorteningCaseByCaseId(caseId)
            .orElseThrow(() -> new IllegalArgumentException("The case is not existed"));
        UrlShorteningCase result = originalCase.toBuilder()
            .setCaseName(urlShorteningCaseEditVO.getCaseName())
            .setRemark(urlShorteningCaseEditVO.getRemark())
            .setRecipients(urlShorteningCaseEditVO.getRecipients())
            .build();

        this.urlShorteningCaseDAO.saveUrlShorteningCase(result);
    }

    public Collection<UrlShorteningCase> listAllCases() {
        return this.urlShorteningCaseDAO.listAllCases();
    }

    public UrlShorteningCase getUrlShorteningCase(String caseId) {
        return this.urlShorteningCaseDAO.getUrlShorteningCaseByCaseId(caseId)
            .orElseThrow(() -> new IllegalArgumentException("The specified case is not existed"));
    }

    public String retrieveOriginalUrl(String shortenedKey) {
        return this.urlShorteningCaseDAO.getUrlShorteningCaseByShortenedUrlKey(shortenedKey)
            .map(UrlShorteningCase::getOriginalUrl)
            .orElseThrow(() -> new RuntimeException("The shortened url key is not existed"));
    }

    public void recordClientInfo(String shortenedKey, ClientInfoVO clientInfoVO) {
        UrlShorteningCase originalUrlShorteningCase = this.urlShorteningCaseDAO
            .getUrlShorteningCaseByShortenedUrlKey(shortenedKey)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find corresponding URL"));

        UrlShorteningCase result = originalUrlShorteningCase.toBuilder().build();
        result.addUrlShortenCaseDetail(TriggeredClientInfo.builder()
            .setTriggeredDateTime(clientInfoVO.getTriggeredDateTime())
            .setHostname(clientInfoVO.getHostname())
            .setIpAddress(clientInfoVO.getIpAddress())
            .setUserAgent(clientInfoVO.getUserAgent())
            .build());

        this.urlShorteningCaseDAO.saveUrlShorteningCase(result);
    }

    public Collection<TriggeredClientInfo> listUrlShortenCaseTriggeredClientInfos(String caseId) {
        return this.urlShorteningCaseDAO.getUrlShorteningCaseByCaseId(caseId)
            .map(UrlShorteningCase::getClientInfos)
            .orElseThrow(() -> new IllegalArgumentException("The specified case is not existed"));
    }

    protected String generateShortenedUrlKey(UrlShortenCaseApplyVO urlShortenCaseApplyVO) {
        String result = urlShortenCaseApplyVO.getShortenedUrlKey();

        if (result == null) {
            result = this.urlEncoder.encodeUrl(urlShortenCaseApplyVO.getOriginalUrl());
        }

        return result;
    }

    protected void checkShortenedKeyExisted(String shortenedKey) {
        if (this.urlShorteningCaseDAO.isShortenedUrlKeyExisted(shortenedKey)) {
            throw new IllegalArgumentException("The shortened key is not existed");
        }
    }

    protected void checkShortenKeySpecified(String shortenedKey) {
        if (this.urlShorteningCaseDAO.isShortenedUrlKeyExisted(shortenedKey)) {
            throw new IllegalArgumentException("The shortened key has been specified");
        }
    }

    protected void checkCaseIdExisted(String caseId) {
        if (this.urlShorteningCaseDAO.isCaseIdExisted(caseId)) {
            throw new IllegalArgumentException("The case is not existed");
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
}
