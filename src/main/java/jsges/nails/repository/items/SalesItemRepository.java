package jsges.nails.repository.items;

import jsges.nails.domain.items.Line;
import jsges.nails.domain.items.SalesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesItemRepository extends JpaRepository<SalesItem, Integer> {

    @Query("select p from SalesItem p  where p.state=0 order by p.denomination")
    List<SalesItem> findByState0();

    @Query("SELECT p FROM SalesItem p WHERE p.state = 0 AND  p.denomination LIKE:request ORDER BY p.denomination")
    List<SalesItem> findByRequest(@Param("request") String request);

    SalesItem findByIdAndState(Integer id, Integer state);


}
