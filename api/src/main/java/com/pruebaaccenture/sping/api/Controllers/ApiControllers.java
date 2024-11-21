package com.pruebaaccenture.sping.api.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.pruebaaccenture.sping.api.Models.Franquicia;
import com.pruebaaccenture.sping.api.Models.Producto;
import com.pruebaaccenture.sping.api.Models.Sucursal;
import com.pruebaaccenture.sping.api.Services.ApiService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ApiControllers {
    
    public ApiService _apiService;

    public ApiControllers(ApiService apiService){
        _apiService = apiService;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String HelloApi() {
        return "API is Working";
    }
    
    @PostMapping("/InsertarFranquicia")
    public ResponseEntity<String> postInsertarFranquicia(@RequestBody Franquicia franquicia) {
        String ans = _apiService.CrearFranquicia(franquicia);
        if(!ans.contains("error") && ans.contains("correctamante")){
            return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(ans);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json").body(ans);
        }
    }
    
    @PostMapping("/InsertarSucursal")
    public ResponseEntity<String> postInsertarSucursal(@RequestBody Sucursal sucursal) {
        String ans = _apiService.CrearSucursal(sucursal);
        if(!ans.contains("error") && ans.contains("correctamante")){
            return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(ans);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json").body(ans);
        }
    }

    @PostMapping("/InsertarProducto")
    public ResponseEntity<String> postInsertarProducto(@RequestBody Producto producto) {
        String ans = _apiService.CrearProducto(producto);
        if(!ans.contains("error") && ans.contains("correctamante")){
            return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(ans);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json").body(ans);
        }
    }
}
