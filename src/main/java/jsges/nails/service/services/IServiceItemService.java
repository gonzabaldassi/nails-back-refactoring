package jsges.nails.service.services;

import jsges.nails.domain.services.ServiceItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceItemService {
    public List<ServiceItem> listar();

    public ServiceItem buscarPorId(Integer id);

    public ServiceItem guardar(ServiceItem model);

    public Page<ServiceItem> findPaginated(Pageable pageable, List<ServiceItem> servicios);

    public Page<ServiceItem> getItemServicios(Pageable pageable);

    public List<ServiceItem> buscarPorServicio(Integer idServicio);
}
