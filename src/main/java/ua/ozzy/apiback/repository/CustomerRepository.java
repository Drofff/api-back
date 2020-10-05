package ua.ozzy.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
