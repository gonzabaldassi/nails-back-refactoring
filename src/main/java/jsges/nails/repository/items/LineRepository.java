package jsges.nails.repository.items;

import jsges.nails.domain.items.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, Integer> {

    @Query("select p from Line p  where p.state=0 order by p.denomination")
    List<Line> findByState0();

    @Query("SELECT p FROM Line p WHERE p.state = 0 AND  p.denomination LIKE:request ORDER BY p.denomination")
    List<Line> findByRequest(@Param("request") String request);

    Line findByIdAndState(Integer id, Integer state);
}


