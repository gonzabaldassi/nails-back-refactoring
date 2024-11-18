package jsges.nails.repository.services;
import jsges.nails.domain.services.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Integer> {

    @Query("select p from ServiceItem p  where p.estado=0 ")
    List<ServiceItem> buscarNoEliminados();



    @Query("SELECT p FROM ServiceItem p WHERE p.estado = 0 ")
    List<ServiceItem> buscarExacto();

    @Query("SELECT p FROM ServiceItem p WHERE p.estado = 0 AND p.servicio.id = :idServicio")
    List<ServiceItem> buscarPorServicio(@Param("idServicio") Integer idServicio);
}

