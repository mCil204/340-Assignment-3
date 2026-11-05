package com.example.characters.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.characters.entity.LimbusCharacter;

public interface LimbusCharacterRepository extends JpaRepository<LimbusCharacter, Long> {

    List<LimbusCharacter> findByFactionIgnoreCase(String faction);
    List<LimbusCharacter> findByNameContainingIgnoreCase(String name);
}
