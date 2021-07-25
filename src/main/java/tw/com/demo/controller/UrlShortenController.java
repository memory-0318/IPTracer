package tw.com.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.demo.dto.ResponseDTO;
import tw.com.demo.dto.UrlShortenCaseApplyDTO;
import tw.com.demo.mapper.UrlShortenCaseApplyMapper;
import tw.com.demo.model.entity.UrlShorteningCase;
import tw.com.demo.service.UrlShortenService;

import javax.validation.Valid;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2021/7/13
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = { "/shorten" })
public class UrlShortenController {
    private final UrlShortenService urlShortenService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<String> shortenUrl(@Valid @RequestBody UrlShortenCaseApplyDTO applyDTO) {
        UrlShorteningCase urlShorteningCase = this.urlShortenService
            .shortenUrl(UrlShortenCaseApplyMapper.INSTANCE.toVO(applyDTO));
        return ResponseDTO.ok(urlShorteningCase.getShortenedUrl());
    }
}
