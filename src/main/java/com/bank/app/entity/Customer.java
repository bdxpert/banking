package com.bank.app.entity;

import com.bank.app.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {
	private String name;

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name= "customer_id")
	private List<Account> accounts;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Customer customer = (Customer) o;
		return  Objects.equals(name, customer.name) &&
				Objects.equals(accounts, customer.accounts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name, accounts);
	}
}
