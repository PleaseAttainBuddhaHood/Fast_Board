package com.fast_board.service;

import com.fast_board.dao.BoardDao;
import com.fast_board.dao.CommentDao;
import com.fast_board.domain.CommentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService
{
    BoardDao boardDao;
    CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao)
    {
        this.commentDao = commentDao;
        this.boardDao = boardDao;
    }


    @Override
    public int getCount(Integer bno) throws Exception
    {
        return commentDao.count(bno);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer cno, Integer bno, String commenter) throws Exception
    {
        int rowCnt = boardDao.updateCommentCnt(bno, -1);

        rowCnt = commentDao.delete(cno, commenter);

        return rowCnt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(CommentDto commentDto) throws Exception
    {
        boardDao.updateCommentCnt(commentDto.getBno(), 1);

        return commentDao.insert(commentDto);
    }

    @Override
    public List<CommentDto> getList(Integer bno) throws Exception
    {
        return commentDao.selectAll(bno);
    }

    @Override
    public CommentDto read(Integer cno) throws Exception
    {
        return commentDao.select(cno);
    }

    @Override
    public int modify(CommentDto commentDto) throws Exception
    {
        return commentDao.update(commentDto);
    }
}
