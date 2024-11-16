package jsges.nails.service.items;

import jsges.nails.dto.items.ArticuloVentaDTO;
import jsges.nails.domain.items.SalesItem;
import jsges.nails.repository.items.SalesItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;


@Service
public class SalesItemImplementation implements ISalesItemService {
    @Autowired
    private SalesItemRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(SalesItemImplementation.class);


    @Override
    public List<SalesItem> listar() {
        return modelRepository.buscarNoEliminados();
    }

    @Override
    public SalesItem buscarPorId(Integer id) {
        return modelRepository.findById(id).orElse(null);
    }

    @Override
    public SalesItem guardar(SalesItem model) {
        return modelRepository.save(model);
    }

    @Override
    public void eliminar(SalesItem model) {
        modelRepository.save(model);
    }

    @Override
    public List<SalesItem> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

    @Override
    public Page<SalesItem> getArticulos(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }

    @Override
    public Page<ArticuloVentaDTO> findPaginated(Pageable pageable, List<ArticuloVentaDTO> listado) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ArticuloVentaDTO> list;
        if (listado.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listado.size());
            list = listado.subList(startItem, toIndex);
        }

        Page<ArticuloVentaDTO> bookPage
                = new PageImpl<ArticuloVentaDTO>(list, PageRequest.of(currentPage, pageSize), listado.size());

        return bookPage;
    }



}
