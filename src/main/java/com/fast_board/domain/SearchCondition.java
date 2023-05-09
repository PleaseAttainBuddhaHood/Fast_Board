package com.fast_board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class SearchCondition
{
    private Integer page = 1;
    private Integer pageSize = 10;
    private Integer offset;
    private String keyword = "";
    private String option = "";


    public SearchCondition(){}

    public SearchCondition(Integer page, Integer pageSize, String keyword, String option)
    {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
    }


    public String getQueryString(Integer page)
    {
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .build().toString();
    }

    public String getQueryString()
    {
        return getQueryString(page);
    }

    public Integer getOffset()
    {
        return (page - 1) * pageSize;
    }

}
