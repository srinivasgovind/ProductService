package dev.srinivas.ProductService.controller;


import dev.srinivas.ProductService.dto.GenericProductDto;
import dev.srinivas.ProductService.dto.SearchRequestDto;
import dev.srinivas.ProductService.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @PostMapping
    public Page<GenericProductDto> searchProducts(@RequestBody SearchRequestDto requestDto){
        List<GenericProductDto> genericProductDtos =searchService.searchProducts(requestDto.getQuery(), requestDto.getPageNumber(), requestDto.getItemsPerPage()
        , requestDto.getSortParams());

        Page<GenericProductDto> genericProductDtoPage = new PageImpl<>(genericProductDtos);

        return genericProductDtoPage;
    }
}
