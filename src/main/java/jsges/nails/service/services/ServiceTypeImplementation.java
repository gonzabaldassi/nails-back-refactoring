package jsges.nails.service.services;
import jsges.nails.dto.services.TipoServicioDTO;
import jsges.nails.domain.services.ServiceType;
import jsges.nails.repository.services.TipoServicioRepository;
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
public class ServiceTypeImplementation implements IServiceTypeService {

    @Autowired
    private TipoServicioRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(ServiceTypeImplementation.class);

    @Override
    public List<ServiceType> listar() {
        return modelRepository.buscarNoEliminados();
    }

    @Override
    public ServiceType buscarPorId(Integer id) {
        return modelRepository.findById(id).orElse(null);
    }



    @Override
    public ServiceType guardar(ServiceType model) {
        return modelRepository.save(model);
    }


    @Override
    public ServiceType newModel(TipoServicioDTO modelDTO) {
        ServiceType model =  new ServiceType();
        model.setDenominacion(modelDTO.denominacion);
        return guardar(model);
    }


    @Override
    public void eliminar(ServiceType model) {

        modelRepository.save(model);
    }

    @Override
    public List<ServiceType> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

    @Override
    public Page<ServiceType> getTiposServicios(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }

    public List<ServiceType> buscar(String consulta) {
        return modelRepository.buscarExacto(consulta);
    }


    @Override
    public Page<ServiceType> findPaginated(Pageable pageable, List<ServiceType>lineas) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ServiceType> list;
        if (lineas.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, lineas.size());
            list = lineas.subList(startItem, toIndex);
        }

        Page<ServiceType> bookPage
                = new PageImpl<ServiceType>(list, PageRequest.of(currentPage, pageSize), lineas.size());

        return bookPage;
    }

}
