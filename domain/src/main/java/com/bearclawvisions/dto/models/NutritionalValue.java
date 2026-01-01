package com.bearclawvisions.dto.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NutritionalValue")
public class NutritionalValue {

    @XmlElement(required = true)
    private double calories;

    @XmlElement(required = true)
    private double protein;

    @XmlElement(required = true)
    private double carbohydrates;

    @XmlElement(required = true)
    private double fats;

    // region Constructors
    public NutritionalValue() {
    }

    public NutritionalValue(double calories, double protein, double carbohydrates, double fats) {
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
    }
    // endregion

    // region Getters and Setters
    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }
    // endregion
}
