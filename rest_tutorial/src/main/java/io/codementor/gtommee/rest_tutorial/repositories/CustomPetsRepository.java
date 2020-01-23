package io.codementor.gtommee.rest_tutorial.repositories;

import java.util.List;

import io.codementor.gtommee.rest_tutorial.models.Pets;

public interface CustomPetsRepository {
	 Pets getPetsByNome(String nome);
	 
	 List<Pets> groupPets();
}
