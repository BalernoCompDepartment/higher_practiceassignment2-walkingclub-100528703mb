import javax.swing.JOptionPane;
import java.io.*;
import java.util.Scanner;

class members {
  String forename;
  String surname;
  double distance;
}

class Main {
  public static void main(String[] args) throws IOException {

    members [] memberList = new members [20];
    
    String fileName = "members.txt";

    memberList = readFromMembers(fileName);

    double furthestDistance = findFurthestDistance(memberList);

    writeToFile(memberList, furthestDistance);
  }


  public static void writeToFile(members[] tempMembers, double tempDistance) throws IOException {

      String fileName = "results.txt";
      String content = "The prize winning members are:";

      File writeFile = new File (fileName);

      if(!writeFile.exists()) {
        writeFile.createNewFile();
      }

      FileWriter fw = new FileWriter(writeFile.getAbsoluteFile());

      BufferedWriter bw = new BufferedWriter(fw);

      bw.write(content);
      bw.write("\n");

      for (int index = 0; index < 20; index ++) {
        if (tempMembers[index].distance > (0.7 * tempDistance)) {
          bw.write(tempMembers[index].forename);
          bw.write(" ");
          bw.write(tempMembers[index].surname);
          bw.write("\n");
        }
      }

      

      bw.close();

      System.exit(0);



    }


public static members[] readFromMembers (String nameOfFile) {
    Scanner fileScanner = null;

    members [] tempMembers = new members [20];

    int index = 0;

    try {
      fileScanner = new Scanner (new BufferedReader (new FileReader (nameOfFile)));
      fileScanner.useDelimiter("[\\r\\n,]+");
      while (fileScanner.hasNext()) {
        tempMembers[index] = new members();
        tempMembers[index].forename = fileScanner.next();
        tempMembers[index].surname = fileScanner.next();
        tempMembers[index].distance = fileScanner.nextDouble();
        index = index + 1;
      }
    } catch (FileNotFoundException error) {
       System.out.println("File not found, fix the code and try again!");
    } finally {
      if(fileScanner!=null) {
        fileScanner.close();
      }
    }
    

      
    return tempMembers;

   }

   public static double findFurthestDistance(members[] tempMembers) {
     double maxDistance = tempMembers[0].distance;
     for (int index = 1; index < 20; index ++) {
       if (tempMembers[index].distance > maxDistance) {
         maxDistance = tempMembers[index].distance;

       }
     }

     return maxDistance;
   }



}




  