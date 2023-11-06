package org.example.designpattern.factorypattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description the Factory pattern
 * `The Factory Method Pattern` defines an interface for creating an object, but lets subclasses decide which class to
 * instantiate. Factory Method lets a class defer instantiation to subclasses.
 * @Author VanessaYang
 * @Date: 2023/9/28 0028 15:14
 **/

public class PizzaTestDrive {

    public static void main(String[] args) {
        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        // Ethan's order: NYStylePizza
        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        // Joel's order: ChicagoStylePizza
        pizza = chicagoPizzaStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");
    }


}

/**
 * The Creator classes
 */
abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza;

        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract Pizza createPizza(String type);

}

class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String item) {
        if ("cheese".equals(item)) {
            return new NYStyleCheesePizza();
        } else if ("veggie".equals(item)) {
            return new NYStyleVeggiePizza();
        } else if ("clam".equals(item)) {
            return new NYStyleClamPizza();
        } else if ("pepperoni".equals(item)) {
            return new NYStylePepperoniPizza();
        }
        return null;
    }

    private class NYStyleCheesePizza extends Pizza {

        public NYStyleCheesePizza() {
            name = "NY Style Sauce and Cheese Pizza";
            dough = "Thin Crust Dough";
            sauce = "Marinara Sauce";

            toppings.add("Great Reggiano Cheese");
        }
    }

    private class NYStyleVeggiePizza extends Pizza {
        public NYStyleVeggiePizza() {
        }
    }

    private class NYStyleClamPizza extends Pizza {
        public NYStyleClamPizza() {
        }
    }

    private class NYStylePepperoniPizza extends Pizza {
        public NYStylePepperoniPizza() {
        }
    }
}

class ChicagoPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String item) {
        if ("cheese".equals(item)) {
            return new ChicagoStyleCheesePizza();
        } else if ("veggie".equals(item)) {
            return new ChicagoStyleVeggiePizza();
        } else if ("clam".equals(item)) {
            return new ChicagoStyleClamPizza();
        } else if ("pepperoni".equals(item)) {
            return new ChicagoStylePepperoniPizza();
        }
        return null;
    }

    private class ChicagoStyleCheesePizza extends Pizza {

        public ChicagoStyleCheesePizza() {
            name = "Chicago Style Deep Dish Cheese Pizza";
            dough = "Extra Thick Crust Dough";
            sauce = "Plum Tomato Sauce";

            toppings.add("Shredded Mozzarella Cheese");
        }

        @Override
        void cut() {
            System.out.println("Cutting the pizza into square slices");
        }
    }

    private class ChicagoStyleVeggiePizza extends Pizza {
        public ChicagoStyleVeggiePizza() {
        }
    }

    private class ChicagoStyleClamPizza extends Pizza {
        public ChicagoStyleClamPizza() {
        }
    }

    private class ChicagoStylePepperoniPizza extends Pizza {
        public ChicagoStylePepperoniPizza() {
        }
    }
}

class CaliforniaPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String item) {
        if ("cheese".equals(item)) {
            return new CaliforniaStyleCheesePizza();
        } else if ("veggie".equals(item)) {
            return new CaliforniaStyleVeggiePizza();
        } else if ("clam".equals(item)) {
            return new CaliforniaStyleClamPizza();
        } else if ("pepperoni".equals(item)) {
            return new CaliforniaStylePepperoniPizza();
        }
        return null;
    }

    private class CaliforniaStyleCheesePizza extends Pizza {

        public CaliforniaStyleCheesePizza() {
        }
    }

    private class CaliforniaStyleVeggiePizza extends Pizza {
        public CaliforniaStyleVeggiePizza() {
        }
    }

    private class CaliforniaStyleClamPizza extends Pizza {
        public CaliforniaStyleClamPizza() {
        }
    }

    private class CaliforniaStylePepperoniPizza extends Pizza {
        public CaliforniaStylePepperoniPizza() {
        }
    }
}

/**
 * The Product classes
 */
abstract class Pizza {
    String name;
    String dough;
    String sauce;
    List<String> toppings = new ArrayList<>();

    void prepare() {
        // Preparation follows a number of steps in a particular sequence.
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings: ");
        for (String topping : toppings) {
            System.out.println("    " + topping);
        }
    }

    // The abstract class provides some basic defaults for baking, cutting, and boxing.
    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    void box() {
        System.out.println("Place Pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }
}
