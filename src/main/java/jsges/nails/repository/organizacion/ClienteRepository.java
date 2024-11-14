package jsges.nails.repository.organizacion;

import jsges.nails.domain.organization.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Customer, Integer> {

    @Query("select p from Customer p  where p.estado=0 order by p.razonSocial")
    List<Customer> buscarNoEliminados();


    @Query("SELECT p FROM Customer p WHERE p.estado = 0 AND  p.razonSocial LIKE %:consulta% ORDER BY p.razonSocial")
    List<Customer> buscarNoEliminados(@Param("consulta") String consulta);





}
