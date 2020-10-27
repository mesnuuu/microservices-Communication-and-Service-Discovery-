package com.mesnuuu.moviecatalogservice.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mesnuuu.moviecatalogservice.models.CatalogItem;

@RestController
public class MovieCatalogeResource {

	@GetMapping("/catalog/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		return Collections.singletonList(new CatalogItem("Avatar", "Test Desc", 4));
	}

}