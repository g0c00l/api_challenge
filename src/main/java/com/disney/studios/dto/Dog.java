package com.disney.studios.dto;

import org.springframework.data.annotation.Version;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Component
public class Dog {
	public Dog(String dogBreed, String imgURL, String details) {
		super();
		this.dogBreed = dogBreed;
		this.imgURL = imgURL;
		this.details = details;
	}

	public Dog() {
		super();
	}

	@Id
	@GeneratedValue(generator = "sequence_id")
	public int id;
	public String dogBreed;
	public String imgURL;
	public int favCount;
	public String details;
	@Version
	public int version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFavCount() {
		return favCount;
	}

	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDogBreed() {
		return dogBreed;
	}

	public void setDogBreed(String dogBreed) {
		this.dogBreed = dogBreed;
	}
}
