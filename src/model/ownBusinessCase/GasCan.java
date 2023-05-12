package model.ownBusinessCase;

public class GasCan {

    private int currentGas_liters, maxGas_liters;

    public GasCan(int currentGas_liters, int maxGas_liters) {
        setMaxGas_liters(maxGas_liters);
        setCurrentGas_liters(currentGas_liters);
    }

    public int getCurrentGas_liters() {
        return currentGas_liters;
    }

    public void setCurrentGas_liters(int currentGas_liters) {
        if(currentGas_liters < 0) throw new IllegalArgumentException("Current gas cannot be a negative number");
        if(currentGas_liters > maxGas_liters) throw new IllegalArgumentException("Current gas cannot exceed the max gas value");

        this.currentGas_liters = currentGas_liters;
    }

    public int getMaxGas_liters() {
        return maxGas_liters;
    }

    public void setMaxGas_liters(int maxGas_liters) {
        if(maxGas_liters < 0) throw new IllegalArgumentException("Max gas cannot be a negative number");
        if(maxGas_liters < currentGas_liters) this.currentGas_liters = maxGas_liters;

        this.maxGas_liters = maxGas_liters;
    }
}
