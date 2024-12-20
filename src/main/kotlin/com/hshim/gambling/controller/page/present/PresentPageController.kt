package com.hshim.gambling.controller.page.present

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/page/present")
class PresentPageController {
    @GetMapping("/donate")
    fun donate(@RequestParam(required = false) userId: String?) = "page/present/donate"
}