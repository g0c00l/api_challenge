package com.disney.studios.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.studios.dto.Dog;


@Repository
public interface DogRepository extends JpaRepository<Dog, Integer> {

	List<Dog> findByDogBreed(String breedName);

}
