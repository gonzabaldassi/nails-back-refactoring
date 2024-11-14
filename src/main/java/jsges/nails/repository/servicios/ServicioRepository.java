package jsges.nails.repository.servicios;
import jsges.nails.domain.services.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Service, Integer> {

    @Query("select p from Service p  where p.estado=0 ")
    List<Service> buscarNoEliminados();



    @Query("SELECT p FROM Service p WHERE p.estado = 0 ")
    List<Service> buscarExacto();

    @Query("SELECT p FROM Service p WHERE p.estado = 0")
    List<Service> buscarNoEliminados(@Param("consulta") String consulta);

}

