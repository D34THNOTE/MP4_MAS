package model.subset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Motherboard {
    private String motherboardName;

    private List<Component> processorList = new ArrayList<>();

    private List<Component> componentList = new ArrayList<>();

    public Motherboard(String motherboardName) {
        setMotherboardName(motherboardName);
    }

    public List<Component> getProcessorList() {
        return Collections.unmodifiableList(processorList);
    }

    public List<Component> getComponentList() {
        return Collections.unmodifiableList(componentList);
    }

    public void addComponent(Component component) {
        if(component == null) throw new IllegalArgumentException("No component has been chosen");
        if(componentList.contains(component)) throw new IllegalArgumentException("This component already is assigned to this motherboard");

        componentList.add(component);
        if(component.getComponentType().equals(ComponentType.CPU)) processorList.add(component);
        component.setMotherboard(this);
    }

    public void removeComponent(Component component) {
        if(component == null) throw new IllegalArgumentException("No component has been selected");
        if(!componentList.contains(component)) throw new IllegalArgumentException("Selected component isn't part of this motherboard");

        if(component.getComponentType().equals(ComponentType.CPU)) processorList.remove(component);
        componentList.remove(component);
        component.setMotherboard(null);
    }





    public String getMotherboardName() {
        return motherboardName;
    }

    public void setMotherboardName(String motherboardName) {
        if(motherboardName == null || motherboardName.isBlank()) throw new IllegalArgumentException("Motherboard's name is required");

        this.motherboardName = motherboardName;
    }
}
