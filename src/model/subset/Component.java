package model.subset;

public class Component {

    private String componentName;

    private ComponentType componentType;

    private Motherboard motherboard;

    private Motherboard motherboardProcesses;

    public Component(String componentName, ComponentType componentType) {
        setComponentName(componentName);
        setComponentType(componentType);
    }

    public void setMotherboard(Motherboard motherboard) {
        if(this.motherboard == null && motherboard != null) {
            linkComponent(this, motherboard);
        } else if(this.motherboard != null && motherboard == null) {
            unlinkComponent(this);
        } else if(this.motherboard != null && this.motherboard != motherboard) {
            unlinkComponent(this);
            linkComponent(this, motherboard);
        }
    }




    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        if(componentName == null || componentName.isBlank()) throw new IllegalArgumentException("Component's name is required");

        this.componentName = componentName;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    // private
    private void setComponentType(ComponentType componentType) {
        if(componentType == null) throw new IllegalArgumentException("Component's type is required");

        this.componentType = componentType;
    }




    private static void linkComponent(Component component, Motherboard motherboard) {
        component.motherboard = motherboard;
        if(component.componentType.equals(ComponentType.CPU)) component.motherboardProcesses = motherboard;

        if(!motherboard.getComponentList().contains(component)) {
            motherboard.addComponent(component);
        }
    }

    private static void unlinkComponent(Component component) {
        if(component.motherboard.getComponentList().contains(component)) {
            component.motherboard.removeComponent(component);
        }

        component.motherboard = null;
        if(component.componentType.equals(ComponentType.CPU)) component.motherboardProcesses = null;
    }
}
