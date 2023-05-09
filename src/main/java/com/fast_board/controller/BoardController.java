package com.fast_board.controller;

import com.fast_board.domain.BoardDto;
import com.fast_board.domain.PageHandler;
import com.fast_board.domain.SearchCondition;
import com.fast_board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RequestMapping("/board")
@Controller
public class BoardController
{
    @Autowired
    BoardService boardService;

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, HttpSession session, Model m, RedirectAttributes rattr)
    {
        String writer = (String) session.getAttribute("id");

        boardDto.setWriter(writer);

        try
        {
            int rowCnt = boardService.modify(boardDto);

            if(rowCnt != 1)
            {
                throw new Exception("Modify failed");
            }

            rattr.addFlashAttribute("msg", "MOD_OK");

            return "redirect:/board/list";
        }
        catch (Exception e)
        {
            e.printStackTrace();

            m.addAttribute(boardDto);
            m.addAttribute("msg", "MOD_ERR");

            return "board";
        }
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, HttpSession session, Model m, RedirectAttributes rattr)
    {
        String writer = (String) session.getAttribute("id");

        boardDto.setWriter(writer);

        try
        {
            if(boardService.write(boardDto) != 1)
            {
                throw new Exception("Write failed");
            }

            rattr.addFlashAttribute("msg", "WRT_OK");

            return "redirect:/board/list";
        }
        catch (Exception e)
        {
            e.printStackTrace();

            m.addAttribute(boardDto);
            m.addAttribute("mode", "new");
            m.addAttribute("msg", "WRT_ERR");

            return "board";
        }
    }

    @GetMapping("/write")
    public String write(Model m)
    {
        m.addAttribute("mode", "new");

        return "board";
    }


    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m,
                         HttpSession session, RedirectAttributes rattr)
    {
        String writer = (String) session.getAttribute("id");

        try
        {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            int rowCnt = boardService.remove(bno, writer);

            if(rowCnt != 1)
            {
                throw new Exception("Board Remove Error");
            }

            rattr.addFlashAttribute("msg", "Delete_Success!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "Delete_Error");
        }

        return "redirect:/board/list";
    }


    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m)
    {
        try
        {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute(boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "board";
    }


    @GetMapping("/list")
    public String list(SearchCondition sc, Model m, HttpServletRequest request)
    {
        if(!loginCheck(request))
        {
            return "redirect:/login/login?toURL=" + request.getRequestURL(); // 로그인을 하지 않았으면 로그인 화면으로 이동
        }

        try
        {
            int totalCnt = boardService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.getSearchResultPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();

            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        }
        catch (Exception e)
        {
            e.printStackTrace();

            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);

        }

        return "boardList";
    }


    private boolean loginCheck(HttpServletRequest request)
    {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession(false);

        // 2. 세션에 아이디가 있는지 확인 후 있으면 true 반환
        return session != null && session.getAttribute("id") != null;
    }

}
