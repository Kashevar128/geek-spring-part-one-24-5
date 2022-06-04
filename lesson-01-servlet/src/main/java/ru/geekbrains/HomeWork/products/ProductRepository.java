package ru.geekbrains.HomeWork.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {

    private final Map<Long, Product> userMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public List<Product> findAll() {
        return new ArrayList<>(userMap.values());
    }

    public Product findById(long id) {
        return userMap.get(id);
    }

    public void insert(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        userMap.put(id, product);
    }

    public void update(Product product) {
        userMap.put(product.getId(), product);
    }

    public void delete(long id) {
        userMap.remove(id);
    }

}
