package com.example.characters.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.characters.entity.LimbusCharacter;
import com.example.characters.service.LimbusCharacterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/characters")
public class LimbusCharacterController {

    private final LimbusCharacterService service;

    public LimbusCharacterController(LimbusCharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<LimbusCharacter> getAll(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String faction) {
        return service.getAll(name, faction);
    }

    @GetMapping("/{id}")
    public LimbusCharacter getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LimbusCharacter create(@Valid @RequestBody LimbusCharacter character) {
        return service.create(character);
    }

    @PutMapping("/{id}")
    public LimbusCharacter update(@PathVariable Long id, @Valid @RequestBody LimbusCharacter character) {
        return service.update(id, character);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}