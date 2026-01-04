package com.bearclawvisions.dto.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "ProductMetadata")
public class ProductMetadata {

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String brand;

    @XmlElement(required = true)
    private String description;

    @XmlElement(required = true)
    private String image;

    @XmlElement
    private BigDecimal price;

    @XmlElement(required = true)
    private String productType;

    @XmlElement(required = true)
    private NutritionalValue nutritionalValue;

    @XmlElementWrapper(name = "Ingredients")
    @XmlElement(name = "Ingredient")
    private List<String> ingredients;

    @XmlElementWrapper(name = "Allergens")
    @XmlElement(name = "Allergen")
    private List<String> allergens;

    @XmlElementWrapper(name = "Tags")
    @XmlElement(name = "Tag")
    private List<String> tags;

    @XmlElement(required = true)
    private String extraInfo;

    // region Constructors
    public ProductMetadata() {
    }

    public ProductMetadata(String name, String brand, String description, String image,
                           BigDecimal price, String productType, NutritionalValue nutritionalValue,
                           List<String> ingredients, List<String> allergens, List<String> tags,
                           String extraInfo) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.brand = Objects.requireNonNull(brand, "Brand cannot be null");
        this.description = Objects.requireNonNull(description, "Description cannot be null");
        this.image = Objects.requireNonNull(image, "Image cannot be null");
        this.price = price;
        this.productType = Objects.requireNonNull(productType, "ProductType cannot be null");
        this.nutritionalValue = Objects.requireNonNull(nutritionalValue, "NutritionalValue cannot be null");
        this.ingredients = Objects.requireNonNull(ingredients, "Ingredients cannot be null");
        this.allergens = Objects.requireNonNull(allergens, "Allergens cannot be null");
        this.tags = Objects.requireNonNull(tags, "Tags cannot be null");
        this.extraInfo = Objects.requireNonNull(extraInfo, "ExtraInfo cannot be null");
    }
    // endregion

    // region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = Objects.requireNonNull(brand, "Brand cannot be null");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNull(description, "Description cannot be null");
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = Objects.requireNonNull(image, "Image cannot be null");
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = Objects.requireNonNull(productType, "ProductType cannot be null");
    }

    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(NutritionalValue nutritionalValue) {
        this.nutritionalValue = Objects.requireNonNull(nutritionalValue, "NutritionalValue cannot be null");
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = Objects.requireNonNull(ingredients, "Ingredients cannot be null");
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = Objects.requireNonNull(allergens, "Allergens cannot be null");
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = Objects.requireNonNull(tags, "Tags cannot be null");
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = Objects.requireNonNull(extraInfo, "ExtraInfo cannot be null");
    }
    // endregion
}