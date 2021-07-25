package tw.com.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tw.com.demo.UrlShortener;
import tw.com.demo.model.entity.UrlShorteningCase;
import tw.com.demo.model.vo.UrlShortenCaseApplyVO;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/13
 */
@RequiredArgsConstructor
@Service
public class UrlShortenService {
    private final UrlShortener urlShortener;

    public UrlShorteningCase applyUrlShorteningCase(UrlShortenCaseApplyVO urlShortenCaseApplyVO) {
        return this.urlShortener.applyUrlShortenCase(urlShortenCaseApplyVO);
    }
}
