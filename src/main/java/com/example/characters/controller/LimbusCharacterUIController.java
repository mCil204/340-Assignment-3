package com.example.characters.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.characters.entity.LimbusCharacter;
import com.example.characters.service.LimbusCharacterService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class LimbusCharacterUIController {

    private final LimbusCharacterService service;

    public LimbusCharacterUIController(LimbusCharacterService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String gallery(@RequestParam(required = false) String name,
                          @RequestParam(required = false) String faction,
                          Model model) {
        model.addAttribute("characters", service.search(name, faction));
        model.addAttribute("name", name);
        model.addAttribute("faction", faction);
        return "index";
    }

    @GetMapping("/characters/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("character", service.getById(id));
        return "details";
    }

    @GetMapping("/characters/new")
    public String newForm(Model model) {
        model.addAttribute("character", new LimbusCharacter());
        model.addAttribute("formAction", "/characters");
        model.addAttribute("formTitle", "Add a Character");
        return "character-form";
    }

    @PostMapping("/characters")
    public String create(@Valid @ModelAttribute("character") LimbusCharacter character,
                         BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("formAction", "/characters");
            model.addAttribute("formTitle", "Add a Character");
            return "character-form";
        }
        LimbusCharacter saved = service.create(character);
        ra.addFlashAttribute("message", saved.getName() + " was added");
        return "redirect:/";
    }

    @GetMapping("/characters/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        LimbusCharacter existing = service.getById(id);
        model.addAttribute("character", existing);
        model.addAttribute("formAction", "/characters/" + id);
        model.addAttribute("formTitle", "Edit " + existing.getName());
        return "character-form";
    }

    @PostMapping("/characters/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("character") LimbusCharacter character,
                         BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("formAction", "/characters/" + id);
            model.addAttribute("formTitle", "Edit Character");
            return "character-form";
        }
        LimbusCharacter saved = service.update(id, character);
        ra.addFlashAttribute("message", saved.getName() + " was updated");
        return "redirect:/characters/" + id;
    }

    @PostMapping("/characters/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        String name = service.getById(id).getName();
        service.delete(id);
        ra.addFlashAttribute("message", name + " was removed");
        return "redirect:/";
    }

    // Applies only to this controller, so the REST API still returns its normal 404
    @ExceptionHandler(ResponseStatusException.class)
    public String notFound(ResponseStatusException e, Model model, HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("message", e.getReason());
        return "error";
    }
}