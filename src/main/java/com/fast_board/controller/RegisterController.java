package com.fast_board.controller;

import com.fast_board.domain.User;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/register")
@Controller
public class RegisterController
{

    @InitBinder
    public void toDate(WebDataBinder binder)
    {
        // hobby 구분자 타입 변환, 'hobby' 필드에만 적용
        binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));
    }

    @GetMapping("/add")
    public String add()
    {
        return "registerForm";
    }

    @PostMapping("/add")
    public String save(@Valid User user, BindingResult result, Model model) throws Exception
    {
        if(result.hasErrors())
        {
            return "registerForm";
        }

        return "registerInfo";
    }

    private boolean isValid(User user)
    {
        return true;
    }
}
