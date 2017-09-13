package com.emusicstore.dao.impl;

import com.emusicstore.dao.ProductDao;
import com.emusicstore.model.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public class ProductDaoImpl implements ProductDao {

    @Autowired
    SessionFactory factory;

    public void addProduct(Product product) {

        Session session = factory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();
    }

    public Product getProductById(String id) {

        Session session = factory.getCurrentSession();
        Product product = (Product) session.get(Product.class,id);
        session.flush();

        return product;

    }

    @SuppressWarnings("JpaQlInspection")
    public List<Product> getAllproduct() {
        Session session = factory.getCurrentSession();

        Query query = session.createQuery("from Product");

        List<Product> products = query.list();

        session.flush();


        return products;
    }

    public void deleteProduct(String productId) {

        Session session = factory.getCurrentSession();
        session.delete(getProductById(productId));
        session.flush();

    }
}
