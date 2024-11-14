package jsges.nails.repository.servicios;
import jsges.nails.domain.services.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoServicioRepository extends JpaRepository<ServiceType, Integer> {

    @Query("select p from ServiceType p  where p.estado=0 order by p.denominacion")
    List<ServiceType> buscarNoEliminados();


    @Query("SELECT p FROM ServiceType p WHERE p.estado = 0 AND  p.denominacion LIKE %:consulta% ORDER BY p.denominacion")
    List<ServiceType> buscarNoEliminados(@Param("consulta") String consulta);

    @Query("SELECT p FROM ServiceType p WHERE p.estado = 0 AND  p.denominacion LIKE:consulta ORDER BY p.denominacion")
    List<ServiceType> buscarExacto(@Param("consulta") String consulta);

    List<ServiceType> findByDenominacionContaining(String consulta);
}
