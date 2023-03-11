package org.lilyhe.admin.controller;

import org.lilyhe.admin.FileUploadUtil;
import org.lilyhe.admin.model.Product;
import org.lilyhe.admin.errors.ProductNotFoundException;
import org.lilyhe.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

/**
 * @author Lily H.
 */

// Controller sends and receives requests, acts like middleman to user and model entities
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // show product listing page
    @GetMapping("/stocklist")
    public String listAll(Model model) {
        List<Product> listProducts = productService.listAll();
        // put list on model
        model.addAttribute("listProducts", listProducts);
        return "stocklist";
    }

    // go to new product form mapping HTTP GET requests
    @GetMapping("/stocklist/new")
    public String newProduct(Model model){
        Product product = new Product();
        product.setInStock("");

        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "Create New Product");
        return "product_form";
    }


    // saving functionality to add entries to db and web
    @PostMapping("/stocklist/save")
    public String saveProduct(Product product, RedirectAttributes ra,
                              @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            product.setMainImage(fileName);

            Product savedProduct = productService.save(product);
            String uploadDir = "../product-images/" + savedProduct.getId();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            System.out.println(product);
            productService.save(product);
        }
        ra.addFlashAttribute("message", "Product saved!");

        /*
        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Address: " + product.getAddress());
        System.out.println("Product Cost: " + product.getCost());
         */
        return "redirect:/stocklist";
    }


    // delete functionality for stocklist listings
    @GetMapping("/stocklist/delete/{id}")
    public String deleteProduct(@PathVariable(name="id") Integer id, Model model,
                                RedirectAttributes redirectAttributes) {
        try {
            productService.delete(id);

            redirectAttributes.addFlashAttribute("message", "The product ID " + id + " has been deleted successfully");
        } catch (ProductNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/stocklist";
    }


}
