package com.backpacker.homework.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingOption {

    private int pageSize;

    private int pageNumber;

    public PagingOption(){
        this.pageSize = 20;
        this.pageNumber = 1;
    }

    public PagingOption(int pageSize,
                        int pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }
}
