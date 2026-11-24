import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class controledeprodutos {
  static class Produto {
    String name;
    Double price;
    Integer amount;
    String category;
  }

  static ArrayList<String> categories = new ArrayList<String>();
  static ArrayList<Produto> products = new ArrayList<Produto>();

  public static void main(String[] args) {
    boolean isRunning = true;
    Scanner scanner = new Scanner(System.in);
    while(isRunning) {
      System.out.print("\033\143");
      System.out.println("Selecione a funcionalidade a executar digitando seu número: ");

      System.out.println("0 - Cadastrar novo produto");
      System.out.println("1 - Exibir todos os produtos cadastrados");
      System.out.println("2 - Exibir valor total do estoque de cada produto");
      System.out.println("3 - Exibir produtos por categoria");
      System.out.println("4 - Finalizar programa");

      Integer menuChoice = scanner.nextInt();
      scanner.nextLine();

      switch (menuChoice) {
        case 0:	
          registerNewProduct(scanner);
          break;
        case 1:	
          showAllProducts(scanner);
          break;
        case 2:	
          showAllProductsValue(scanner);
          break;
        case 3:	
          showProductsByCategory(scanner);
          break;
        case 4:
          isRunning = false;
          break;
        default:
          break;
      }
      System.out.println("Enter para continuar");
      scanner.nextLine();
      System.out.print("\033\143");
    }
    scanner.close();
  }

  static void registerNewProduct(Scanner scanner) {
    System.out.print("\033\143");

    System.out.println("Digite o nome do produto e aperte enter para confirmar:\n");
    String productName = scanner.nextLine();

    System.out.println("Digite o valor do produto e aperte enter para confirmar:\n");
    Double productPrice = scanner.nextDouble();
    scanner.nextLine();

    System.out.println("Digite a quantidade em estoque e aperte enter para confirmar:\n");
    Integer productAmount = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Digite a categoria do produto e aperte enter para confirmar:\n");
    System.out.println("Categorias já existentes: (Digite uma nova para adicionar)");
    System.out.println(" ");
    for (String category : categories) {
      System.out.println(category);
    }

    String productCategory = scanner.nextLine();

    System.out.println("Nome: " + productName);
    System.out.println("Preço: R$" + productPrice);
    System.out.println("Quantidade: x" + productAmount);
    System.out.println("Categoria: " + productCategory);

    Produto newProduct = new Produto();
    newProduct.name = productName;
    newProduct.price = productPrice;
    newProduct.amount = productAmount;
    newProduct.category = productCategory;
    products.add(newProduct);

    if (categories.contains(productCategory)) {
      return;
    }
    categories.add(productCategory);
  }

  static void showAllProducts(Scanner scanner) {
    if (products.size() == 0) {
      System.out.println("Nenhum produto cadastrado");
    }

    System.out.println(" ");
    for (Produto product : products) {
      System.out.println("x" + product.amount + " - " + product.name + " - Valor: R$" + product.price + " - Categoria: " + product.category);
    }
  }

  static void showAllProductsValue(Scanner scanner) {
    if (products.size() == 0) {
      System.out.println("Nenhum produto cadastrado");
    }

    for (Produto product : products) {
      double fullPrice = product.price * product.amount;
      System.out.println("x" + product.amount + " - " + product.name + " - Valor Total do Estoque: R$" + String.format("%.2f", fullPrice));
    }
  }

  static void showProductsByCategory(Scanner scanner) {
    if (products.size() == 0) {
      System.out.println("Nenhum produto cadastrado");
    }

    System.out.println("Escolha a categoria: (digite o número associado)");
    Integer count = 0;
    HashMap<Integer, String> categoryMap = new HashMap<Integer, String>();
    for (String category : categories) {
      System.out.println(count + " - " + category);
      categoryMap.put(count, category);
      count++;
    }

    Integer chosenNumber = scanner.nextInt();
    scanner.nextLine();
    String chosenCategory = categoryMap.get(chosenNumber);

    System.out.print("\033\143");

    System.out.println(" - " + chosenCategory + ":");
    for (Produto product : products) {
      if (!product.category.equals(chosenCategory)) {
        continue;
      }
      System.out.println("x" + product.amount + " - " + product.name + " - Valor Total do Estoque: R$" + (product.price * product.amount));
    }
  }
}
