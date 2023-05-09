package com.fast_board.controller;

import com.fast_board.dao.UserDao;
import com.fast_board.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequestMapping("/login")
@Controller
public class LoginController
{

    @Autowired UserDao userDao;

    @GetMapping("/login")
    public String loginForm()
    {
        return "loginForm";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        // 1. 세션 종료
        session.invalidate();
        // 2. 홈으로 이동
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String id, String pwd, String toURL, boolean rememberId,
                        HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // 1. 아이디와 패스워드 확인
        if(!loginCheck(id, pwd))
        {
            // 2. 일치 하지 않으면 loginForm으로 이동
            String msg = URLEncoder.encode("아이디 또는 패스워드가 일치하지 않습니다.", StandardCharsets.UTF_8);

            return "redirect:/login/login?msg=" + msg;
        }

        // 3. 아이디와 패스워드가 일치하면
        // 세션 객체 얻어오기
        HttpSession session = request.getSession();
        // 세션 객체에 아이디 저장
        session.setAttribute("id", id);

        if(rememberId)
        { // 쿠키 생성
            Cookie cookie = new Cookie("id", id);
            response.addCookie(cookie);
        }
        else
        { // 쿠키 삭제
            Cookie cookie = new Cookie("id", id);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        // 3-3 홈으로 이동
        toURL = toURL == null || toURL.equals("") ? "/" : toURL;

        return "redirect:" + toURL;
    }

    private boolean loginCheck(String id, String pwd)
    {
        User user = null;

        try
        {
            user = userDao.selectUser(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();

            return false;
        }

        return user != null && user.getPwd().equals(pwd);
    }
}
