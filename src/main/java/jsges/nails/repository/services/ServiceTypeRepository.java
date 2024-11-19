package jsges.nails.repository.services;
import jsges.nails.domain.services.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {

    @Query("select p from ServiceType p  where p.state=0 order by p.denomination")
    List<ServiceType> findByState0();

    @Query("SELECT p FROM ServiceType p WHERE p.state = 0 AND  p.denomination LIKE:request ORDER BY p.denomination")
    List<ServiceType> findByRequest(@Param("request") String request);

    ServiceType findByIdAndState(Integer id, Integer state);
}
