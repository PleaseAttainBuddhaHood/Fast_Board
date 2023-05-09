package com.fast_board.controller;

import com.fast_board.domain.CommentDto;
import com.fast_board.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController
{
    CommentService commentService;

    public CommentController(CommentService commentService)
    {
        this.commentService = commentService;
    }


    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> list(Integer bno)
    {
        List<CommentDto> list = null;

        try
        {
            list = commentService.getList(bno);

            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/comments/{cno}")
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session)
    {
        String commenter = "asdf";

        try
        {
            int rowCnt = commentService.remove(cno, bno, commenter);

            if(rowCnt != 1)
                throw new Exception("Delete Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping("/comments")
    public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno, HttpSession session)
    {
        String commenter = "asdf";

        dto.setCommenter(commenter);
        dto.setBno(bno);

        try
        {
            if(commentService.write(dto) != 1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto)
    {
        dto.setCno(cno);

        try
        {
            if(commentService.modify(dto) != 1)
                throw new Exception("Mod failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }
}
