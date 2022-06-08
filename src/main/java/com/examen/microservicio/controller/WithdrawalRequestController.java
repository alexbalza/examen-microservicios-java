package com.examen.microservicio.controller;

import com.examen.microservicio.model.Customer;
import com.examen.microservicio.model.WithdrawalRequest;
import com.examen.microservicio.repository.WithdrawalRequestRepository;
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
@RequestMapping("/api/withdrawalrequest")
public class WithdrawalRequestController {
    @Autowired
    private WithdrawalRequestRepository withdrawalRequestRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Método para regitrar solicitud
     * @param withdrawalRequest la entidad solicitud que va a ser registrada
     * @return String, mensaje de confirmación
     */
    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public String createWithdrawalRequest(@RequestBody WithdrawalRequest withdrawalRequest) {
        LOGGER.info("Se hizo la petición de registrar solicitud");
        withdrawalRequestRepository.save(withdrawalRequest);
        return "Se ha registrado correctamente la solicitud";
    }

    /**
     * Método para actualizar solicitud
     * @param withdrawalRequest,requestId la entidad solicitud que va a ser actualizada por el id de la solicitud
     * @return WithdrawalRequest con los datos ya actualizados
     */
    @PutMapping("/update/{requestId}")
    @ResponseStatus(HttpStatus.OK)
    public WithdrawalRequest updateWithdrawalRequest(@RequestBody WithdrawalRequest withdrawalRequest, @PathVariable(value = "requestId") String requestId) {
        LOGGER.info("Se hizo la petición de actualizar solicitud por id");
        withdrawalRequest.setRequestId(requestId);
        withdrawalRequestRepository.save(withdrawalRequest);
        return withdrawalRequest;
    }

    /**
     * Método para eliminar solicitud
     * @param requestId el id de la solicitud con el cual va a ser borrada la data
     * @return String, mensaje de confirmación
     */
    @DeleteMapping("/delete/{requestId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteWithdrawalRequest(@PathVariable(value = "requestId") String requestId) {
        LOGGER.info("Se hizo la petición de eliminar solicitud por id");
        withdrawalRequestRepository.deleteById(requestId);
        return "Se eliminó correctamente la solicitud";
    }

    /**
     * Método para obtener solicitud
     * @param requestId el id de solicitud con el que se van a traer todos los datos
     * @return ResponseEntity la entidad solicitud que va a ser obtenida
     */
    @GetMapping("/get/{requestId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<WithdrawalRequest> getWithdrawalRequest(@PathVariable(value = "requestId") String requestId) {
        LOGGER.info("Se hizo la petición de obtener solicitud por id");
        Optional<WithdrawalRequest> withdrawalRequest = withdrawalRequestRepository.findById(requestId);
        return new ResponseEntity<>(withdrawalRequest.get(), withdrawalRequest.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Método para listar Solicitudes
     * @return List, todas las solicitudes en una lista
     */
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<WithdrawalRequest> listWithdrawalRequests() {
        LOGGER.info("Se hizo la petición de listar solicitudes");
        return withdrawalRequestRepository.findAll();
    }
}
