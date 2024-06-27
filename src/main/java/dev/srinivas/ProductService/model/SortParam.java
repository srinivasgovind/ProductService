package dev.srinivas.ProductService.model;


import lombok.Getter;
import lombok.Setter;
/**
 * Entity representing an SortParam.
 */
@Getter
@Setter
public class SortParam {

    private String sortParamName;
    private String sortType; //Asc or Descending


}
