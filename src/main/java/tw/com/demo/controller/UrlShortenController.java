package tw.com.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tw.com.demo.mapper.TriggeredClientInfoMapper;
import tw.com.demo.mapper.UrlShortenCaseMapper;
import tw.com.demo.mapper.UrlShorteningCaseApplyMapper;
import tw.com.demo.mapper.UrlShorteningCaseEditMapper;
import tw.com.demo.model.dto.*;
import tw.com.demo.model.entity.UrlShorteningCase;
import tw.com.demo.service.UrlShortenService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<String> applyUrlShorteningCase(@Valid @RequestBody UrlShorteningCaseApplyDTO applyDTO) {
        UrlShorteningCase urlShorteningCase = this.urlShortenService
            .applyUrlShorteningCase(UrlShorteningCaseApplyMapper.INSTANCE.toVO(applyDTO));
        return ResponseDTO.ok(urlShorteningCase.getShortenedUrl());
    }

    @GetMapping(value = { "/case" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<Collection<UrlShorteningCaseDTO>> listAll(PageRequestDTO pageRequestDTO) {
        return ResponseDTO.ok(
            this.urlShortenService.listAllCases()
                .stream()
                .map(UrlShortenCaseMapper.INSTANCE::toDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping(value = { "/case/{caseId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<UrlShorteningCaseDTO> getUrlShorteningCase(@PathVariable("caseId") String caseId) {
        UrlShorteningCase urlShorteningCase = this.urlShortenService.getUrlShorteningCase(caseId);
        return ResponseDTO.ok(UrlShortenCaseMapper.INSTANCE.toDTO(urlShorteningCase));
    }

    @GetMapping(value = { "/case/{caseId}/triggered_history" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<Collection<TriggeredClientInfoDTO>> listUrlShortenCaseTriggeredClientInfos(
        @PathVariable("caseId") String caseId) {
        return ResponseDTO.ok(
            this.urlShortenService.listUrlShortenCaseTriggeredClientInfos(caseId)
                .stream()
                .map(TriggeredClientInfoMapper.INSTANCE::toDTO)
                .collect(Collectors.toList())
        );
    }

    @PutMapping(value = { "/case/{caseId}" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<Void> editUrlShorteningCase(
        @PathVariable("caseId") String caseId,
        @Valid @RequestBody UrlShorteningCaseEditDTO editDTO) {
        this.urlShortenService.editUrlShorteningCase(caseId, UrlShorteningCaseEditMapper.INSTANCE.toVO(editDTO));
        return ResponseDTO.ok(null);
    }
}
