package com.hannahj.springBoard.paging;

import org.springframework.data.domain.Page;
import lombok.Getter;
import lombok.Setter;


/**
 * @author kopo21
 * @param pageNumber: current page number, gets from Page parameter
 * @param totalPages: length(Pages), gets from Page parameter
 * @param startBlockPage: page selector starts with this number
 * @param endBlockPage: page selector ends with this number
 */
@Setter
public class Criteria {


    public final int pageBlock = 10;

    /** 현재 페이지 번호 */
    private int pageNumber;

    private int totalPages;
    
    @Getter
    private int startBlockPage;
    
    @Getter
    private int endBlockPage;

    public Criteria(Page<?> page) {
        this.pageNumber = page.getPageable().getPageNumber();
        this.totalPages=page.getTotalPages();
        this.startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; 
        this.endBlockPage = (int) ((totalPages < startBlockPage+pageBlock-1)?
                totalPages : startBlockPage+pageBlock-1); 
    }

    

}