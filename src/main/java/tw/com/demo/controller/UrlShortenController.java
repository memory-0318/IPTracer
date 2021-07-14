package tw.com.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.demo.dto.ResponseDTO;
import tw.com.demo.dto.UrlShortenCaseApplyDTO;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<String> shortenUrl(@Valid @RequestBody UrlShortenCaseApplyDTO applyDTO) {
        return ResponseDTO.ok("123");
    }
}
