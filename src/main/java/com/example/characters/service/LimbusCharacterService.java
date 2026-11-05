package com.example.characters.service;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.characters.entity.LimbusCharacter;
import com.example.characters.repository.LimbusCharacterRepository;

@Service
public class LimbusCharacterService {

    private final LimbusCharacterRepository repository;

    public LimbusCharacterService(LimbusCharacterRepository repository) {
        this.repository = repository;
    }

    public List<LimbusCharacter> getAll(String name, String faction) {
        if (name != null && !name.isBlank()) {
            return repository.findByNameContainingIgnoreCase(name);
        }
        if (faction != null && !faction.isBlank()) {
            return repository.findByFactionIgnoreCase(faction);
        }
        return repository.findAll();
    }

    public LimbusCharacter getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Character " + id + " not found"));
    }

    public LimbusCharacter create(LimbusCharacter character) {
        character.setCharacterId(null);
        return repository.save(character);
    }

    public LimbusCharacter update(Long id, LimbusCharacter updated) {
        LimbusCharacter existing = getById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setSpecialty(updated.getSpecialty());
        existing.setFaction(updated.getFaction());
        existing.setImageUrl(updated.getImageUrl());
        return repository.save(existing);
    }

    public void delete(Long id) {
        LimbusCharacter existing = getById(id);
        repository.delete(existing);
    }

    public List<LimbusCharacter> search(String name, String faction) {
    boolean hasName = name != null && !name.isBlank();
    boolean hasFaction = faction != null && !faction.isBlank();

    return repository.findAll().stream()
            .filter(c -> !hasName || (c.getName() != null
                    && c.getName().toLowerCase().contains(name.trim().toLowerCase())))
            .filter(c -> !hasFaction || (c.getFaction() != null
                    && c.getFaction().equalsIgnoreCase(faction.trim())))
            .toList();
}

}