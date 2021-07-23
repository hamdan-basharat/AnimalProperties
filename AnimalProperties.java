package animalproperties;

import java.util.*;
import java.io.*;

public class AnimalProperties
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);                   
    int userInput = -1;                                       
    Database obj = new Database();
    while (userInput != 5)                                     
    {
      System.out.println("Menu - Please select an option");
      System.out.println("==============================");
      System.out.println("1. Open a file");
      System.out.println("2. Display data information");
      System.out.println("3. Enter a new record");
      System.out.println("4. Save a file");
      System.out.println("5. Exit the program");
      System.out.println("==============================");
      
      while (!input.hasNextInt())                            
      {
        input.next();
      }
      userInput = input.nextInt();
      
      switch (userInput)
      {
      case 1:
        System.out.println("...file opening...");
        try
        {
        obj.openFile();
        }
        catch (IOException e)
        {
          e.getMessage();
        }
        catch (Exception e)
        {
          e.getMessage();
        }
        break;
        case 2:

        obj.displayFile();
        break;
        case 3:
          obj.add();
          break;
        case 4:
          try
        {
          obj.saveFile();
        }
          catch (IOException e)
          {
            e.getMessage();
          }
          catch (Exception e)
          {
            e.getMessage();
          }
          System.out.println("...Saving file...");
          break;
        case 5:
          System.out.println("Good-Bye");
          break;
        case 6:
          obj.test();
          break;
        default:
          System.out.println("Invalid option selected. Please try again.");
          break;
      }
      
    }
    
  }
}

class Database
{
  private Animal[] array;
  Database()
  {
   array = new Animal[10];
  }
  
  public Animal[] getArray()
  {
    return array;
  }
  
  public Animal getArray(int I)
  {
    return array[I];
  }
  
  public void openFile() throws IOException
  {
    File animals = new File("mrAFileData.txt");
    Scanner fileInput = new Scanner(animals);
    
    int a = 0;
    while (a < array.length)  
    {
      String classType = fileInput.next();
      if (classType.equalsIgnoreCase("Mammal"))
      {
        array[a] = (new Mammal(fileInput.next(), fileInput.nextDouble(), fileInput.nextBoolean(), fileInput.nextBoolean()));
      }
      else if (classType.equalsIgnoreCase("Bird"))
      {
        array[a] = (new Bird(fileInput.next(), fileInput.nextDouble(), fileInput.nextBoolean(), fileInput.nextBoolean()));
      }
      else if (classType.equalsIgnoreCase("Reptile"))
      {
        array[a] = (new Reptile(fileInput.next(), fileInput.nextDouble(), fileInput.nextBoolean(), fileInput.nextBoolean()));
      }
      a++;
    }
    fileInput.close();     
    
  }
  
  public void displayFile()
  {
    int elements = 0;
    for (int i = 0; i < array.length; i++)
    {
      if (array[i] != null)
      {
        elements++;
      }
    }
    System.out.printf("%-20s %-20s %-10s %-20s %-30s \n", "Type", "Name", "Weight", "Live Birth", "hasFur/canBlend/hasFeathers");
    System.out.println("======================================================================================================");
    for (int i = 0; i < elements; i++)
    {
      boolean uniqueTrait = false;
      String species = "";
      String animalName = "";
      String type = (getArray(i).getType());
      if (type.equalsIgnoreCase("Mammal"))
      {
        System.out.printf("%-20s %-20s %-10.1f %-20s %-30s%n", getArray(i).getType(), (((Mammal)getArray(i)).getName()), getArray(i).getWeight(), getArray(i).getLiveBirth(), (((Mammal)getArray(i)).getHasFur()));
      }
      else if (type.equalsIgnoreCase("Reptile"))
      {
        System.out.printf("%-20s %-20s %-10.1f %-20s %-30s%n", getArray(i).getType(), (((Reptile)getArray(i)).getName()), getArray(i).getWeight(), getArray(i).getLiveBirth(), (((Reptile)getArray(i)).getCanBlend()));
      }
      else if (type.equalsIgnoreCase("Bird"))
      {
        System.out.printf("%-20s %-20s %-10.1f %-20s %-30s%n", getArray(i).getType(), (((Bird)getArray(i)).getName()), getArray(i).getWeight(), getArray(i).getLiveBirth(),  (((Bird)getArray(i)).getHasFeathers()));
      }
    
    }
  }
  
  public void test()
  {
    System.out.println(getArray(6).getType());
    System.out.println(((Mammal)getArray(6)).getName());
    System.out.println(getArray(6).getWeight());
    System.out.println(getArray(6).getLiveBirth());
    System.out.println(((Mammal)getArray(6)).getHasFur());
    
    
  }
  
  public void add()
  {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a type (Mammal, Bird, Reptile)");
    String type = input.next();
    System.out.println("Enter a name");
    String name = input.next();
    System.out.println("Enter a weight (kg)");
    
    while (!input.hasNextDouble())
    {
      input.next();
    }
    double weight = input.nextDouble();
    
    System.out.println("Live birth? (true/false)");
    
    while (!input.hasNextBoolean())
    {
      input.next();
    }
    boolean liveBirth = input.nextBoolean();
    
    String uniqueFlag = "";
    boolean unique = false;
    
    if (type.equalsIgnoreCase("Mammal"))
    {
      uniqueFlag = "hasFur?";
    }
    else if (type.equalsIgnoreCase("Reptile"))
    {
      uniqueFlag = "canBlend?";
    }
    else if (type.equalsIgnoreCase("Bird"))
    {
      uniqueFlag = "hasFeathers?";
    }
    
    System.out.println(uniqueFlag + "(true/false)");
    
    while(!input.hasNextBoolean())
    {
      input.next();
    }
    unique = input.nextBoolean();
    
    int elements = 0;
    
    for (int i = 0; i < array.length; i++)
    {
      if (array[i] != null)
      {
        elements++;
      }
    }
    System.out.println(type + name + weight + liveBirth + unique);
    if (type.equalsIgnoreCase("Mammal"))
    {
      array[elements] = (new Mammal(name, weight, liveBirth, unique));
    }
    else if (type.equalsIgnoreCase("Reptile"))
    {
      array[elements] = (new Reptile(name, weight, liveBirth, unique));
    }
    else if (type.equalsIgnoreCase("Bird"))
    {
      array[elements] = (new Bird(name, weight, liveBirth, unique));
    }
    input.close();
  }
  
  public void saveFile() throws IOException
  {
    PrintWriter refInput = new PrintWriter("hamdanbasharat.txt");
    int elements = 0;
    
    for (int i = 0; i < array.length; i++)
    {
      if (array[i] != null)
      {
        elements++;
      }
    }
    for (int i = 0; i < elements; i++)
    {
      String type = array[i].getType();
      
      if (type.equalsIgnoreCase("Mammal"))
      {
        refInput.printf("%s %s %.1f %s %s%n", getArray(i).getType(), (((Mammal)getArray(i)).getName()), getArray(i).getWeight(), getArray(i).getLiveBirth(), (((Mammal)getArray(i)).getHasFur()));
      }
      else if (type.equalsIgnoreCase("Reptile"))
      {
        refInput.printf("%s %s %.1f %s %s%n", getArray(i).getType(), (((Reptile)getArray(i)).getName()), getArray(i).getWeight(), getArray(i).getLiveBirth(), (((Reptile)getArray(i)).getCanBlend()));
      }
      else if (type.equalsIgnoreCase("Bird"))
      {
        refInput.printf("%s %s %.1f %s %s%n", getArray(i).getType(), (((Bird)getArray(i)).getName()), getArray(i).getWeight(), getArray(i).getLiveBirth(),  (((Bird)getArray(i)).getHasFeathers()));
      }
      
    }
    
    refInput.close();
    
  }
}

class Animal
{
  private String type;
  private double weight;
  private boolean liveBirth;
  
  Animal(String T, double W, boolean B)
  {
    this.type = T;
    this.weight = W;
    this.liveBirth = B;
  }
  
  public String getType()
  {
    return type;
  }
  public void setType(String A)
  {
    type = A;
  }
  public double getWeight()
  {
    return weight;
  }
  public void setWeight(double B)
  {
    weight = B;
  }
  public boolean getLiveBirth()
  {
    return liveBirth;
  }
  public void setLiveBirth(boolean C)
  {
    liveBirth = C;
  }
  public void speak()
  {
  }
}

class Mammal extends Animal
{
  private String name;
  private boolean hasFur;
  Mammal(String N, double W, boolean B, boolean F)
  {
    super("Mammal", W, B);
    this.name = N;
    this.hasFur = F;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String D)
  {
    name = D;
  }
  public boolean getHasFur()
  {
    return hasFur;
  }
  public void setHasFur(boolean E)
  {
    hasFur = E;
  }
  public void speak()
  {
  }
}

class Reptile extends Animal
{
  private String name;
  private boolean canBlend;
  Reptile(String N, double W, boolean B, boolean Blend)
  {
    super("Reptile", W, B);
    this.name = N;
    this.canBlend = Blend;
  }
    public String getName()
  {
    return name;
  }
  public void setName(String F)
  {
    name = F;
  }
  public boolean getCanBlend()
  {
    return canBlend;
  }
  public void setCanBlend(boolean G)
  {
    canBlend = G;
  }
  public void speak()
  {
  }
}

class Bird extends Animal
{
  private String name;
  private boolean hasFeathers;
  Bird(String N, double W, boolean B, boolean F)
  {
    super("Bird", W, B);
    this.name = N;
    this.hasFeathers = F;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String H)
  {
    name = H;
  }
  public boolean getHasFeathers()
  {
    return hasFeathers;
  }
  public void setHasFeathers(boolean I)
  {
    hasFeathers = I;
  }
  @Override
  public void speak()
  {
  }
}
