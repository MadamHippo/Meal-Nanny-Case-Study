package org.lilyhe.admin.user;

import org.junit.jupiter.api.Test;
import org.lilyhe.admin.repository.ProductRepo;
import org.lilyhe.admin.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

// imported
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lily H.
 *
 * Tests for database
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepoTests {

    @Autowired
    private ProductRepo repo;

    @Autowired
    private TestEntityManager entityManager;

    /* Test successful: first test to create
    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("TheOriginal");
        product.setAlias("original_pantry");
        product.setAddress("Seattle, WA 98102");
        product.setCost(99);
        Product savedProduct = repo.save(product);

        assertThat(savedProduct).isNotNull();

        assertThat(savedProduct.getId()).isGreaterThan(0);
    }

     */

    // get list of all products
    @Test
    public void testListAllProducts() {
        Iterable<Product> iterableProducts = repo.findAll();

        // double colon :: is known as the method reference, used without invoking to print text to console. we're
        // printing each element using forEach.
        iterableProducts.forEach(System.out::println);

    }


    /* Test fetch successful
    @Test
    public void testGetProduct() {
        Integer id = 2;
        Product product = repo.findById(id).get();
        System.out.println(product);
        assertThat(product).isNotNull();
    }

    */

    // Test successful - updating product in db
    @Test
    public void testUpdateProduct() {
        String address = "12345 fake st";
        Integer id = 1;
        Product product = repo.findById(id).get();
        product.setAddress(address);
        repo.save(product);

        Product updatedPriceProduct = entityManager.find(Product.class, id);

        assertThat(updatedPriceProduct.getAddress()).isEqualTo(address);
    }


    /* Test successful delete product in db
    @Test
    public void testDeleteProduct(){
        Integer id=3;
        repo.deleteById(id);

        Optional<Product> deletedProduct = repo.findById(id);
        assertThat(!deletedProduct.isPresent());
    }

    */

    /* Test successful - testing to see if images can be added to db
    @Test
    public void testSaveProductWithImages(){
        Integer productTest=1;
        Product product = repo.findById(productTest).get();

        product.setMainImage("main-image.jpg");
        product.addExtraImage("extra-image-2.png");
        product.addExtraImage("extra-image-3.png");

        Product savedProduct = repo.save(product);

        assertThat(savedProduct.getImages().size()).isEqualTo(2);
    }
    */
}
