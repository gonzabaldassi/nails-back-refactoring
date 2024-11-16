package jsges.nails.repository.items;

import jsges.nails.domain.items.SalesItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesItemRepository extends JpaRepository<SalesItem, Integer> {

    @Query("select p from SalesItem p  where p.estado=0 order by p.denominacion")
    List<SalesItem> buscarNoEliminados();


    @Query("SELECT p FROM SalesItem p WHERE p.estado = 0 AND  p.denominacion LIKE %:consulta% ORDER BY p.denominacion")
    List<SalesItem> buscarNoEliminados(@Param("consulta") String consulta);

}
