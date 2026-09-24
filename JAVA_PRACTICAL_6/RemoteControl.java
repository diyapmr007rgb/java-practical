interface Switchable {

    void on();

    void off();

  default void toggle() {
        System.out.println("Toggling device...");
    }
}

class Fan implements Switchable {

    boolean isOn = false;

    public void on() {
        isOn = true;
        System.out.println("Fan is ON");
    }

    public void off() {
        isOn = false;
        System.out.println("Fan is OFF");
    }

    @Override
    public void toggle() {
        if (isOn)
            off();
        else
            on();
    }
}

class Light implements Switchable {

    boolean isOn = false;

    public void on() {
        isOn = true;
        System.out.println("Light is ON");
    }

    public void off() {
        isOn = false;
        System.out.println("Light is OFF");
    }

    @Override
    public void toggle() {
        if (isOn)
            off();
        else
            on();
    }
}

@FunctionalInterface
interface SwitchPermission {

    boolean maySwitchOn(Switchable device, int hour);
}

public class RemoteControl {

    public static void main(String[] args) {

       
        Switchable fan = new Fan();
        Switchable light = new Light();

        
        Switchable[] devices = { fan, light };

        System.out.println("---- TOGGLING DEVICES ----");

        for (Switchable device : devices) {
            device.toggle();
        }

        System.out.println();

     
        SwitchPermission permission1 = new SwitchPermission() {

            @Override
            public boolean maySwitchOn(Switchable device, int hour) {

                return hour >= 6 && hour <= 22;
            }
        };

        int hour = 10;

        System.out.println("---- ANONYMOUS CLASS ----");

        if (permission1.maySwitchOn(fan, hour)) {
            System.out.println("Fan is allowed to switch ON at " + hour + ":00");
        } else {
            System.out.println("Fan is NOT allowed to switch ON");
        }

        SwitchPermission permission2 =
                (device, h) -> h >= 6 && h <= 22;

        System.out.println();
        System.out.println("---- LAMBDA ----");

        if (permission2.maySwitchOn(light, 23)) {
            System.out.println("Light is allowed to switch ON");
        } else {
            System.out.println("Light is NOT allowed to switch ON at 23:00");
        }
    }
}