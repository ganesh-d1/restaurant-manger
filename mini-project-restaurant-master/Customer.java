import java.util.*;

public class Customer extends RestaurantOwner implements GST {
	
	HashMap<String, Integer> order;
	GST gst;

	Customer() {
		order = new HashMap<String, Integer>();
		gst = null;
	}

	void displayMenu() {
		super.displayMenu();
	}

	void displayOrder() {
		

		if (order.isEmpty()) {
			System.out.println("No items in order.");
			return;
		}

		Set<String> foodNames = new HashSet<String>();
		foodNames = order.keySet();
		System.out.println("-------------------------------------");
		System.out.println("FOOD \t QUANTITY \t PRICE \t TOTAL");
		System.out.println("-------------------------------------");
		for (String food : foodNames) {
			System.out.println(food + "\t" + order.get(food) + "\t" + menu.get(food) + "\t"
					+ menu.get(food) * order.get(food));
		}
		System.out.println("-------------------------------------\n");
	}

	boolean orderFood(String food, int quantity) {
		
		if (!super.menu.containsKey(food))
			return false;
		order.put(food, quantity);
		return true;

	}

	boolean removeFood(String food) {
		
		if (!order.containsKey(food))
			return false;
		else
			order.remove(food);
		return true;
	}

	boolean update(String food, int quantity) {
		
		try {
			removeFood(food);
			order.put(food, quantity);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	double totalBill() {
		/*
		 * calculate the total amount for the food items ordered including GST.
		 */
		double amount = 0;
		Set<String> foodNames = new HashSet<String>();
		foodNames = order.keySet();
		for (String food : foodNames) {
			amount += (super.menu.get(food) * order.get(food));
		}
		double tax = gst.GSTTaxPercent * amount / 100;
		return amount + tax;
	}
}
