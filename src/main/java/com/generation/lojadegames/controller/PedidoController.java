package com.generation.lojadegames.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generation.lojadegames.model.Pedido;
import com.generation.lojadegames.repository.PedidoRepository;
import com.generation.lojadegames.repository.UsuarioRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll(){
        return ResponseEntity.ok(pedidoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id){
        return pedidoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Pedido> post(@Valid @RequestBody Pedido pedido){
        if(usuarioRepository.existsById(pedido.getUsuario().getId())){
            pedido.setId(null);
            if(pedido.getDataPedido() == null){pedido.setDataPedido(LocalDate.now());
            }
            return ResponseEntity  .status(HttpStatus.CREATED).body(pedidoRepository.save(pedido));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<Pedido> put(@Valid @RequestBody Pedido pedido){
        if(pedidoRepository.existsById(pedido.getId())){
            if(usuarioRepository.existsById(pedido.getUsuario().getId())){
            	return ResponseEntity.ok(pedidoRepository.save(pedido));
            }
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(pedido.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}