package com.market.apimarket.persistence;

import com.market.apimarket.domain.Purchase;
import com.market.apimarket.domain.repository.PurchaseRepository;
import com.market.apimarket.persistence.crud.CompraCrudRepository;
import com.market.apimarket.persistence.crud.ProductoCrudRepository;
import com.market.apimarket.persistence.entity.Compra;
import com.market.apimarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Override
    public List<Purchase> getAll() {
        List<Compra> compras= (List<Compra>) compraCrudRepository.findAll();
        return purchaseMapper.toPurchases(compras);
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {

        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }
    @Override
    public Purchase save(Purchase purchase) {
        Compra compra= purchaseMapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
