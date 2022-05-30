package com.revature.project0.daos;

import com.revature.project0.models.Product;
import com.revature.project0.util.database.DatabaseConnection;
import com.revature.project0.util.custom_exceptions.InvalidSQLException;

import java.util.List;

public class ProductDAO implements CrudDAO<Product>{

    @Override
    public void save(Product obj) {

    }

    @Override
    public void update(Product obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Product getById(String id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    public void updateProdQuant(String quantity, String id) {
    }
}
