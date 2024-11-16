package jsges.nails.repository.items;

import jsges.nails.domain.items.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, Integer> {

    @Query("select p from Line p  where p.estado=0 order by p.denominacion")
    List<Line> buscarNoEliminados();


    @Query("SELECT p FROM Line p WHERE p.estado = 0 AND  p.denominacion LIKE %:consulta% ORDER BY p.denominacion")
    List<Line> buscarNoEliminados(@Param("consulta") String consulta);

    @Query("SELECT p FROM Line p WHERE p.estado = 0 AND  p.denominacion LIKE:consulta ORDER BY p.denominacion")
    List<Line> buscarExacto(@Param("consulta") String consulta);

    List<Line> findByDenominacionContaining(String consulta);
}
