package jsges.nails.service.organization;

import jsges.nails.domain.items.Line;
import jsges.nails.dto.items.LineDTO;
import jsges.nails.dto.organization.CustomerDTO;
import jsges.nails.domain.organization.Customer;
import jsges.nails.mapper.organization.CustomerMapper;
import jsges.nails.repository.organization.CustomerRepository;
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
import java.util.stream.Collectors;

@Service
public class CustomerImplementation implements ICustomerService {

    @Autowired
    private CustomerRepository modelRepository;

    @Autowired
    private CustomerMapper modelMapper;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getModels(){
        return modelRepository.findByState0()
                .stream()
                .map(modelMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getModelById(Integer id) {
        Customer model = modelRepository.findByIdAndState(id,0);

        if (model == null) {
            return null;
        }

        return modelMapper.convertModelToDto(model);
    }

    @Override
    public List<CustomerDTO> getModelByRequest(String request) {

        return modelRepository.findByRequest(request)
                .stream()
                .map(modelMapper::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CustomerDTO> findPaginated(Pageable pageable, List<CustomerDTO> models) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<CustomerDTO> list;
        if (models.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, models.size());
            list = models.subList(startItem, toIndex);
        }

        Page<CustomerDTO> bookPage
                = new PageImpl<CustomerDTO>(list, PageRequest.of(currentPage, pageSize), models.size());

        return bookPage;
    }

    @Override
    public CustomerDTO createModel(Customer model){
        String normalizedName = model.getBusinessName().replaceAll("\\s+", " ").trim().toUpperCase();

        Integer verification = verifyBusinessName(normalizedName);

        if(verification == null) {
            Customer newModel = modelRepository.save(model);
            return modelMapper.convertModelToDto(newModel);
        }

        Customer existingCustomer = modelRepository.findById(verification).orElse(null);

        if(existingCustomer.getState() == 1){

            existingCustomer.setBusinessName(normalizedName);
            existingCustomer.setLetter(model.getLetter());
            existingCustomer.setEmail(model.getEmail());
            existingCustomer.setContact(model.getContact());
            existingCustomer.setServices(model.getServices());
            existingCustomer.setDateOfBirth(model.getDateOfBirth());
            existingCustomer.setPhoneNumber(model.getPhoneNumber());
            existingCustomer.setStartDate(model.getStartDate());
            existingCustomer.setState(0);

            Customer updatedCustomer = modelRepository.save(existingCustomer);

            return modelMapper.convertModelToDto(updatedCustomer);
        }
        return null;
    }

    @Override
    public CustomerDTO updateModel(CustomerDTO editedModelDTO, Customer newModel){
        String normalizedName = newModel.getBusinessName().replaceAll("\\s+", " ").trim().toUpperCase();

        Integer verification = verifyBusinessName(normalizedName, editedModelDTO.getId());

        Customer editedModel = modelRepository.findById(editedModelDTO.getId()).orElse(null);

        if (verification == null){

            editedModel.setLetter(newModel.getLetter());
            editedModel.setEmail(newModel.getEmail());
            editedModel.setContact(newModel.getContact());
            editedModel.setServices(newModel.getServices());
            editedModel.setDateOfBirth(newModel.getDateOfBirth());
            editedModel.setPhoneNumber(newModel.getPhoneNumber());
            editedModel.setStartDate(newModel.getStartDate());
            editedModel.setBusinessName(normalizedName);
            editedModel.setState(0);

            Customer updatedModel = modelRepository.save(editedModel);

            return modelMapper.convertModelToDto(updatedModel);
        }

        Customer existingModel = modelRepository.findById(verification).orElse(null);

        if (existingModel.getState() == 1){

            editedModel.setState(1);

            existingModel.setLetter(newModel.getLetter());
            existingModel.setEmail(newModel.getEmail());
            existingModel.setContact(newModel.getContact());
            existingModel.setServices(newModel.getServices());
            existingModel.setDateOfBirth(newModel.getDateOfBirth());
            existingModel.setPhoneNumber(newModel.getPhoneNumber());
            existingModel.setStartDate(newModel.getStartDate());
            existingModel.setState(0);

            Customer updatedModel = modelRepository.save(existingModel);

            return modelMapper.convertModelToDto(updatedModel);
        }
        return null;
    }

    @Override
    public void deleteModel(CustomerDTO model){
        Customer existingModel = modelRepository.findById(model.getId()).orElse(null);
        if (existingModel != null) {
            existingModel.setState(1);
        }
    }


    @Override
    public Integer verifyBusinessName(String normalizedName){
        List<Customer> customers = customerRepository.findAll();
        String name = normalizedName.replaceAll("\\s+", "");

        for(Customer customer : customers){
            if(name.equals(customer.getBusinessName().replaceAll("\\s+", ""))){
                return customer.getId();
            }
        }
        return null;
    }

    @Override
    public Integer verifyBusinessName(String normalizedName, Integer id){
        List<Customer> customers = customerRepository.findAll();
        String name = normalizedName.replaceAll("\\s+", "");

        for(Customer customer : customers){
            if(customer.getId() != id && name.equals(customer.getBusinessName().replaceAll("\\s+", ""))){
                return customer.getId();
            }
        }
        return null;
    }





















    /*
    @Autowired
    private CustomerRepository customerRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerImplementation.class);
    @Override
    public List<Customer> listar() {
        return customerRepository.buscarNoEliminados();
    }

    @Override
    public Customer buscarPorId(Integer id) {
        Customer model = customerRepository.findById(id).orElse(null);
        return model;
    }

    @Override
    public Customer guardar(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void eliminar(Customer customer) {
          customerRepository.save(customer);
    }

    @Override
    public List<Customer> listar(String consulta) {
         return customerRepository.buscarNoEliminados(consulta);
    }

    public Page<Customer> getClientes(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Page<CustomerDTO> findPaginated(Pageable pageable, List<CustomerDTO> clientes) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<CustomerDTO> list;
        if (clientes.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, clientes.size());
            list = clientes.subList(startItem, toIndex);
        }

        Page<CustomerDTO> bookPage
                = new PageImpl<CustomerDTO>(list, PageRequest.of(currentPage, pageSize), clientes.size());

        return bookPage;
    }

*/
}
