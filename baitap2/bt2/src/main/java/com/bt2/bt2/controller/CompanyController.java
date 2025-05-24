package com.bt2.bt2.controller;

import com.bt2.bt2.model.Company;
import com.bt2.bt2.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public String listCompanies(Model model) {
        model.addAttribute("companies", companyRepository.findAll());
        return "company_list";
    }

    @GetMapping("/new")
    public String showCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "company_form";
    }

    @PostMapping
    public String saveCompany(@ModelAttribute Company company) {
        companyRepository.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/edit/{id}")
    public String editCompany(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyRepository.findById(id).orElseThrow());
        return "company_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        companyRepository.deleteById(id);
        return "redirect:/companies";
    }
}
