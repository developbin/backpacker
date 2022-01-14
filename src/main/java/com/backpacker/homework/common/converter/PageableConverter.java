package com.backpacker.homework.common.converter;

import com.backpacker.homework.common.PagingOption;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableConverter {

    private PageableConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Pageable convert(PagingOption pagingOption){
        return PageRequest.of(pagingOption.getPageNumber() - 1, pagingOption.getPageSize());
    }

}
