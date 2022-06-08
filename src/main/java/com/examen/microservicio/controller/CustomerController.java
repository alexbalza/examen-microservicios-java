package com.examen.microservicio.controller;

import com.examen.microservicio.model.Customer;
import com.examen.microservicio.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Método para regitrar cliente
     *
     * @param customer la entidad cliente que va a ser registrada
     * @return String, mensaje de confirmación
     */
    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public String createCustomer(@RequestBody Customer customer) {
        LOGGER.info("Se hizo la petición de registrar cliente");
        String dni = customer.getDni();
        Optional<Customer> customer1 = listCustomers().stream().filter(c1 -> c1.getDni().equals(dni)).findFirst();

        if (customer1.isPresent()) {
            return "Ya hay un cliente con el mismo dni";
        } else {
            customerRepository.save(customer);
            return "Se ha registrado correctamente el cliente " + customer.getFirstName() + " " + customer.getLastName();
        }
    }

    /**
     * Método para actualizar cliente
     *
     * @param customer,customerId la entidad cliente que va a ser actualizada por el id
     * @return customer con los datos actualizados
     */
    @PutMapping("/update/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable(value = "customerId") String customerId) {
        LOGGER.info("Se hizo la petición de actualizar cliente por id");
        customer.setCustomerId(customerId);
        customerRepository.save(customer);
        return customer;
    }

    /**
     * Método para eliminar cliente
     *
     * @param customerId el id de cliente que se va a usar para eliminar
     * @return String, mensaje de confirmación
     */
    @DeleteMapping("/delete/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCustomer(@PathVariable(value = "customerId") String customerId) {
        LOGGER.info("Se hizo la petición de eliminar cliente por id");
        customerRepository.deleteById(customerId);
        return "Se eliminó correctamente el cliente";
    }

    /**
     * Método para obtener cliente
     *
     * @param customerId el id por el cual se va a obtener la entidad
     * @return ResponseEntity, cliente con los datos ya actualizados
     */
    @GetMapping("/get/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "customerId") String customerId) {
        LOGGER.info("Se hizo la petición de obtener cliente por id");
        Optional<Customer> customer = customerRepository.findById(customerId);
        return new ResponseEntity<>(customer.get(), customer.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Método para listar clientes
     *
     * @return List, lista de tidos los clientes
     */
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> listCustomers() {
        LOGGER.info("Se hizo la petición de listar clientes");
        return customerRepository.findAll();
    }
}