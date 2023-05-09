package com.fast_board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageHandler
{
    private SearchCondition sc;
    private int totalCnt;
    private int naviSize = 10;
    private int totalPage;
    private int beginPage; // 내비게이션의 첫 번째 페이지
    private int endPage;
    private boolean showPrev;
    private boolean showNext;



    public PageHandler(int totalCnt, SearchCondition sc)
    {
        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc);
    }

    public void doPaging(int totalCnt, SearchCondition sc)
    {
        this.totalCnt = totalCnt;

        totalPage = (int) Math.ceil(totalCnt / (double)sc.getPageSize());
        beginPage = (sc.getPage() - 1) / naviSize * naviSize + 1;
        endPage = Math.min(beginPage + naviSize - 1, totalPage);

        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }

    void print()
    {
        System.out.println("page = " + sc.getPage());

        System.out.print(showPrev ? "[이전] " : "");

        for (int i = beginPage; i <= endPage; i++)
        {
            System.out.print(i + " ");
        }

        System.out.println(showNext ? "[다음]" : "");
    }
}
