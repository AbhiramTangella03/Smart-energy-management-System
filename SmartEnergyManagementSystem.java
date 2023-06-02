import java.util.Scanner;

public class SmartEnergyManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double presentUnits, goalUnits, maxUnits;
        // get present and goal units from user
        System.out.print("Enter present units: ");
        presentUnits = sc.nextDouble();
        System.out.print("Enter goal units: ");
        goalUnits = sc.nextDouble();

        // calculate max units allowed
        maxUnits = presentUnits + (presentUnits - goalUnits);

        // create smart devices
        SmartThermostat thermostat = new SmartThermostat();
        SmartLightingSystem lightingSystem = new SmartLightingSystem();

        while (true) {
            System.out.print("Enter units used: ");
            double unitsUsed = sc.nextDouble();
            presentUnits += unitsUsed;
            System.out.println("Total units used: " + presentUnits);

            // adjust temperature based on energy usage
            thermostat.adjustTemperature(presentUnits, goalUnits);

            // adjust lighting based on energy usage
            lightingSystem.adjustLighting(presentUnits, goalUnits);

            if (presentUnits >= maxUnits) {
                System.out.println("Maximum units reached. Power will be cutoff.");

                // ask user if they want to continue
                System.out.print("Do you want to continue? (y/n): ");
                String choice = sc.next();

                if (choice.equalsIgnoreCase("n")) {
                    break;
                } else if (choice.equalsIgnoreCase("y")) {
                    // ask user for new goal units
                    System.out.print("Enter new goal units: ");
                    goalUnits = sc.nextDouble();
                    maxUnits = presentUnits + (presentUnits - goalUnits);
                }
            }

            // time-based power management
            // if (isTimeBasedPowerManagementEnabled) {
            // int hourOfDay = java.time.LocalTime.now().getHour(); // get current hour of
            // day

            // if (hourOfDay >= 18 || hourOfDay < 6) { // if current hour is between 6PM to
            // 6AM
            // System.out.println("Time-based power management activated. Power will be
            // cutoff until 6AM.");
            // // simulate power cutoff by setting units used to 0
            // unitsUsed = -presentUnits;
            // }
            // }
        }

        sc.close();
    }
}

class SmartThermostat {
    // adjust temperature based on energy usage
    public void adjustTemperature(double presentUnits, double goalUnits) {
        double energyUsageRatio = presentUnits / goalUnits;

        if (energyUsageRatio > 1.2) { // if energy usage is more than 20% above goal
            System.out.println("Energy usage is high. Decreasing temperature to save energy.");
            // decrease temperature by 2 degrees
        } else if (energyUsageRatio < 0.8) { // if energy usage is less than 20% below goal
            System.out.println("Energy usage is low. Increasing temperature to optimize comfort.");
            // increase temperature by 2 degrees
        } else {
            System.out.println("Energy usage is normal. Temperature remains unchanged.");
        }
    }
}

class SmartLightingSystem {
    private double brightness = 1.0; // default brightness

    // adjust lighting based on energy usage
    public void adjustLighting(double presentUnits, double goalUnits) {
        double energyUsageRatio = presentUnits / goalUnits;

        if (energyUsageRatio > 1.2) { // if energy usage is more than 20% above goal
            System.out.println("Energy usage is high. Dimming lights by 20%.");
            // dim the lights by reducing brightness by 20%
            double newBrightness = 0.8 * brightness;
            setBrightness(newBrightness);
        } else if (energyUsageRatio < 0.8) { // if energy usage is less than 20% below goal
            System.out.println("Energy usage is low. Increasing lights by 20%.");
            // increase the lights by increasing brightness by 20%
            double newBrightness = 1.2 * brightness;
            setBrightness(newBrightness);
        } else {
            System.out.println("Energy usage is normal. Lights remain unchanged.");
        }
    }

    // get the current brightness of the lights
    public double getBrightness() {
        return brightness;
    }

    // set the brightness of the lights
    public void setBrightness(double brightness) {
        this.brightness = brightness;
        System.out.println("Brightness set to " + brightness);
    }

}
