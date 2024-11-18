package jsges.nails.repository.organization;

import jsges.nails.domain.items.Line;
import jsges.nails.domain.organization.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select p from Customer p  where p.state=0 order by p.businessName")
    List<Customer> findByState0();


    @Query("SELECT p FROM Customer p WHERE p.state = 0 AND  p.businessName LIKE:request ORDER BY p.businessName")
    List<Customer> findByRequest(@Param("request") String request);

    Customer findByIdAndState(Integer id, Integer state);




}
