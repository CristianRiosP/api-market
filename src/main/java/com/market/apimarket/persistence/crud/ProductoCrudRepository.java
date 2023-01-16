package com.market.apimarket.persistence.crud;


import com.market.apimarket.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository <Producto,Integer>{

    /*Query method mejor metodo*/
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    Optional<List<Producto>>findByCantidadStockLessThanAndEstado(int cantidadStock,boolean estado);
    /*Query nativo con la sentencia sql*/
    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?",nativeQuery = true)
    List<Producto> getProductosporidCategoria(int idCategoria);
}
