package com.demo.crud.example.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.crud.example.LocStats;
import com.demo.crud.example.entity.Product;
import com.demo.crud.example.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	RestTemplate restTemplate;

	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	public List<Product> saveProducts(List<Product> products) {
		return productRepo.saveAll(products);
	}

	public List<Product> getProducts() {
		return productRepo.findAll();
	}

	public Product getProductById(Integer id) {
		return productRepo.findById(id).orElse(null);
	}

	public Product getProductByName(String name) {
		return productRepo.findByName(name);
	}

	public String deleteProduct(Integer id) {
		productRepo.deleteById(id);
		return "Product removed!! " + id;
	}

	public Product updateProduct(Product product) {
		Product existingProduct = productRepo.findById(product.getId()).orElse(null);
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setQuantity(product.getQuantity());
		return productRepo.save(existingProduct);
	}

	@Scheduled(cron = "* * 1 * * *")
	public List<LocStats> test() throws IOException {
		List<LocStats> newState = new ArrayList<LocStats>();
		final String uri = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

		String result = restTemplate.getForObject(uri, String.class);
		System.out.println("dlf:: " + result);
		StringReader sr = new StringReader(result);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(sr);
		for (CSVRecord record : records) {
			LocStats locStats = new LocStats();
			locStats.setState(record.get("Province/State"));
			locStats.setCountry(record.get("Country/Region"));
			locStats.setTotalCases(Integer.parseInt(record.get(record.size() - 1)));
			newState.add(locStats);
		}

		this.locStats = newState;
		return locStats;

	}

	private List<LocStats> locStats = new ArrayList<LocStats>();

	public List<LocStats> getLocStats() {
		return locStats;
	}

	public void setLocStats(List<LocStats> locStats) {
		this.locStats = locStats;
	}

}
