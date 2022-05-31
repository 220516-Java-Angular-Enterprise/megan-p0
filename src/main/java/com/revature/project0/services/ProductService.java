package com.revature.project0.services;

import com.revature.project0.daos.ProductDAO;
import com.revature.project0.models.Product;
import com.revature.project0.util.custom_exceptions.InvalidSQLException;

import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void register(Product prod) {
        productDAO.save(prod);
    }

    public List<Product> getAllProd() {
        return productDAO.getAll();
    }

    public boolean updateQuant(int quantity, String id) {
        try {
            productDAO.updateProdQuant(quantity, id);
            return true;
        } catch (InvalidSQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public boolean updateName(String name, String id) {
        try {
            productDAO.updateProdName(name, id);
            return true;
        } catch (InvalidSQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }


    public boolean deleteProd(String id) {
        try {
            productDAO.delete(id);
            return true;
        } catch (InvalidSQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
