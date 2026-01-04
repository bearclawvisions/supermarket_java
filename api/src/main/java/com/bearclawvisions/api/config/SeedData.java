package com.bearclawvisions.api.config;

import com.bearclawvisions.dto.models.NutritionalValue;
import com.bearclawvisions.dto.models.ProductMetadata;
import com.bearclawvisions.entitities.Product;
import com.bearclawvisions.entitities.ProductCategory;
import com.bearclawvisions.enums.ProductStatus;
import com.bearclawvisions.repositories.ProductCategoryRepository;
import com.bearclawvisions.repositories.ProductRepository;
import com.bearclawvisions.utils.XmlUtils;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public void run(String... args) throws Exception {
        List<ProductCategory> categories = productCategoryRepository.findAll();

        if (categories.isEmpty()) {
            seedDatabase();
        }

        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            seedDatabase();
        }
    }

    public void seedDatabase() throws IOException, JAXBException, URISyntaxException {
        // Load from classpath resource
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader == null) {
            throw new IllegalStateException("Class loader is null");
        }

        URL file = Objects.requireNonNull(classLoader.getResource("data.json"));
        File dataFile = new File(file.toURI());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.readTree(dataFile);

        if (jsonData.has("products") && jsonData.has("categories")) {

            // Seed categories
//            JsonNode categoriesElement = jsonData.get("categories");
//            for (JsonNode categoryElement : categoriesElement) {
//                seedCategory(categoryElement);
//            }

            // Seed products
            JsonNode productsElement = jsonData.get("products");
            for (JsonNode productElement : productsElement) {
                seedProduct(productElement);
            }

            System.out.println("Database seeded successfully");
        }
    }

    private void seedCategory(JsonNode categoryElement) {
        String name = categoryElement.get("name").asString();
        String description = categoryElement.get("description").asString();

        ProductCategory category = new ProductCategory();
        category.setName(name);
        category.setDescription(description);
        category.setCreatedBy("System");

        productCategoryRepository.save(category);

        System.out.println("Category: " + name + " - Description: " + description);
    }

    private void seedProduct(JsonNode productElement) throws JAXBException {
        // read from JSON
        String name = productElement.get("name").asString();
        String category = productElement.get("category").asString();
        String type = productElement.get("type").asString();
        String brand = productElement.get("brand").asString();
        String description = productElement.get("description").asString();
        BigDecimal priceKg = BigDecimal.valueOf(Double.parseDouble(productElement.get("price-kg").asString("0")));
        String allergen = productElement.get("allergen").asString();
        String extraInfo = productElement.get("extra-info").asString();

        JsonNode nutritionalValue = productElement.get("nutritional-value");
        String metadataXml = createNutritionalValue(nutritionalValue, name, brand, description, priceKg, type, allergen, extraInfo);

        // Get category ID
        int categoryId = productCategoryRepository.findByName(category).getId();

        Product product = new Product();
        product.setCategoryId(categoryId);
        product.setName(name);
        product.setMetadata(metadataXml);
        product.setPrice(priceKg);
        product.setQuantity(10);
        product.setStatus(ProductStatus.IN_STOCK);
        product.setExpiryDate(LocalDateTime.now().plusDays(30));
        product.setCreatedBy("System");

        productRepository.save(product);
    }

    private String createNutritionalValue(JsonNode nutritionalValue, String name, String brand, String description, BigDecimal priceKg, String type, String allergen, String extraInfo)
            throws JAXBException {
        int calories = Integer.parseInt(nutritionalValue.get("calories").asString("0"));
        double protein = Double.parseDouble(nutritionalValue.get("protein").asString("0"));
        double fat = Double.parseDouble(nutritionalValue.get("fat").asString("0"));
        double carbohydrate = Double.parseDouble(nutritionalValue.get("carbohydrate").asString("0"));

        // Build metadata
        NutritionalValue nutritional = new NutritionalValue();
        nutritional.setCalories(calories);
        nutritional.setProtein(protein);
        nutritional.setCarbohydrates(carbohydrate);
        nutritional.setFats(fat);

        ProductMetadata metadata = new ProductMetadata();
        metadata.setName(name);
        metadata.setBrand(brand);
        metadata.setDescription(description);
        metadata.setImage("placeholder.jpg");
        metadata.setPrice(priceKg);
        metadata.setProductType(type);
        metadata.setNutritionalValue(nutritional);
        metadata.setIngredients(new ArrayList<>());
        metadata.setAllergens(new ArrayList<>(List.of(allergen)));
        metadata.setTags(new ArrayList<>());
        metadata.setExtraInfo(extraInfo);

        return XmlUtils.toXml(metadata);
    }
}
