package jsges.nails.service.services;

import jsges.nails.domain.services.ServiceItem;
import jsges.nails.repository.services.ItemServicioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*import jsges.nails.domain.services.Service;*/

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceItemImplementation implements IServiceItemService {


    @Autowired
    private ItemServicioRepository modelRepository;
    private final Logger log = LoggerFactory.getLogger(ServiceItemImplementation.class);

    @Override
    public List<ServiceItem> listar() {
        System.out.println("2"); // Debug: Verificar el contenido
        List<ServiceItem> items = modelRepository.findAll();
        System.out.println("Items desde el repositorio: " + items); // Debug: Verificar el contenido
        return items;
    }

    @Override
    public ServiceItem buscarPorId(Integer id) {
        return null;
    }

    @Override
    public ServiceItem guardar(ServiceItem model) {
        return modelRepository.save(model);
    }

    @Override
    public Page<ServiceItem> findPaginated(Pageable pageable, List<ServiceItem> servicios) {
        return null;
    }

    @Override
    public Page<ServiceItem> getItemServicios(Pageable pageable) {
        return null;
    }
    @Override
    public List<ServiceItem> buscarPorServicio(Integer idServicio){

        return modelRepository.buscarPorServicio(idServicio);
    };
}
