class OutOfStockException extends Exception {
    int shortfall;

    OutOfStockException(int shortfall) {
        this.shortfall = shortfall;
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException() {
    }
}

class Warehouse {
    int stock;

    Warehouse(int stock) {
        this.stock = stock;
    }

    void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException();
        }

        if (qty > stock) {
            throw new OutOfStockException(qty - stock);
        }

        stock = stock - qty;

        System.out.println("Issued " + qty + " " + item);
    }
}

public class Main {

    public static void main(String[] args) {

        Warehouse w = new Warehouse(10);

        String[] items = {"Pen", "Book", "Pencil", "Bag"};
        int[] qty = {3, 12, -2, 5};

        for (int i = 0; i < items.length; i++) {

            try {
                w.issue(items[i], qty[i]);

            } catch (OutOfStockException e) {

                System.out.println(
                    "Out of stock. Shortfall: " + e.shortfall
                );

            } catch (InvalidQuantityException e) {

                System.out.println(
                    "Invalid quantity. Quantity must be greater than 0."
                );
            }
        }

        System.out.println("All requests processed.");
    }
}