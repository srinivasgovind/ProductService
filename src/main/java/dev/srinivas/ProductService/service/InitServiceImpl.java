package dev.srinivas.ProductService.service;


import dev.srinivas.ProductService.model.Category;
import dev.srinivas.ProductService.model.Order;
import dev.srinivas.ProductService.model.Price;
import dev.srinivas.ProductService.model.Product;
import dev.srinivas.ProductService.repository.CategoryRepository;
import dev.srinivas.ProductService.repository.OrderRepository;
import dev.srinivas.ProductService.repository.PriceRepository;
import dev.srinivas.ProductService.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService{

    private ProductRepository productRepository;

    private PriceRepository priceRepository;

    private OrderRepository orderRepository;

    private CategoryRepository categoryRepository;



    public InitServiceImpl(ProductRepository productRepository, PriceRepository priceRepository,OrderRepository orderRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;

    }

    @Override
    public void initialise() {
        Category electronics = new Category();
        electronics.setCategoryName("Electronics");

        electronics = categoryRepository.save(electronics);

        Price priceIphone = new Price();
        priceIphone.setAmount(10000);
        priceIphone.setCurrency("INR");
        priceIphone.setDiscount(0);

        Price priceMacbook = new Price();
        priceMacbook.setDiscount(0);
        priceMacbook.setCurrency("INR");
        priceMacbook.setAmount(200000);

        Price priceWatch = new Price();

        priceWatch.setCurrency("INR");
        priceWatch.setDiscount(0);
        priceWatch.setAmount(50000);

        Price pricePS = new Price();
        pricePS.setAmount(20000);
        pricePS.setCurrency("INR");
        pricePS.setDiscount(0);

        priceWatch = priceRepository.save(priceWatch);
        pricePS = priceRepository.save(pricePS);
        priceIphone = priceRepository.save(priceIphone);
        priceMacbook = priceRepository.save(priceMacbook);

        Product iphone = new Product();
        iphone.setTitle("Iphone 15 Pro");
        iphone.setDescription("Best Iphone ever");
        iphone.setImage("https://google.com/iphone15");
        iphone.setPrice(priceIphone);
        iphone.setCategory(electronics);
        iphone = productRepository.save(iphone);

        Product macbook = new Product();
        macbook.setTitle("Macbook Pro 16");
        macbook.setDescription("Best macbook ever");
        macbook.setImage("http://someImageURl");
        macbook.setPrice(priceMacbook);
        macbook.setCategory(electronics);
        macbook = productRepository.save(macbook);

        Product watch = new Product();
        watch.setTitle("Watch Series 10");
        watch.setDescription("Best watch ever");
        watch.setImage("http://someImageURl");
        watch.setPrice(priceWatch);
        watch.setCategory(electronics);
        watch = productRepository.save(watch);

        Product ps5 = new Product();
        ps5.setTitle("PlayStation5");
        ps5.setDescription("Best playstation ever");
        ps5.setImage("http://someImageURl");
        ps5.setPrice(pricePS);
        ps5.setCategory(electronics);
        ps5 = productRepository.save(ps5);

        Order order = new Order();
        order.setProducts(List.of(iphone,macbook,watch,ps5));
        order = orderRepository.save(order);

    }
}
