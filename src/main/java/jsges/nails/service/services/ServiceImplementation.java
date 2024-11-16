package jsges.nails.service.services;
import jsges.nails.dto.services.ServicioDTO;
import jsges.nails.repository.services.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

/*import jsges.nails.domain.services.Service;*/
import org.springframework.stereotype.Service;

@Service
public class ServiceImplementation implements IServiceService {

    @Autowired
    private ServiceRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(ServiceImplementation.class);

    @Override
    public List<Service> listar() {
        return modelRepository.buscarNoEliminados();
    }

    @Override
    public Service buscarPorId(Integer id) {
        return modelRepository.findById(id).orElse(null);
    }


    @Override
    public Service guardar(Service model) {
        return modelRepository.save(model);
    }


    @Override
    public Page<Service> getServicios(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }



    @Override
    public Page<ServicioDTO> findPaginated(Pageable pageable, List<ServicioDTO> listado) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ServicioDTO> list;
        if (listado.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listado.size());
            list = listado.subList(startItem, toIndex);
        }

        Page<ServicioDTO> bookPage
                = new PageImpl<ServicioDTO>(list, PageRequest.of(currentPage, pageSize), listado.size());

        return bookPage;
    }

    @Override
    public List<Service> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

}
