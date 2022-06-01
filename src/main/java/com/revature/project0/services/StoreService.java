package com.revature.project0.services;

import com.revature.project0.daos.StoreDAO;
import com.revature.project0.models.Store;
import com.revature.project0.util.custom_exceptions.InvalidSQLException;

import java.util.List;

public class StoreService {
    private final StoreDAO storeDAO;

    public StoreService(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    public void register(Store store) {
        storeDAO.save(store);
    }

    public List<Store> getAllStores() {
        return storeDAO.getAll();
    }

    public Store getById(String id) {
        return storeDAO.getById(id);
    }


}
