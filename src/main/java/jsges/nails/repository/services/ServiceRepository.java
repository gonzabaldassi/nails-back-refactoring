package jsges.nails.repository.services;
import jsges.nails.domain.services.ServiceClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceClass, Integer> {

    @Query("select p from ServiceClass p  where p.state=0 ")
    List<ServiceClass> findByState0();

    @Query("SELECT p FROM ServiceClass p WHERE p.state = 0")
    List<ServiceClass> findByRequest(@Param("request") String request);

    ServiceClass findByIdAndState(Integer id, Integer state);

}

