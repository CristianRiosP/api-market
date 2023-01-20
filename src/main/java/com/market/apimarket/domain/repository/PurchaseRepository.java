package com.market.apimarket.domain.repository;

import com.market.apimarket.domain.Purchase;
import com.market.apimarket.domain.PurchaseItem;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);

}
