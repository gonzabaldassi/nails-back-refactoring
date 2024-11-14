package jsges.nails.service.servicios;

import jsges.nails.domain.services.ServiceItem;
import jsges.nails.domain.services.Service;
import jsges.nails.repository.servicios.ItemServicioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Service
public class ItemServicioService implements IItemServicioService {


    @Autowired
    private ItemServicioRepository modelRepository;
    private final Logger log = LoggerFactory.getLogger(ItemServicioService.class);

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
