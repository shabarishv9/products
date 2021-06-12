/**
 * 
 */
package com.techhungry.products.controllers;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techhungry.products.proxies.ProviderProxy;

/**
 * @author shabarish
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProviderProxy providerProxy;
	
	@GetMapping
	public String getProducts() {
		
		return "list of products";
	}
	
	@GetMapping("/{id}")
	public String getProducById(@PathVariable String id) {
		
		return "testing id : "+id;
		
	}

}
