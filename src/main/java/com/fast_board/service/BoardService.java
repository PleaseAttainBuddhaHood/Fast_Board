package com.fast_board.service;

import com.fast_board.domain.BoardDto;
import com.fast_board.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardService
{
    // 생성
    int write(BoardDto boardDto) throws Exception;


    // 조회
    int getCount() throws Exception;
    List<BoardDto> getList() throws Exception;
    BoardDto read(Integer bno) throws Exception;
    List<BoardDto> getPage(Map map) throws Exception;
    int getSearchResultCnt(SearchCondition sc) throws Exception;
    List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception;


    // 수정
    int modify(BoardDto boardDto) throws Exception;


    // 삭제
    int remove(Integer bno, String writer) throws Exception;
}
