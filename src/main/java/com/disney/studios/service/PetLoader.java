package com.disney.studios.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.disney.studios.constants.ApplicationConstants;
import com.disney.studios.dto.Dog;
import com.disney.studios.repository.DogRepository;

/**
 * Loads stored objects from the file system and builds up
 * the appropriate objects to add to the data source.
 *
 * Created by fredjean on 9/21/15.
 */
@Component
public class PetLoader implements InitializingBean {
    // Resources to the different files we need to load.
    @Value("classpath:data/labrador.txt")
    private Resource labradors;

    @Value("classpath:data/pug.txt")
    private Resource pugs;

    @Value("classpath:data/retriever.txt")
    private Resource retrievers;

    @Value("classpath:data/yorkie.txt")
    private Resource yorkies;

    @Autowired
    DataSource dataSource;

    
    @Autowired
    DogRepository dogRepository;

    /**
     * Load the different breeds into the data source after
     * the application is ready.
     *
     * @throws Exception In case something goes wrong while we load the breeds.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        loadBreed(ApplicationConstants.LABORDOR, labradors);
        loadBreed(ApplicationConstants.PUG, pugs);
        loadBreed(ApplicationConstants.RETREIVER, retrievers);
        loadBreed(ApplicationConstants.YORKIE, yorkies);
    }

    /**
     * Reads the list of dogs in a category and (eventually) add
     * them to the data source.
     * @param breed The breed that we are loading.
     * @param source The file holding the breeds.
     * @throws IOException In case things go horribly, horribly wrong.
     */
    private void loadBreed(String breed, Resource source) throws IOException {
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(source.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
              if(breed.equalsIgnoreCase(ApplicationConstants.LABORDOR))
              {
            	  insertDog(ApplicationConstants.LABORDOR,line);
              }
              else if(breed.equalsIgnoreCase(ApplicationConstants.PUG))
              {
            	  insertDog(ApplicationConstants.PUG,line);
              }
              else if(breed.equalsIgnoreCase(ApplicationConstants.RETREIVER))
              {
            	  insertDog(ApplicationConstants.RETREIVER,line);
              }
              else if(breed.equalsIgnoreCase(ApplicationConstants.YORKIE))
              {
            	  insertDog(ApplicationConstants.YORKIE,line);
              }
            }
        }
    }
  //  String dogBreed, String imgURL, String favCount, String details
	private void insertDog(String dogBreed, String imgURL)  {
	
		Dog dog = new Dog(dogBreed,imgURL,constructDetails(dogBreed,imgURL));
		
		dogRepository.save(dog);
	}

	private String constructDetails(String dogBreed, String imgURL) {
		// TODO Auto-generated method stub
		return "Dog Breed: "+dogBreed+" imageUrl: "+ imgURL;
	}
}
