package org.lilyhe.admin.product;

import org.lilyhe.common.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLOutput;
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
        product.setInStock(1);

        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "Create New Product");
        return "product_form";
    }


    @PostMapping("/stocklist/save")
    public String saveProduct(Product product, RedirectAttributes ra){
        System.out.println(product);
        productService.save(product);
        ra.addFlashAttribute("message", "Product saved!");

        /*
        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Description: " + product.getDescription());
        System.out.println("Product Cost: " + product.getCost());

         */
        return "redirect:/stocklist";
    }


}
