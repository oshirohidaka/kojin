package com.example.framework.controller;

import com.example.framework.entity.CategoriesRecord;
import com.example.framework.entity.ProductRecord;
import com.example.framework.form.InsertForm;
import com.example.framework.form.LoginForm;
import com.example.framework.service.PgProductService;
import com.example.framework.service.PgUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    PgUsersService pgUsersService;
    @Autowired
    PgProductService pgProductService;

    @GetMapping("/login")
    public String index(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "index";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        var user = pgUsersService.findById(loginForm.getLoginId());
        if (user == null) {
            model.addAttribute("error", "IDまたはパスワードが不正です");
            return "index";
        }
        if (loginForm.getPassword().equals(user.password())) {
            return "redirect:/menu";
        }
        model.addAttribute("error", "IDまたはパスワードが不正です");
        return "index";
    }

    //    @GetMapping("/menu")
//    public String menu(){
//        return "menu";
//    }
    @GetMapping({"/menu","/menu/{order}&{keyword}"})//←「URLとメソッドを紐づける」役割
    public String menu(@RequestParam(name = "keyword", defaultValue = "") String keyword, Model model) {
        List<ProductRecord>list;

        if(keyword == null){
            list = pgProductService.findAll();
        }else {
            list = pgProductService.findByKeyword(keyword);
        }

        model.addAttribute("menu",list);
        var count = list.size();
        model.addAttribute("count", count);
        return "menu";


    }

    @GetMapping("/insert")
    public String indexs(@ModelAttribute("insertForms") InsertForm insertForm, Model model) {
        List<CategoriesRecord>list=pgProductService.categories();

        model.addAttribute("categories",list);
        var count = list.size();
        model.addAttribute("count", count);
        return "insert";
    }

    @PostMapping("insert")
    public String insert(@Validated @ModelAttribute("insertForms") InsertForm insertForm, BindingResult bindingResult,Model model) {
        List<CategoriesRecord> list = pgProductService.categories();
        model.addAttribute("categories", list);
        var count = list.size();
        model.addAttribute("count", count);

        if (bindingResult.hasErrors()) {
            return "insert";
        }
        var Pid = pgProductService.findByPid(insertForm.getProduct_id());
        if (Pid != null) {
            model.addAttribute("error", "商品コードが重複しています");
            return "insert";
        }
        pgProductService.insert(insertForm.getProduct_id(), insertForm.getName(), insertForm.getPrice(), insertForm.getCategory_id());
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success(@ModelAttribute("insertForms") InsertForm insertForm) {
        return "success";
    }
}




