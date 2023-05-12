package model.unique;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Car {

    private static List<Car> extent = new ArrayList<>();

    private String manufacturer, model, licensePlate;

    public Car(String manufacturer, String model, String licensePlate) {
        setManufacturer(manufacturer);
        setModel(model);
        setLicensePlate(licensePlate);

        extent.add(this);
    }

    public static List<Car> getExtent() {
        return Collections.unmodifiableList(extent);
    }

    public static void removeCarFromExtent(Car car) {
        if(car == null) throw new IllegalArgumentException("Please choose a car to remove");
        if(!extent.contains(car)) throw new IllegalArgumentException("Chosen car doesn't exist in the database");

        extent.remove(car);
    }




    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        if(manufacturer == null || manufacturer.isBlank()) throw new IllegalArgumentException("Manufacturer of the car is required");

        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(model == null || model.isBlank()) throw new IllegalArgumentException("Model of the car is required");

        this.model = model;
    }




    // The unique attribute
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        if(licensePlate == null || licensePlate.isBlank()) throw new IllegalArgumentException("License plate of the car is required");
        if(extent.stream().anyMatch(car -> car.licensePlate.equals(licensePlate))) throw new IllegalArgumentException("Chosen license plate isn't unique");

        this.licensePlate = licensePlate;
    }
}
