package dev.srinivas.ProductService.service;

import dev.srinivas.ProductService.dto.GenericProductDto;
import dev.srinivas.ProductService.repository.OpenSearchProductElasticsearchRepository;
import dev.srinivas.ProductService.model.Product;
import dev.srinivas.ProductService.model.SortParam;
import dev.srinivas.ProductService.repository.ProductJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementation of the SearchService using Pagination & Sorting.
 */
@Service
public class SearchService {

    private ProductJpaRepository productJpaRepository;

    private OpenSearchProductElasticsearchRepository openSearchProductElasticsearchRepository;

    public SearchService(ProductJpaRepository productJpaRepository, OpenSearchProductElasticsearchRepository openSearchProductElasticsearchRepository){
        this.productJpaRepository = productJpaRepository;
        this.openSearchProductElasticsearchRepository = openSearchProductElasticsearchRepository;
    }

    public List<GenericProductDto> searchProducts(String query, int pageNumber, int pageSize, List<SortParam> sortParams){


//        Sort sort= Sort.by("title").ascending()
//                .and(Sort.by("rating").descending())
//                .and(Sort.by("price").descending());
        Sort sort = null;

        if(sortParams.get(0).getSortType().equals("ASC")){
            sort = Sort.by(sortParams.get(0).getSortParamName()).ascending();
        }
        else{
            sort = Sort.by(sortParams.get(0).getSortParamName()).descending();
        }

        for(int i = 1; i < sortParams.size(); i++){
            if(sortParams.get(i).getSortType().equals("ASC")){
                sort.and(Sort.by(sortParams.get(i).getSortParamName()).ascending());
            }
            else{
                sort.and(Sort.by(sortParams.get(i).getSortParamName()).descending());
            }
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sort);
        List<Product> products = openSearchProductElasticsearchRepository.findAllByTitleContainingIgnoreCase(query, (Pageable) pageRequest);
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for(Product product: products){
            genericProductDtos.add(product.from(product));
        }
        return genericProductDtos;
    }

}
