package com.pruebaaccenture.sping.api.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.Query;
import com.pruebaaccenture.sping.api.Models.Franquicia;
import com.pruebaaccenture.sping.api.Models.Producto;
import com.pruebaaccenture.sping.api.Models.Sucursal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiService {
    
    public String CrearFranquicia(Franquicia franquicia){
        try 
        {
            Firestore dbFirestore = com.google.firebase.cloud.FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection("franquicia").document(franquicia.nombreFranquicia);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();
            if(document.exists()){
                return "La franquicia ya existe";
            }
            if(franquicia.nombreFranquicia.equals("") || franquicia.nombreFranquicia == null){
                return "No se especificó un nombre para la franquicia";
            }
            ApiFuture<WriteResult> collectFuture = dbFirestore.collection("franquicia").document(franquicia.nombreFranquicia).set(franquicia);
            return "Franquicia creada correctamante: "+collectFuture.get().getUpdateTime().toString();
        } 
        catch (Exception e) 
        {
            return "Ocurrio el siguiente error: "+e.getMessage();
        }
    }

    public String CrearSucursal(Sucursal sucursal){
        try 
        {
            Firestore dbFirestore = com.google.firebase.cloud.FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection("sucursal").document(sucursal.nombreSucursal);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();
            if(document.exists()){
                return "La sucursal ya existe para la franquicia "+document.getString("nombreFranquicia");
            }

            DocumentReference documentReference2 = dbFirestore.collection("franquicia").document(sucursal.nombreFranquicia);
            ApiFuture<DocumentSnapshot> future2 = documentReference2.get();
            DocumentSnapshot document2 = future2.get();
            if(!document2.exists()){
                return "La franquicia especificada no existe";
            }

            if(sucursal.nombreSucursal.equals("") || sucursal.nombreSucursal == null){
                return "No se especificó un nombre para la sucursal";
            }

            ApiFuture<WriteResult> collectFuture = dbFirestore.collection("sucursal").document(sucursal.nombreSucursal).set(sucursal);
            return "Sucursal creada correctamante: "+collectFuture.get().getUpdateTime().toString();
        } 
        catch (Exception e) 
        {
            return "Ocurrio el siguiente error: "+e.getMessage();
        }
    }

    public String CrearProducto(Producto producto){
        try 
        {
            if(producto.nombreProducto.equals("")){
                return "No se especificó un nombre para el producto";
            }
            producto.nombreProducto = producto.nombreProducto+"Carlos32PTAccenture"+producto.nombreSucursal;

            Firestore dbFirestore = com.google.firebase.cloud.FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection("producto").document(producto.nombreProducto);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();
            if(document.exists()){
                return "El producto ya existe para la sucursal "+document.getString("nombreSucursal");
            }

            DocumentReference documentReference2 = dbFirestore.collection("sucursal").document(producto.nombreSucursal);
            ApiFuture<DocumentSnapshot> future2 = documentReference2.get();
            DocumentSnapshot document2 = future2.get();
            if(!document2.exists()){
                return "La sucursal especificada no existe";
            }

            if(producto.stockProducto < 0){
                return "El producto tiene un valor de stock invalido";
            }

            ApiFuture<WriteResult> collectFuture = dbFirestore.collection("producto").document(producto.nombreProducto).set(producto);
            return "Producto creado correctamante: "+collectFuture.get().getUpdateTime().toString();
        } 
        catch (Exception e) 
        {
            return "Ocurrio el siguiente error: "+e.getMessage();
        }
    }

    public String EliminarProducto(Producto producto){
        try 
        {
            producto.nombreProducto = producto.nombreProducto+"Carlos32PTAccenture"+producto.nombreSucursal;

            Firestore dbFirestore = com.google.firebase.cloud.FirestoreClient.getFirestore();
            DocumentReference documentReference2 = dbFirestore.collection("sucursal").document(producto.nombreSucursal);
            ApiFuture<DocumentSnapshot> future2 = documentReference2.get();
            DocumentSnapshot document2 = future2.get();
            if(!document2.exists()){
                return "La sucursal especificada no existe";
            }

            DocumentReference documentReference = dbFirestore.collection("producto").document(producto.nombreProducto);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();
            if(!document.exists()){
                return "El producto no existe para la sucursal "+producto.nombreSucursal;
            }

            ApiFuture<WriteResult> collectFuture = dbFirestore.collection("producto").document(producto.nombreProducto).delete();
            return "Producto eliminado correctamante: "+collectFuture.get().getUpdateTime().toString();
        } 
        catch (Exception e) 
        {
            return "Ocurrio el siguiente error: "+e.getMessage();
        }
    }

    public String ModificarStockProducto(Producto producto){
        try 
        {
            producto.nombreProducto = producto.nombreProducto+"Carlos32PTAccenture"+producto.nombreSucursal;

            Firestore dbFirestore = com.google.firebase.cloud.FirestoreClient.getFirestore();
            DocumentReference documentReference2 = dbFirestore.collection("sucursal").document(producto.nombreSucursal);
            ApiFuture<DocumentSnapshot> future2 = documentReference2.get();
            DocumentSnapshot document2 = future2.get();
            if(!document2.exists()){
                return "La sucursal especificada no existe";
            }

            DocumentReference documentReference = dbFirestore.collection("producto").document(producto.nombreProducto);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();
            if(!document.exists()){
                return "El producto no existe para la sucursal "+producto.nombreSucursal;
            }

            if(producto.stockProducto < 0){
                return "El producto tiene un valor de stock invalido";
            }

            ApiFuture<WriteResult> collectFuture = dbFirestore.collection("producto").document(producto.nombreProducto).set(producto);
            return "Stock del producto actualizado correctamante: "+collectFuture.get().getUpdateTime().toString();
        } 
        catch (Exception e) 
        {
            return "Ocurrio el siguiente error: "+e.getMessage();
        }
    }

    public String TopProductoSucursal(Franquicia franquicia){
        try 
        {
            Firestore dbFirestore = com.google.firebase.cloud.FirestoreClient.getFirestore();
            DocumentReference documentReference = dbFirestore.collection("franquicia").document(franquicia.nombreFranquicia);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();
            if(!document.exists()){
                return "La franquicia no existe";
            }

            ApiFuture<QuerySnapshot> sucursalesQuery = dbFirestore.collection("sucursal").whereEqualTo("nombreFranquicia", franquicia.nombreFranquicia).get();
            List<QueryDocumentSnapshot> sucursales = sucursalesQuery.get().getDocuments();

            Map<String, Map<String, Object>> productosMayorStock = new HashMap<>();

            for (QueryDocumentSnapshot sucursalDoc : sucursales) {
                String nombreSucursal = sucursalDoc.getString("nombreSucursal");

                ApiFuture<QuerySnapshot> productosQuery = dbFirestore.collection("producto")
                        .whereEqualTo("nombreSucursal", nombreSucursal)
                        .orderBy("stockProducto", Query.Direction.DESCENDING)
                        .limit(1)
                        .get();

                List<QueryDocumentSnapshot> productos = productosQuery.get().getDocuments();

                if (!productos.isEmpty()) {
                    String anteriorNombre = (String)productos.get(0).getData().get("nombreProducto");
                    Map<String, Object> productoActualizado = productos.get(0).getData();
                    productoActualizado.put("nombreProducto", anteriorNombre.split("Carlos32PTAccenture")[0]);
                    productosMayorStock.put(nombreSucursal, productoActualizado);
                }
            }
            return new ObjectMapper().writeValueAsString(productosMayorStock);
        } 
        catch (Exception e) 
        {
            return "Ocurrio el siguiente error: "+e.getMessage();
        }
    }
}
