package com.pruebaaccenture.sping.api.Services;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.v1.FirestoreClient;
import com.pruebaaccenture.sping.api.Models.Franquicia;
import com.pruebaaccenture.sping.api.Models.Producto;
import com.pruebaaccenture.sping.api.Models.Sucursal;

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

            ApiFuture<WriteResult> collectFuture = dbFirestore.collection("producto").document(producto.nombreProducto).set(producto);
            return "Producto creado correctamante: "+collectFuture.get().getUpdateTime().toString();
        } 
        catch (Exception e) 
        {
            return "Ocurrio el siguiente error: "+e.getMessage();
        }
    }
}
