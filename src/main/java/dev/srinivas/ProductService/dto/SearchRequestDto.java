package dev.srinivas.ProductService.dto;


import dev.srinivas.ProductService.model.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object for search requests.
 */
@Getter
@Setter
public class SearchRequestDto {

    /**
     * The search query.
     */
    private String query;

    /**
     * The page number for pagination.
     */
    private int pageNumber;

    /**
     * The number of items per page for pagination.
     */
    private int itemsPerPage;

    /**
     * The list of sort parameters.
     */
    private List<SortParam> sortParams;
}
