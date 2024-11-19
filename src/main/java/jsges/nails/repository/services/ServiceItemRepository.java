package jsges.nails.repository.services;
import jsges.nails.domain.services.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Integer> {

    @Query("select p from ServiceItem p  where p.state=0 ")
    List<ServiceItem> findByState0();

    @Query("SELECT p FROM ServiceItem p WHERE p.state = 0 AND p.service.id = :idService")
    List<ServiceItem> findByService(@Param("idService") Integer idService);

    ServiceItem findByIdAndState(Integer id, Integer state);
}

