package com.techhungry.products.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="providers-service",url="${providers.url}")
public interface ProviderProxy {
	
	@GetMapping(value="/providers/{id}")
	public ResponseEntity<String> getProviders(@PathVariable("id") String id);

}
