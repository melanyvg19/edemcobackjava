package com.IntegracionSiesa.Controller;

import com.IntegracionSiesa.Service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping("/list")
    private ResponseEntity<?> listClientes(){
        return ResponseEntity.ok(clienteService.listClientes());
    }

    @GetMapping("/list_especiales")
    private ResponseEntity<?> listClientesEspeciales() {
        return ResponseEntity.ok(clienteService.findSpecialCustomers());
    }

    @GetMapping("/find")
    private ResponseEntity<?> findById(@RequestParam("nit") String nit){
        return ResponseEntity.ok(clienteService.findById(nit));
    }

}
