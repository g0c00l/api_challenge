package com.disney.studios.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.disney.studios.constants.ApplicationConstants;
import com.disney.studios.dto.Dog;
import com.disney.studios.exception.DisneyException;
import com.disney.studios.repository.DogRepository;
import com.disney.studios.service.DogService;

@RestController
public class DogController {

	@Autowired
	DogRepository dogRepository;

	@Autowired
	DogService dogService;

	@GetMapping("/dogs")
	public ResponseEntity<Map<String, List<Dog>>> getAllDogs(@RequestParam(required = false) boolean sortAsc) {
		Map<String, List<Dog>> dogMap = dogService.getAllDogs(sortAsc);
		return new ResponseEntity<Map<String, List<Dog>>>(dogMap, HttpStatus.OK);
	}
	@GetMapping("/dogsImageSorted")
	public ResponseEntity<Map<String, List<Dog>>> getAllDogsSortByImg(@RequestParam(required = false) String imgSortOrder) {
		Map<String, List<Dog>> dogMap = dogService.getAllDogsSortByImg(imgSortOrder);
		return new ResponseEntity<Map<String, List<Dog>>>(dogMap, HttpStatus.OK);
	}


	@GetMapping("/dogs/{id}")
	public ResponseEntity<String> getDetails(@PathVariable int id) throws DisneyException {
		String details = dogService.findById(id);
		return new ResponseEntity<String>(details, HttpStatus.OK);
	}

	@PutMapping("/dog")
	public ResponseEntity<String> countDogFav(@RequestParam int dogId, @RequestParam String vote)
			throws DisneyException {
		String dogDetailMsg = dogService.countDogFav(dogId, vote);
		return new ResponseEntity<String>(dogDetailMsg, HttpStatus.OK);
	}

	@GetMapping("/dogsByBreedName/{breedName}")
	public ResponseEntity<List<Dog>> getDogsByBreed(@PathVariable String breedName) throws DisneyException {
		List<Dog> dogs = dogService.findByDogBreed(breedName);
		return new ResponseEntity<List<Dog>>(dogs, HttpStatus.OK);

	}

}
