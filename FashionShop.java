import java.util.*;

class Orders {
	private String orderId;
	private String customerId;
	private String size;
	private int qty;
	private int status;
	private double amount;
	
	Orders () {
		
	}

	public Orders (String orderId, String customerId, String size, int qty, double amount) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.size = size;
		this.qty = qty;
		this.amount = amount;
	}
	
	public void setOrderId (String orderId) {
		this.orderId = orderId;
	}
	
	public void setCustomerId (String customerId) {
		this.customerId = customerId;
	}
	
	public void setSize (String size) {
		this.size = size;
	}
	
	public void setQty (int qty) {
		this.qty = qty;
	}
	
	public void setStatus (int status) {
		this.status = status;
	}
	
	public void setAmount (double amount) {
		this.amount = amount;
	}
	
	public String getOrderId ( ) {
		return orderId;
	}
	
	public String getCustomerId ( ) {
		return customerId;
	}
	
	public String getSize ( ) {
		return size;
	}
	
	public int getQty ( ) {
		return qty;
	}
	
	public int getStatus ( ) {
		return status;
	}
	
	public double getAmount ( ) {
		return amount;
	}
	
	public String getStatusName ( ) {
		switch (status) {
			case 0  : return "PROCESSING";
			case 1   : return "DELIVERING";
			case 2   : return "DELIVERED";
			default : return null;
		}
	}
	
	public String toString () {
		return "OrderId = "+orderId+", CustomerId = "+customerId+", Size"+size+", Amount"+amount+", Status"+getStatusName()+" }";
	}
}

class FashionShop {
	
	//--------------------------------------------------------Making Object Arrays----------------------------------------------------------

	public static Scanner input = new Scanner (System.in);
	
	public static Orders [ ] orders = new Orders [0];
	
	public static final int PROCESSING = 0;
	public static final int DELIVERING = 1;
	public static final int DELIVERED = 2;
	
	public static final int XS = 600;
	public static final int S = 800;
	public static final int M = 900;
	public static final int L = 1000;
	public static final int XL = 1100;
	public static final int XXL = 1200;
	
	//--------------------------------------------------------Generate Order ID------------------------------------------------------------

	public static String generateOrderId ( ) {
		if (orders.length > 0) {
			String lastOrderId = orders [orders.length - 1].getOrderId ( );
			int number = Integer.parseInt(lastOrderId.split ("[#]") [1]);
			number++;
			
			return String.format ("ODR#%05d",number);
		}else {
			return "ODR#00001";
		}
	}
	
	//----------------------------------------------------------CustomerId Validation--------------------------------------------------

	public static boolean validation (String customerId) {
		if (customerId.startsWith("0")){
			if (customerId.length() == 10){
				return true;
			}
		}
		return false;
	}

	//---------------------------------------------------------Extend Arrays-------------------------------------------------------------------
	
	public static void extendArrays ( ) {
		Orders [] temp = new Orders [orders.length + 1];
		
		for (int i = 0; i < orders.length; i++) {
			temp [i] = orders [i];
		}
		
		temp [temp.length -1] = new Orders ();
		
		orders = temp;
	}

	//----------------------------------------------------------Delete Orders---------------------------------------------------------------

	public static void deleteOrders() {
		String label = "\t\t\t\t  _____       _      _           ____          _               \r\n"+
		"\t\t\t\t |  __ \\     | |    | |         / __ \\        | |              \r\n"+
		"\t\t\t\t | |  | | ___| | ___| |_ ___   | |  | |_ __ __| | ___ _ __ ___ \r\n"+
		"\t\t\t\t | |  | |/ _ \\ |/ _ \\ __/ _ \\  | |  | | '__/ _` |/ _ \\ '__/ __|\r\n"+
		"\t\t\t\t | |__| |  __/ |  __/ ||  __/  | |__| | | | (_| |  __/ |  \\__ \\\r\n"+
		"\t\t\t\t |_____/ \\___|_|\\___|\\__\\___|   \\____/|_|  \\__,_|\\___|_|  |___/\r\n"+
		"\r\n"+
		"\t\t\t\t---------------------------------------------------------------\r\n"+
		"\r\n";
		System.out.println(label);
		
		L3 : do {
			System.out.print("\n\t\t\t\tEnter the order ID : ");
			String orderId = input.next();
			
			boolean isExist = true;
			for (int i = 0; i < orders.length; i++) {
				if (orderId.equals(orders[i].getOrderId())) {
					isExist = false;
					double amount = orders[i].getSize().equals("XS") ? XS*orders[i].getQty() : orders[i].getSize().equals("S") ? S*orders[i].getQty() : orders[i].getSize().equals("M") ? M*orders[i].getQty() : orders[i].getSize().equals("L") ? L*orders[i].getQty() : orders[i].getSize().equals("XL") ?XL*orders[i].getQty() : XXL*orders[i].getQty();
				
					System.out.println("\n\t\t\t\tCustomer ID : "+orders[i].getCustomerId());
					System.out.println("\t\t\t\tOrder size : "+orders[i].getSize());
					System.out.println("\t\t\t\tOrder quantity : "+orders[i].getQty());
					System.out.println("\t\t\t\tOrder amount : "+amount);
					System.out.println("\t\t\t\tOrder status : "+orders[i].getStatusName());
				
					System.out.print("\n\t\t\t\tDo you want to delete this order ? (Yes/No) : ");
					String choice = input.next();
					
					if (choice.equalsIgnoreCase("Yes")) {
						Orders [] temp = new Orders [orders.length - 1];
						
						int k = 0;
						for (int j = 0; j < orders.length; j++) {
							if (i==j) {
								continue;
							}
							temp[k] = orders[j];
							k++;
						}
					}
					
					System.out.println("\n\t\t\t\tContact deleted successfully !");
					System.out.print("\n\t\t\t\tDo you want to delete another order ? (Yes/No) : ");
					String choose = input.next();
					if (choose.equalsIgnoreCase("Yes")) {
						continue L3;
					} else if (choose.equalsIgnoreCase("No")) {
						clearConsole(); homePage();
					}
				} else {
					System.out.println("\n\t\t\t\tOrder Not Found!");
					System.out.print("\n\t\t\t\tDo you want to search another order ? (Yes/No) : ");
					String choose = input.next();
					if (choose.equalsIgnoreCase("Yes")) {
						continue L3;
					} else if (choose.equalsIgnoreCase("No")) {
						clearConsole();
						homePage();
					}
				}
			}
		} while (true);
	}

	//----------------------------------------------------------Change Status--------------------------------------------------------------

	public static void changeStatus() {
		String label = "\t\t\t\t   _____ _                               _____ _        _             \r\n"+
		"\t\t\t\t  / ____| |                             / ____| |      | |            \r\n"+
		"\t\t\t\t | |    | |__   __ _ _ __   __ _  ___  | (___ | |_ __ _| |_ _   _ ___ \r\n"+
		"\t\t\t\t | |    | '_ \\ / _` | '_ \\ / _` |/ _ \\  \\___ \\| __/ _` | __| | | / __|\r\n"+
		"\t\t\t\t | |____| | | | (_| | | | | (_| |  __/  ____) | || (_| | |_| |_| \\__ \\\r\n"+
		"\t\t\t\t  \\_____|_| |_|\\__,_|_| |_|\\__, |\\___| |_____/ \\__\\__,_|\\__|\\__,_|___/\r\n"+
		"\t\t\t\t                            __/ |                                     \r\n"+
		"\t\t\t\t                           |___/                                      \r\n"+
		"\r\n"+
		"\t\t\t\t----------------------------------------------------------------------\r\n"+
		"\r\n";
		System.out.println(label);
		
		L1 : do {
			System.out.print("\n\t\t\t\tEnter Customer ID : ");
			String customerId = input.next();
			
			if (validation (customerId)) {
				System.out.println("\n\t\t\t\tCustomer ID : "+customerId);
				System.out.println("\n\t\t\t\tCustomer Order Details");
				for (int i=0; i < orders.length; i++) {
					if (customerId.equals(orders[i].getCustomerId())) {
						double amount = orders[i].getSize().equals("XS") ? XS*orders[i].getQty() : orders[i].getSize().equals("S") ? S*orders[i].getQty() : orders[i].getSize().equals("M") ? M*orders[i].getQty() : orders[i].getSize().equals("L") ? L*orders[i].getQty() : orders[i].getSize().equals("XL") ? XL*orders[i].getQty() : XXL*orders[i].getQty();
						
						System.out.println("\n\t\t\t\tPhone Number : "+orders[i].getCustomerId());
						System.out.println("\t\t\t\tSize : "+orders[i].getSize());
						System.out.println("\t\t\t\tQuantity : "+orders[i].getQty());
						System.out.println("\t\t\t\tAmount : "+amount);
						System.out.println("\t\t\t\tStatus : "+orders[i].getStatusName());
					}
				}
			} else {
				System.out.println("\n\t\t\t\tInvalid order ID!\n\t\t\t\tPltese enter a correct ID...");
				continue L1;
			}
			System.out.print("\n\t\t\t\tDo you want to change this order status ? (Yes/No) : ");
			String choice = input.next();
			if (choice.equalsIgnoreCase("Yes")) {
				System.out.print("\n\t\t\t\tPlease press [0] to PROCESSING\n\t\t\t\t\t     [1] to DELIVERING\n\t\t\t\t\t     [2] to DELIVERED\n\n\t\t\t\tChoose an option to change : ");
				int option = input.nextInt();
				
				orders[orders.length - 1].setStatus(option);
				System.out.println("\n\t\t\t\tSuccessfully changed!");
				
				System.out.print("\n\t\t\t\tDo you want to change another one ? (Yes/No) : ");
				String yn = input.next();
				
				if (yn.equalsIgnoreCase("Yes")) {
					continue L1;
				}else if (yn.equalsIgnoreCase("No")) {
					clearConsole(); homePage();
				}
			} else if (choice.equalsIgnoreCase("No")) {
				clearConsole(); homePage();
			}
		} while (true);
	}
	
	//---------------------------------------------------------Customer Reports------------------------------------------------------------
	
	public static void customerReports() {
		String label = "\t\t\t\t   _____          _                              _____                       _       \r\n"+
		"\t\t\t\t  / ____|        | |                            |  __ \\                     | |      \r\n"+
		"\t\t\t\t | |    _   _ ___| |_ ___  _ __ ___   ___ _ __  | |__) |___ _ __   ___  _ __| |_ ___ \r\n"+
		"\t\t\t\t | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__| |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|\r\n"+
		"\t\t\t\t | |___| |_| \\__ \\ || (_) | | | | | |  __/ |    | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\\r\n"+
		"\t\t\t\t  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|    |_|  \\_\\___| .__/ \\___/|_|   \\__|___/\r\n"+
		"\t\t\t\t                                                           | |                       \r\n"+
		"\t\t\t\t                                                           |_|                       \r\n"+
		"\r\n"+
		"\t\t\t\t-------------------------------------------------------------------------------------\r\n"+
		"\r\n";
		
		System.out.println(label);
		System.out.println("\n\t\t\t\t[1] Best Customers");
		System.out.println("\n\t\t\t\t[2] View Customers");
		System.out.println("\n\t\t\t\t[3] All Customer Details");
		
		System.out.print("\n\n\n\t\t\t\tEnter an option to view : "); 
		int options = input.nextInt();
		switch (options) {
			case 1 : clearConsole(); bestCustomers(); break;
			case 2 : clearConsole(); viewCustomers(); break;
			case 3 : clearConsole(); allCustomerDetails(); break;
			default : System.out.println(); System.out.println("\n\t\t\t\tInvalid option entered !\n\t\t\t\tPlease enter a correct option to view...");
		}
	}

	//-----------------------------------------------------------Best Customers------------------------------------------------------------

	public static void bestCustomers() {
		String label = "\t\t\t\t  ____            _      _____          _                                \r\n"+
		"\t\t\t\t |  _ \\          | |    / ____|        | |                               \r\n"+
		"\t\t\t\t | |_) | ___  ___| |_  | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___ \r\n"+
		"\t\t\t\t |  _ < / _ \\/ __| __| | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__/ __|\r\n"+
		"\t\t\t\t | |_) |  __/\\__ \\ |_  | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\\r\n"+
		"\t\t\t\t |____/ \\___||___/\\__|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/\r\n"+
		"\r\n"+
		"\t\t\t\t--------------------------------------------------------------------------\r\n"+
		"\r\n";
		
		System.out.println("\n\t\t\t\t+---------------------------------------------------------------+");
        System.out.println("\t\t\t\t|\tSize\t|\tQuantity\t|\tTotal Amount\t|");
        System.out.println("\t\t\t\t+---------------------------------------------------------------+");
		
        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; j < orders.length - i -1; j++) {
                if (orders[j].getQty() > orders[j+1].getQty()) {
					Orders temp = orders[j];
                    orders[j] = orders[j+1];
                    orders[j+1] = temp;
                }
            }
			System.out.println("\t\t\t\t|\t"+orders[i].getSize() +"\t|\t\t"+orders[i].getQty()+"\t|\t\t"+orders[i].getAmount() +"\t|");
        }
       
        System.out.println("\t\t\t\t+---------------------------------------------------------------+");
        System.out.print("\n\n\n\t\t\t\tTo access the main Menu press 0 : ");
		int y = input.nextInt();
		switch (y) {
			case 0 : clearConsole(); homePage(); break;
		}
	}
	
	//-----------------------------------------------------------View Customers------------------------------------------------------------
	
	public static void viewCustomers() {
		String label = "\t\t\t\t __      ___                   _____          _                                \r\n"+
		"\t\t\t\t \\ \\    / (_)                 / ____|        | |                               \r\n"+
		"\t\t\t\t  \\ \\  / / _  _____      __  | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___ \r\n"+
		"\t\t\t\t   \\ \\/ / | |/ _ \\ \\ /\\ / /  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__/ __|\r\n"+
		"\t\t\t\t    \\  /  | |  __/\\ V  V /   | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\\r\n"+
		"\t\t\t\t     \\/   |_|\\___| \\_/\\_/     \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/"+
		"\r\n"+
		"\t\t\t\t    ---------------------------------------------------------------------------\r\n"+
		"\r\n";
		
		System.out.println(label);
		System.out.println("\n\t\t\t\t+-----------------------------------------------------------------------+");
		System.out.println("\t\t\t\t|\tCustomer ID\t|\tALL QTY\t\t|\tTotal Amount\t|");
		System.out.println("\t\t\t\t+-----------------------------------------------------------------------+");
		
		for (int i = 0; i < orders.length; i++) {
			System.out.println("\t\t\t\t|\t"+orders[i].getCustomerId() +"\t|\t\t"+orders[i].getQty()+"\t|\t\t"+orders[i].getAmount() +"\t|");
		}
		System.out.println("\t\t\t\t+-----------------------------------------------------------------------+");
		System.out.print("\n\n\n\t\t\t\tTo access the main Menu press 0 : ");
		int y = input.nextInt();
		switch (y) {
			case 0 : clearConsole(); homePage(); break;
		}
	}
	
	//-----------------------------------------------------------All Customer Details-------------------------------------------------------
	
	public static void allCustomerDetails() {
		String label = "\t\t\t           _ _    _____          _                              _____       _        _ _     \r\n"+
		"\t\t\t     /\\   | | |  / ____|        | |                            |  __ \\     | |      (_) |    \r\n"+
		"\t\t\t    /  \\  | | | | |    _   _ ___| |_ ___  _ __ ___   ___ _ __  | |  | | ___| |_ __ _ _| |___ \r\n"+
		"\t\t\t   / /\\ \\ | | | | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__| | |  | |/ _ \\ __/ _` | | / __|\r\n"+
		"\t\t\t  / ____ \\| | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |    | |__| |  __/ || (_| | | \\__ \\\r\n"+
		"\t\t\t /_/    \\_\\_|_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|    |_____/ \\___|\\__\\__,_|_|_|___/\r\n"+
		"\r\n"+
		"\t\t\t---------------------------------------------------------------------------------------------\r\n"+
		"\r\n";
		
		System.out.println(label);
		System.out.println("\n\t\t\t+-------------------------------------------------------------------------------+");
		System.out.println("\t\t\t|\tPhone Number\t|  XS  |  S  |  M  |  L  |  XL  |  XXL  |\tTotal\t|");
		System.out.println("\t\t\t+-------------------------------------------------------------------------------+");
		
		int [] sizeCounts = new int [6];
		int total = 0;
		for (int i = 0; i < orders.length; i++) {
		String size = orders[i].getSize();
			if (size.equals("XL")) {
				sizeCounts[0] = orders[i].getQty();
			} else if (size.equals("S")) {
				sizeCounts[1] = orders[i].getQty();
			} else if (size.equals("M")) {
				sizeCounts[2] = orders[i].getQty();
			} else if (size.equals("L")) {
				sizeCounts[3] = orders[i].getQty();
			} else if (size.equals("XL")) {
				sizeCounts[4] = orders[i].getQty();
			} else if (size.equals("XXL")) {
				sizeCounts[5] = orders[i].getQty();
			}
			total  = sizeCounts[0]+sizeCounts[1]+sizeCounts[2]+sizeCounts[3]+sizeCounts[4]+sizeCounts[5];
			System.out.println("\t\t\t|\t"+orders[i].getCustomerId()+"\t|   "+sizeCounts[0]+"  |  "+sizeCounts[1]+"  |  "+sizeCounts[2]+"  |  "+sizeCounts[3]+"  |   "+sizeCounts[4]+"  |    "+sizeCounts[5]+"  |\t"+total+"\t|");
		}
        System.out.println("\t\t\t+-------------------------------------------------------------------------------+");
        System.out.print("\n\n\n\t\tTo access the main Menu press 0 : ");
		int y = input.nextInt();
		switch (y) {
			case 0 : clearConsole(); homePage(); break;
		}
	}
	
	//---------------------------------------------------------Item Reports----------------------------------------------------------------
	
	public static void itemReports() {
		String label = "\t\t\t\t  _____ _                    _____                       _       \r\n"+
		"\t\t\t\t |_   _| |                  |  __ \\                     | |      \r\n"+
		"\t\t\t\t   | | | |_ ___ _ __ ___    | |__) |___ _ __   ___  _ __| |_ ___ \r\n"+
		"\t\t\t\t   | | | __/ _ \\ '_ ` _ \\   |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|\r\n"+
		"\t\t\t\t  _| |_| ||  __/ | | | | |  | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\\r\n"+
		"\t\t\t\t |_____|\\__\\___|_| |_| |_|  |_|  \\_\\___| .__/ \\___/|_|   \\__|___/\r\n"+
		"\t\t\t\t                                       | |                       \r\n"+
		"\t\t\t\t                                       |_|                       \r\n"+
		"\r\n"+
		"\t\t\t\t-------------------------------------------------------------------\r\n"+
		"\r\n";
		
		System.out.println(label);
		System.out.println("\n\t\t\t\t[1] Best items by Quantity\t\t\t\t[2] Best items by Amount");
		
		System.out.print("\n\n\t\t\t\tEnter an option to view : ");
		int options = input.nextInt();
		switch (options) {
			case 1 : clearConsole(); bestItemByQTY(); break;
			case 2 : clearConsole(); bestItemByAmount(); break;
			default : System.out.println(); System.out.println("\n\t\t\t\tInvalid option entered !\n\t\t\t\tPlease enter a correct option to view...");
		}
	}

	//---------------------------------------------------------Best Item By QTY-----------------------------------------------------------
	
	public static void bestItemByQTY() {
		String label = "\t\t\t\t  ___         _     ___ _               ___         ___                _   _ _        \r\n"+
		"\t\t\t\t | _ ) ___ __| |_  |_ _| |_ ___ _ __   | _ )_  _   / _ \\ _  _ __ _ _ _| |_(_) |_ _  _ \r\n"+
		"\t\t\t\t | _ \\/ -_|_-<  _|  | ||  _/ -_) '  \\  | _ \\ || | | (_) | || / _` | ' \\  _| |  _| || |\r\n"+
		"\t\t\t\t |___/\\___/__/\\__| |___|\\__\\___|_|_|_| |___/\\_, |  \\__\\_\\_,_\\__,_|_||_\\__|_|\\__|\\_, |\r\n"+
		"\t\t\t\t                                            |__/                                 |__/\r\n"+
		"\r\n"+
		"\t\t\t\t--------------------------------------------------------------------------------------\r\n"+
		"\r\n";
		
		System.out.println(label);
		System.out.println("\n\t\t\t\t+---------------------------------------------------------------+");
        System.out.println("\t\t\t\t|\tSize\t|\tQuantity\t|\tTotal Amount\t|");
        System.out.println("\t\t\t\t+---------------------------------------------------------------+");
		
        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; j < orders.length - i -1; j++) {
                if (orders[j].getQty() > orders[j+1].getQty()) {
					Orders temp = orders[j];
                    orders[j] = orders[j+1];
                    orders[j+1] = temp;
                }
            }
			System.out.println("\t\t\t\t|\t"+orders[i].getSize() +"\t|\t\t"+orders[i].getQty()+"\t|\t\t"+orders[i].getAmount() +"\t|");
        }
       
        System.out.println("\t\t\t\t+---------------------------------------------------------------+");
        System.out.print("\n\n\n\t\t\t\tTo access the main Menu press 0 : ");
		int y = input.nextInt();
		switch (y) {
			case 0 : clearConsole(); homePage(); break;
		}
	}
	
	//---------------------------------------------------------Best Item By Amount--------------------------------------------------------

	public static void bestItemByAmount() {
		String label = "\t\t\t\t  ___         _     ___ _               ___          _                      _   \r\n"+
		"\t\t\t\t | _ ) ___ __| |_  |_ _| |_ ___ _ __   | _ )_  _    /_\\  _ __  ___ _  _ _ _| |_ \r\n"+
		"\t\t\t\t | _ \\/ -_|_-<  _|  | ||  _/ -_) '  \\  | _ \\ || |  / _ \\| '  \\/ _ \\ || | ' \\  _|\r\n"+
		"\t\t\t\t |___/\\___/__/\\__| |___|\\__\\___|_|_|_| |___/\\_, | /_/ \\_\\_|_|_\\___/\\_,_|_||_\\__|\r\n"+
		"\t\t\t\t                                            |__/                                \r\n"+
		"\r\n"+
		"\t\t\t\t---------------------------------------------------------------------------------\r\n"+
		"\r\n";
		
		System.out.println(label);
		System.out.println("\n\t\t\t\t+---------------------------------------------------------------+");
        System.out.println("\t\t\t\t|\tSize\t|\tQuantity\t|\tTotal Amount\t|");
        System.out.println("\t\t\t\t+---------------------------------------------------------------+");

        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; j < orders.length - i -1; j++) {
                if (orders[j].getAmount() > orders[j+1].getAmount()) {
					Orders temp = orders[j];
					orders[j] = orders[j + 1];
					orders[j + 1] = temp;
                }
            }
			System.out.println("\t\t\t\t|\t"+orders[i].getSize() +"\t|\t\t"+orders[i].getQty()+"\t|\t\t"+orders[i].getAmount() +"\t|");
        }
        System.out.println("\t\t\t\t+---------------------------------------------------------------+");
        System.out.print("\n\n\n\t\t\t\tTo access the main Menu press 0 : ");
		int y = input.nextInt();
		switch (y) {
			case 0 : clearConsole(); homePage(); break;
		}
	}

	//----------------------------------------------------------Order Reports---------------------------------------------------------------
	
	public static void orderReports() {
		String label = "\t\t\t\t   ____          _              _____                       _       \r\n"+
		"\t\t\t\t  / __ \\        | |            |  __ \\                     | |      \r\n"+
		"\t\t\t\t | |  | |_ __ __| | ___ _ __   | |__) |___ _ __   ___  _ __| |_ ___ \r\n"+
		"\t\t\t\t | |  | | '__/ _` |/ _ \\ '__|  |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|\r\n"+
		"\t\t\t\t | |__| | | | (_| |  __/ |     | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\\r\n"+
		"\t\t\t\t  \\____/|_|  \\__,_|\\___|_|     |_|  \\_\\___| .__/ \\___/|_|   \\__|___/\r\n"+
		"\t\t\t\t                                          | |                       \r\n"+
		"\t\t\t\t                                          |_|                       \r\n"+
		"\r\n"+
		"\t\t\t\t--------------------------------------------------------------------------\r\n"+
		"\r\n";
		
		System.out.println(label);
		System.out.println("\n\t\t\t\t[1] All Orders\t\t\t\t[2] Orders by Amount");
		System.out.print("\n\n\t\t\t\tEnter an option to view : ");
		int options = input.nextInt();
		
		switch (options) {
			case 1 : clearConsole(); allOrders(); break;
			case 2 : clearConsole(); ordersByAmount(); break;
			default : System.out.println(); System.out.println("\n\t\t\t\tInvalid option entered !\n\t\t\t\tPlease enter a correct option to view...");
		}
	}

	//----------------------------------------------------------All Orders-------------------------------------------------------------------
	
	public static void allOrders() {
		String label = "\t\t\t\t\t           _ _     ____          _               \r\n"+
		"\t\t\t\t\t     /\\   | | |   / __ \\        | |              \r\n"+
		"\t\t\t\t\t    /  \\  | | |  | |  | |_ __ __| | ___ _ __ ___ \r\n"+
		"\t\t\t\t\t   / /\\ \\ | | |  | |  | | '__/ _` |/ _ \\ '__/ __|\r\n"+
		"\t\t\t\t\t  / ____ \\| | |  | |__| | | | (_| |  __/ |  \\__ \\\r\n"+
		"\t\t\t\t\t /_/    \\_\\_|_|   \\____/|_|  \\__,_|\\___|_|  |___/\r\n"+
		"\r\n"+
		"\t\t\t\t\t-----------------------------------------------------\r\n"+
		"\r\n";
		
		System.out.println(label);
		System.out.println("\n\t\t+-----------------------+-----------------------+---------------+---------+-------------+---------------+");
		System.out.println("\t\t|\tOrder ID\t|\tCustomer ID\t|\tSize\t|   QTY   |\tAmount\t|\tStatus\t|");
		System.out.println("\t\t+-----------------------+-----------------------+---------------+---------+-------------+---------------+");
		
		for (int i = 0; i < orders.length; i++) {
			System.out.println("\t\t|\t"+orders[i].getOrderId()+"\t|\t"+orders[i].getCustomerId()+"\t|\t"+orders[i].getSize()+"\t|    "+orders[i].getQty()+"    |   "+orders[i].getAmount()+"    |   "+orders[i].getStatusName()+"  |");
		}
		System.out.println("\t\t+-----------------------+-----------------------+---------------+---------+-------------+---------------+");
		System.out.print("\n\n\n\t\tTo access the main Menu press 0 : ");
		int y = input.nextInt();
		switch (y) {
			case 0 : clearConsole(); homePage(); break;
		}
	}

	//---------------------------------------------------------Orders By Amount-----------------------------------------------------------
	
	public static void ordersByAmount() {
		String label = "\t\t\t\t    ___         _              _             _                      _   \r\n"+
		"\t\t\t\t   / _ \\ _ _ __| |___ _ _ ___ | |__ _  _    /_\\  _ __  ___ _  _ _ _| |_ \r\n"+
		"\t\t\t\t  | (_) | '_/ _` / -_) '_(_-< | '_ \\ || |  / _ \\| '  \\/ _ \\ || | ' \\  _|\r\n"+
		"\t\t\t\t   \\___/|_| \\__,_\\___|_| /__/ |_.__/\\_, | /_/ \\_\\_|_|_\\___/\\_,_|_||_\\__|\r\n\r\n"+
		"\t\t\t\t                                    |__/                                \r\n"+
		"\r\n"+
		"\t\t\t\t ---------------------------------------------------------------------\r\n"+
		"\r\n";
		
		System.out.println(label);
		System.out.println("\n\t\t\t\t+---------------------------------------------------------------+");
        System.out.println("\t\t\t\t|\tSize\t|\tQuantity\t|\tTotal Amount\t|");
        System.out.println("\t\t\t\t+---------------------------------------------------------------+");
		
        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; j < orders.length - i -1; j++) {
                if (orders[j].getQty() > orders[j+1].getQty()) {
					Orders temp = orders[j];
                    orders[j] = orders[j+1];
                    orders[j+1] = temp;
                }
            }
			System.out.println("\t\t\t\t|\t"+orders[i].getSize() +"\t|\t\t"+orders[i].getQty()+"\t|\t\t"+orders[i].getAmount() +"\t|");
        }
       
        System.out.println("\t\t\t\t+---------------------------------------------------------------+");
        System.out.print("\n\n\n\t\t\t\tTo access the main Menu press 0 : ");
		int y = input.nextInt();
		switch (y) {
			case 0 : clearConsole(); homePage(); break;
		}
	}
	
	//----------------------------------------------------------View Reports---------------------------------------------------------------
	
	public static void viewReports() {
		String label = "\t\t\t\t __      ___                  _____                       _       \r\n"+
		"\t\t\t\t \\ \\    / (_)                |  __ \\                     | |      \r\n"+
		"\t\t\t\t  \\ \\  / / _  _____      __  | |__) |___ _ __   ___  _ __| |_ ___ \r\n"+
		"\t\t\t\t   \\ \\/ / | |/ _ \\ \\ /\\ / /  |  _  // _ \\ '_ \\ / _ \\| '__| __/ __|\r\n"+
		"\t\t\t\t    \\  /  | |  __/\\ V  V /   | | \\ \\  __/ |_) | (_) | |  | |_\\__ \\\r\n"+
		"\t\t\t\t     \\/   |_|\\___| \\_/\\_/    |_|  \\_\\___| .__/ \\___/|_|   \\__|___/\r\n"+
		"\t\t\t\t                                        | |                       \r\n"+
		"\t\t\t\t                                        |_|                       \r\n"+
		"\r\n"+
		"\t\t\t\t-----------------------------------------------------------------------\r\n";
		System.out.println(label);
		
		System.out.println("\n\t\t\t\t[1] Customer Reports");
		System.out.println("\n\t\t\t\t[2] Item Reports");
		System.out.println("\n\t\t\t\t[3] Order Reports");
		
		System.out.print("\n\n\n\t\t\t\tEnter an option to view : "); 
		int options = input.nextInt();
		switch (options) {
			case 1 : clearConsole(); customerReports(); break;
			case 2 : clearConsole(); itemReports(); break;
			case 3 : clearConsole(); orderReports(); break;
			default : System.out.println(); System.out.println("\n\t\t\t\tInvalid option entered !\n\t\t\t\tPlease enter a correct option to view...");
		}
	}

	//----------------------------------------------------------Search Orders--------------------------------------------------------------
	
	public static void searchOrders() {
		String label =  "\t\t\t\t   _____                     _         ____          _               \r\n"+
		"\t\t\t\t  / ____|                   | |       / __ \\        | |              \r\n"+
		"\t\t\t\t | (___   ___  __ _ _ __ ___| |__    | |  | |_ __ __| | ___ _ __ ___ \r\n"+
		"\t\t\t\t  \\___ \\ / _ \\/ _` | '__/ __| '_ \\   | |  | | '__/ _` |/ _ \\ '__/ __|\r\n"+
		"\t\t\t\t  ____) |  __/ (_| | | | (__| | | |  | |__| | | | (_| |  __/ |  \\__ \\\r\n"+
		"\t\t\t\t |_____/ \\___|\\__,_|_|  \\___|_| |_|   \\____/|_|  \\__,_|\\___|_|  |___/\r\n"+
		"\r\n"+
		"\t\t\t\t----------------------------------------------------------------------\r\n"+
		"\r\n";
		System.out.println(label);
		
		L1 : do {
			System.out.print("\n\t\t\t\tEnter Order ID : ");
			String orderId = input.next();
			
			for (int i=0; i < orders.length; i++) {
				if (orderId.equals(orders[i].getOrderId())) {
					double amount = orders[i].getSize().equals("XS") ? XS*orders[i].getQty() : orders[i].getSize().equals("S") ? S*orders[i].getQty() : orders[i].getSize().equals("M") ? M*orders[i].getQty() : orders[i].getSize().equals("L") ? L*orders[i].getQty() : orders[i].getSize().equals("XL") ? XL*orders[i].getQty() : XXL*orders[i].getQty();
							
					System.out.println("\n\t\t\t\tCustomer ID : "+orders[i].getCustomerId());
					System.out.println("\n\t\t\t\tSize : "+orders[i].getSize());
					System.out.println("\n\t\t\t\tQuantity : "+orders[i].getQty());
					System.out.println("\n\t\t\t\tAmount : "+amount);
					System.out.println("\n\t\t\t\tStatus : "+orders[i].getStatusName());
							
					System.out.print("\n\t\t\t\tDo you want to search another order ? (Yes/No) : ");
					String yn = input.next();
						
					if (yn.equalsIgnoreCase("Yes")) {
							continue L1;
					}else if (yn.equalsIgnoreCase("No")) {
						clearConsole(); homePage();
					}
				}else {
					System.out.println("\n\t\t\t\tInvalid Order ID!");
					System.out.print("\t\t\t\tDo you want to another order ? (Yes/No) : ");
					String yn = input.next();
						
					if (yn.equalsIgnoreCase("Yes")) {
						continue L1;
					}else if (yn.equalsIgnoreCase("No")) {
						clearConsole(); homePage();
					}
				}
			}
		} while (true);	
	}

	//----------------------------------------------------------Search Customers----------------------------------------------------------
	
	public static void searchCustomers() {
		String label = "\t\t\t   _____                     _         _____          _                                \r\n"+
		"\t\t\t  / ____|                   | |       / ____|        | |                               \r\n"+
		"\t\t\t | (___   ___  __ _ _ __ ___| |__    | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___ \r\n"+
		"\t\t\t  \\___ \\ / _ \\/ _` | '__/ __| '_ \\   | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__/ __|\r\n"+
		"\t\t\t  ____) |  __/ (_| | | | (__| | | |  | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\\r\n"+
		"\t\t\t |_____/ \\___|\\__,_|_|  \\___|_| |_|   \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/\r\n"+
		"\r\n"+
		"\t\t\t----------------------------------------------------------------------------------------\r\n"+
		"\r\n";
		System.out.println(label);
		L2 : do {
			System.out.print("\n\t\t\tEnter Customer ID : ");
			String customerId = input.next();
		
			if (validation (customerId)) {
				System.out.println("\n\t\t\tCustomer ID : "+customerId);
				System.out.println("\n\t\t\tCustomer Order Details");
				System.out.println("\n\t\t\t+---------------+-----------------------+-----------------------+");
				System.out.println("\t\t\t|\tSize\t|\tQuantity\t|\tAmount\t\t|");
				System.out.println("\t\t\t+---------------+-----------------------+-----------------------+");
				
				for (int i = 0; i < orders.length; i++) {
					if (customerId.equals(orders[i].getCustomerId())) {
						String size = orders[i].getSize();
						int qty = orders[i].getQty();
						double amount = orders[i].getSize().equals("XS") ? XS*orders[i].getQty() : orders[i].getSize().equals("S") ? S*orders[i].getQty() : orders[i].getSize().equals("M") ? M*orders[i].getQty() : orders[i].getSize().equals("L") ? L*orders[i].getQty() : orders[i].getSize().equals("XL") ? XL*orders[i].getQty() : XXL*orders[i].getQty();
						System.out.println("\t\t\t|\t"+size+"\t|\t   "+qty+"\t\t|\t"+amount+"\t\t|");
					}
				}
				System.out.println("\t\t\t+---------------+-----------------------+-----------------------+");
			} else {
				System.out.println("\n\t\t\tInvalid customer ID!\n\t\t\tPlease try again...");
			}
			System.out.print("\n\t\t\tDo you want to search another customer ? (Yes/No) : ");
			String choice = input.next();
			if (choice.equalsIgnoreCase("Yes")) {
				continue L2;
			} else if ( choice.equalsIgnoreCase("No")) {
				clearConsole();
				homePage();
			}
		} while (true);
	}

	//----------------------------------------------------------Place Orders----------------------------------------------------------------
	
	public static void placeOrders ( ) {
		String label = "\t\t\t\t  _____  _                    ____          _              \r\n"+
		"\t\t\t\t |  __ \\| |                  / __ \\        | |              \r\n"+
		"\t\t\t\t | |__) | | __ _  ___ ___   | |  | |_ __ __| | ___ _ __ ___ \r\n"+
		"\t\t\t\t |  ___/| |/ _` |/ __/ _ \\  | |  | | '__/ _` |/ _ \\ '__/ __|\r\n"+
		"\t\t\t\t | |    | | (_| | (_|  __/  | |__| | | | (_| |  __/ |  \\__ \\\r\n"+
		"\t\t\t\t |_|    |_|\\__,_|\\___\\___|   \\____/|_|  \\__,_|\\___|_|  |___/\r\n"+
		"\n\n\r\n"+
		"\t\t\t\t------------------------------------------------------------";
		System.out.println (label);
		
		String orderId = generateOrderId ( );
		System.out.println ("\n\t\t\t\tYour order ID is "+orderId);
		
		System.out.print ("\n\t\t\t\tEnter Customer Phone : ");
		String customerId = input.next ( );
		if (validation (customerId)) {
			
			L1 : do {
				System.out.print ("\n\t\t\t\tEnter T-Shirt Size (XS/S/M/L/XL/XXL) : ");
				String size = input.next ( ).toUpperCase ( );
				
				System.out.print ("\n\t\t\t\tEnter QTY : ");
				int qty = input.nextInt ( );
				
				double amount = size.equals ("XS") ? XS * qty : size.equals ("S") ? S * qty : size.equals ("M") ? M * qty : size.equals ("L") ? L * qty : size.equals ("XL") ? XL * qty : XXL * qty;
				System.out.print ("\n\t\t\t\tYour Ordered Amount is LKR "+amount);
				
				System.out.print ("\n\n\t\t\t\tDo you want to place this order ? (Yes/No) : ");
				String yn = input.next ( );
				
				if (yn.equalsIgnoreCase("Yes")) {
					extendArrays();
					
					orders[orders.length - 1].setOrderId(orderId);
					orders[orders.length - 1].setCustomerId(customerId);
					orders[orders.length - 1].setSize(size);
					orders[orders.length - 1].setQty(qty);
					orders[orders.length - 1].setAmount(amount);
					
					System.out.println ("\n\t\t\t\tYour order is placed!");
				} else if (yn.equalsIgnoreCase ("No")) {
					clearConsole ( ); homePage ( );
				}
				System.out.print ("\n\n\t\t\t\tDo you want to place another order ? (Yes/No) : ");
				String choice = input.next ( );
				if (choice.equalsIgnoreCase ("Yes")) {
					clearConsole ( ); placeOrders ( );
				} else if (choice.equalsIgnoreCase ("No")) {
					clearConsole(); homePage ();
				}
			} while (true);
		} else {
			System.out.println("\n\t\t\t\tYour phone number is invalid!\n\t\t\t\tPlease enter a valid number...");
			System.out.print("\n\t\t\t\tDo you want to continue ? (Yes/No) : ");
			String choice = input.next();
			if (choice.equalsIgnoreCase("Yes")) {
				clearConsole(); placeOrders();
			} else if (choice.equalsIgnoreCase("No")) {
				clearConsole(); homePage();
			}
		}
	}
	
	//-----------------------------------------------------------Clear Console--------------------------------------------------------------
	
	public final static void clearConsole() { 
        try {
			final String os = System.getProperty("os.name"); 
			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}else{
				System.out.print("\033[H\033[2J"); 
				System.out.flush();
			}
		}catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	//-------------------------------------------------------------------Exit-----------------------------------------------------------------------------
	
	public static void exit(){
		System.out.println();
		System.out.println("\t\tThank you for shopping with us...");
		System.out.println("\t\tWe hope to see you again soon!");
		System.exit(0);
	}
	
	//-----------------------------------------------------------Home Page----------------------------------------------------------------
	
	public static void homePage() {
		String label = "\t\t /$$$$$$$$                 /$$       /$$                            /$$$$$$  /$$                          \r\n"+
		"\t\t| $$_____/                | $$      |__/                           /$$__  $$| $$                          \r\n"+
		"\t\t| $$    /$$$$$$   /$$$$$$$| $$$$$$$  /$$  /$$$$$$  /$$$$$$$       | $$  \\__/| $$$$$$$   /$$$$$$   /$$$$$$ \r\n"+
		"\t\t| $$$$$|____  $$ /$$_____/| $$__  $$| $$ /$$__  $$| $$__  $$      |  $$$$$$ | $$__  $$ /$$__  $$ /$$__  $$\r\n"+
		"\t\t| $$__/ /$$$$$$$|  $$$$$$ | $$  \\ $$| $$| $$  \\ $$| $$  \\ $$       \\____  $$| $$  \\ $$| $$  \\ $$| $$  \\ $$\r\n"+
		"\t\t| $$   /$$__  $$ \\____  $$| $$  | $$| $$| $$  | $$| $$  | $$       /$$  \\ $$| $$  | $$| $$  | $$| $$  | $$\r\n"+
		"\t\t| $$  |  $$$$$$$ /$$$$$$$/| $$  | $$| $$|  $$$$$$/| $$  | $$      |  $$$$$$/| $$  | $$|  $$$$$$/| $$$$$$$/\r\n"+
		"\t\t|__/   \\_______/|_______/ |__/  |__/|__/ \\______/ |__/  |__/       \\______/ |__/  |__/ \\______/ | $$____/ \r\n"+
		"\t\t                                                                                                | $$      \r\n"+
		"\t\t                                                                                                | $$      \r\n"+
		"\t\t                                                                                                |__/      \r\n"+
		"\n\n"+
		"\t\t-----------------------------------------------------------------------------------------------------------\r\n"+
		"\n\n";
		
		System.out.println(label);
		System.out.println("\t\t[1] Place Orders\t\t\t\t[2] Search Customers\t\t\t[7] Exit Shopping");
		System.out.println("\n\t\t[3] Search Orders\t\t\t\t[4] View Reports");
		System.out.println("\n\t\t[5] Change Status\t\t\t\t[6] Delete Orders");
		
		System.out.print("\n\n\n\t\tEnter an option to continue : ");
		int options = input.nextInt();
		switch (options) {
			case 1 : clearConsole(); placeOrders(); break;
			case 2 : clearConsole(); searchCustomers(); break;
			case 3 : clearConsole(); searchOrders(); break;
			case 4 : clearConsole(); viewReports(); break;
			case 5 : clearConsole(); changeStatus(); break;
			case 6 : clearConsole(); deleteOrders(); break;
			case 7 : clearConsole(); exit(); break;
			default : System.out.println(); System.out.println("\n\t\tInvalid option entered!\n\t\tEnter the correct option to continue...");
		}
	}

	//-----------------------------------------------------------------Main Method--------------------------------------------------------

	public static void main (String [] args) {
		homePage();
	}
}
