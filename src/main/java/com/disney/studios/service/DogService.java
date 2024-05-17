package com.disney.studios.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.disney.studios.constants.ApplicationConstants;
import com.disney.studios.dto.Dog;
import com.disney.studios.exception.DisneyException;
import com.disney.studios.repository.DogRepository;

@Component
public class DogService {
	@Autowired 
	DogRepository dogRepository;
	
	public Map<String, List<Dog>> getAllDogs(boolean sortAsc)
	{
		List<Dog> dogs = dogRepository.findAll();
		Map<String, List<Dog>> dogMap = null;
		Comparator<Dog> comparatorToBeUsed = findComparatorByFavCount(sortAsc);
		dogMap = dogs.stream().sorted(Comparator.comparing(Dog::getDogBreed))
				.sorted(comparatorToBeUsed).collect(Collectors.groupingBy(Dog::getDogBreed));
		return dogMap;
	}
	
	private Comparator<Dog> findComparatorByFavCount(boolean sortAsc) {
		return (sortAsc)?Comparator.comparing(Dog::getFavCount):Comparator.comparing(Dog::getFavCount).reversed();
	}

	public List<Dog> findByDogBreed(String breedName) throws DisneyException {
		List<Dog> dogs = null;
			dogs = dogRepository.findByDogBreed(breedName);
			if (dogs.isEmpty()) {
				throw new DisneyException(HttpStatus.BAD_REQUEST, "Record Not Found", breedName);
			}
			dogs = dogs.stream().sorted(Comparator.comparing(Dog::getFavCount).reversed()).collect(Collectors.toList());
		return dogs;
	}

	public String findById(int id) throws DisneyException {
		Optional<Dog> dog = dogRepository.findById(id);
		String details = null;
		if (!dog.isPresent()) {
			throw new DisneyException(HttpStatus.BAD_REQUEST, "Record Not Found", String.valueOf(id));
		}
		details = dog.get().getDetails();
		return details;
	}

	public String countDogFav(int dogId, String vote) throws DisneyException {
		Optional<Dog> dog = dogRepository.findById(dogId);
		if (!dog.isPresent()) {
			throw new DisneyException(HttpStatus.BAD_REQUEST, "Record Not Found", String.valueOf(dogId));
		}
		int count = dog.get().getFavCount();
		validateAddDog(dog, count, vote);
		countLikeDislike(dog, count, vote);
		Dog savedDog = dogRepository.save(dog.get());
		contructResponseForAdding(savedDog);
		return contructResponseForAdding(savedDog);
	}
	
	private String contructResponseForAdding(Dog dog) {
		return "The dog with id: " + dog.getId() + " has " + dog.getFavCount() + " likes";
	}


	private void countLikeDislike(Optional<Dog> dog, int count, String vote) {
		if (vote.equalsIgnoreCase(ApplicationConstants.UP)) {
			count++;
			dog.get().setFavCount(count);
		}
		if (vote.equalsIgnoreCase(ApplicationConstants.DOWN)) {
			count--;
			dog.get().setFavCount(count--);
		}
	}

	private void validateAddDog(Optional<Dog> dog, int count, String vote) throws DisneyException {
		if (!vote.equalsIgnoreCase(ApplicationConstants.DOWN) && !vote.equalsIgnoreCase(ApplicationConstants.UP)) {
			throw new DisneyException(HttpStatus.BAD_REQUEST, ApplicationConstants.WRONG_SYNTAX);
		}
		if (vote.equalsIgnoreCase(ApplicationConstants.DOWN) && count <= 0) {
			throw new DisneyException(HttpStatus.BAD_REQUEST, ApplicationConstants.DISLIKE_ERROR);
		}
	}

	public Map<String, List<Dog>> getAllDogsSortByImg(String imgSortOrder) {
		List<Dog> dogs = dogRepository.findAll();
		Map<String, List<Dog>> dogMap = null;
		Comparator<Dog> comparatorToBeUsed = Comparator.comparing(Dog::getDogBreed);
		if (imgSortOrder != null) {
			comparatorToBeUsed = findComparatorImageOrder(imgSortOrder);
			dogMap = dogs.stream().sorted(Comparator.comparing(Dog::getDogBreed)).sorted(comparatorToBeUsed)
					.collect(Collectors.groupingBy(Dog::getDogBreed));
		} else {
			dogMap = dogs.stream().sorted(Comparator.comparing(Dog::getDogBreed))
					.collect(Collectors.groupingBy(Dog::getDogBreed));
		}
		return dogMap;
	}

	private Comparator<Dog> findComparatorImageOrder(String imgSortOrder) {
		return (imgSortOrder.equalsIgnoreCase("asc"))?Comparator.comparing(Dog::getImgURL):Comparator.comparing(Dog::getImgURL).reversed();
	}


}
