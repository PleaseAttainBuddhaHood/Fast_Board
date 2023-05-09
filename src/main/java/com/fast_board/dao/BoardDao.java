package com.fast_board.dao;

import com.fast_board.domain.BoardDto;
import com.fast_board.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardDao
{

    // 생성
    int insert(BoardDto dto) throws Exception;


    // 조회
    BoardDto select(int bno) throws Exception;
    List<BoardDto> selectAll() throws Exception;
    List<BoardDto> selectPage(Map map) throws Exception;
    int count() throws Exception;
    List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;
    int searchResultCnt(SearchCondition sc) throws Exception;


    // 수정
    int update(BoardDto dto) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;


    // 삭제
    int delete(Integer bno, String writer) throws Exception;
    int deleteAll() throws Exception;

    int updateCommentCnt(Integer bno, int cnt);

}
