/**
 * 
 */
package com.demo.crud.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anil Sandrapati
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT_TBL")
@Entity
public class Product {
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private Integer quantity;

	private Double price;

}
