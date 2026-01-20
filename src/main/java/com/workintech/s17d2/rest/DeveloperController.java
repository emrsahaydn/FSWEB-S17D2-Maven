package com.workintech.s17d2.rest;

import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.Experience;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeveloperController {
    public Map<Integer, Developer> developers ;
    private Taxable taxable;
    @PostConstruct
    public void init(){
        developers=new HashMap<>();
        System.out.println("Developers başarıyla oluşturuldu.");
    }
    @Autowired
    public DeveloperController(Taxable taxable){
        this.taxable=taxable;
    }
    @GetMapping("/developers")
    public List<Developer> getAllDevelopers(){
        return new ArrayList<>(developers.values());
    }
    @GetMapping("/developers/{id}")
    public Developer getById(@PathVariable("id") int id){
        if(developers.containsKey(id)){
            return developers.get(id);
        }
        return null;
    }
    @PostMapping("/developers")
    @ResponseStatus(HttpStatus.CREATED)
    public Developer save(@RequestBody Developer developer) {
        developer.setSalary(calculateTax(developer));
        developers.put(developer.getId(), developer);
        return developer;
    }
    @PutMapping("/developers/{id}")
    public Developer update(@PathVariable("id") Integer id, @RequestBody Developer developer) {
        if (!developers.containsKey(id)) {
            return null;
        }
        developer.setId(id);
        developer.setSalary(calculateTax(developer));
        developers.put(id, developer);
        return developer;
    }
    @DeleteMapping("/developers/{id}")
    public Developer delete(@PathVariable("id") Integer id){
        if(developers.containsKey(id)){
            return developers.remove(id);
        }
        return null;
    }

    private double calculateTax(Developer developer) {
        double salary = developer.getSalary();
        if (developer.getExperience() == Experience.JUNIOR) {
            return salary - (salary * taxable.getSimpleTaxRate() / 100);
        } else if (developer.getExperience() == Experience.MID) {
            return salary - (salary * taxable.getMiddleTaxRate() / 100);
        } else if (developer.getExperience() == Experience.SENIOR) {
            return salary - (salary * taxable.getUpperTaxRate() / 100);
        }
        return salary;
    }


 }
