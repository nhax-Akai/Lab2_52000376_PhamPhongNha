import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void addProduct(Product pro) {
        ProductDAO.getInstance().add(pro);
    }

    public static void readAllProduct() {
        // print all products
        List<Product> list = new ArrayList<>();
        list = ProductDAO.getInstance().readAll();
        for (Product product : list) {
            System.out.println(product);
        }

    }

    public static void readProductById(int id) {
        // print detail of a product by id
        Product pro = new Product();
        pro = ProductDAO.getInstance().read(id);
        System.out.println(pro);
    }

    public static void updateProduct(Product pro) {
        if (ProductDAO.getInstance().update(pro) == true) {
            System.out.println("Update success");
        } else {
            System.out.println("Update fail");
            System.out.println("Please check id of product");
        }
    }

    public static void deleteProduct(int id) {
        if (ProductDAO.getInstance().delete(id) == true) {
            System.out.println("Delete success");
        } else {
            System.out.println("Delete fail");
            System.out.println("Please check id of product");
        }
    }

    public static void show() {
        // show menu: 1. read all products, read detail of a product by id, add a
        // product, update a product, delete a product
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("Menu");
        System.out.println("1. Read all products");
        System.out.println("2. Read detail of a product by id");
        System.out.println("3. Add a product");
        System.out.println("4. Update a product");
        System.out.println("5. Delete a product");
        System.out.println("6. Exit");
        System.out.println("---------------------------------");
        System.out.println("Please choose an option: ");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                readAllProduct();
                break;
            case 2:
                System.out.println("Please enter id of product: ");
                int id = sc.nextInt();
                readProductById(id);
                break;
            case 3:
                System.out.println("Please enter id of product: ");
                int id1 = sc.nextInt();
                System.out.println("Please enter name of product: ");
                String name = sc.next();
                System.out.println("Please enter price of product: ");
                int price = sc.nextInt();
                Product pro = new Product(id1, name, price);
                addProduct(pro);
                break;
            case 4:
                System.out.println("Please enter id of product: ");
                int id2 = sc.nextInt();
                System.out.println("Please enter name of product: ");
                String name1 = sc.next();
                System.out.println("Please enter price of product: ");
                int price1 = sc.nextInt();
                Product pro1 = new Product(id2, name1, price1);
                updateProduct(pro1);
                break;
            case 5:
                System.out.println("Please enter id of product: ");
                int id3 = sc.nextInt();
                ProductDAO.getInstance().delete(id3);
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Please choose an option from 1 to 6");
                break;
        }

    }

    public static void main(String[] args) {
        do {
            show();
        } while (true);
    }
}