package com.emusicstore.controller;

import com.emusicstore.dao.ProductDao;
import com.emusicstore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class AdminController {
    private Path path;

    @Autowired
    ProductDao productDao;

    @RequestMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @RequestMapping("/admin/productInventory")
    public String productInventory(Model model) {
        List<Product> products = productDao.getAllproduct();
        model.addAttribute("products", products);
        return "productInventory";
    }



    @RequestMapping("/admin/productInventory/addProduct")
    public String addProduct(Model model) {

        Product product = new Product();

        product.setProductStatus("active");
        product.setProductCategory("instrument");
        product.setProductCondition("new");


        model.addAttribute(product);


        return "addProduct";

    }



    @RequestMapping(value = "/admin/productInventory/addProduct", method = RequestMethod.POST)
    public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result , HttpServletRequest request) {
        if (result.hasErrors()){
            return "addProduct";
        }
        productDao.addProduct(product);

        MultipartFile productImage = product.getProductImage();

        String rootPath = request.getSession().getServletContext().getRealPath("/");

        path = Paths.get(rootPath + "WEB-INF/resources/images/" + product.getProductId() + ".png");

        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed",e);
            }

        }

        return "redirect:/admin/productInventory";

    }



    @RequestMapping("/admin/productInventory/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable String productId, Product product, HttpServletRequest request) {

        // MultipartFile productImage = product.getProductImage();
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootPath + "WEB-INF/resources/images/" + productId + ".png");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        productDao.deleteProduct(productId);
        return "redirect:/admin/productInventory";
    }



    @RequestMapping("/admin/productInventory/editProduct/{productId}")
    public String editProduct(@PathVariable String productId, Model model) {
        Product product = productDao.getProductById(productId);
        model.addAttribute(product);
        return "editProduct";
    }



    @RequestMapping(value = "/admin/productInventory/editProduct", method = RequestMethod.POST)
    public String editProduct(@Valid @ModelAttribute("product") Product product, Model model, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()){
            return "editProduct";
        }
        MultipartFile productImage = product.getProductImage();

        String rootPath = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootPath + "WEB-INF/resources/images/" + product.getProductId() + ".png");

        if (productImage != null && !productImage.isEmpty()) {

            try{
                productImage.transferTo(new File(path.toString()));
            }

            catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed",e);
            }


        }
        productDao.editProduct(product);

        return "redirect:/admin/productInventory";
    }
}
