package com.example.demo.controller;


import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UnknownFormatConversionException;


@Controller
public class Controlador {

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @PostMapping("/submit")
    public String calcular(@RequestParam("inputField") String inputField, Model model) {
        System.out.println(inputField);
        String result= " ";

        try{
            Expression e= new ExpressionBuilder(inputField).build();
            result = String.valueOf((int) e.evaluate());
        }catch (UnknownFormatConversionException | ArithmeticException e){
            result = "Syntax Error";
        }
        model.addAttribute("result", result);
        return "index";

    }





}
