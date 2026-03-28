import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandling
{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String filename; //to store fliename
        String input;// TO TAKE USER INPUT 
        char choice=' '; //to store user choice
        
        filename=getFlieName(sc);
        
        do
        { 
            System.out.println("\n ========== File Operation Menu ========== \n");
            System.out.print("\n 1. Create new File \n 2. Select file  \n 3. Write to File \n 4. Read File \n 5. Append to Flie \n 6. Modify(Replace Text) \n 7. exit \n");
            System.out.println("enter your choice :");
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("No input entered!");
                continue;
            }
            choice = input.charAt(0);
            try
            {
                switch(choice) {
                    case '1':
                        createFile(filename);
                        break;
                    case '2':
                        filename=getFlieName(sc);
                        break;
                    case '3':
                        System.out.print("Enter text to write in flie :");
                        String writeData = sc.nextLine();
                        writeFile(filename,writeData);
                        break;
                    case '4':
                        readFile(filename);
                        break;
                    case '5':
                        System.out.print("Enter text to append: ");
                        String appendData = sc.nextLine();
                        appendFile(filename, appendData);
                        break;
                    case '6':
                        System.out.print("Enter word to replace: ");
                        String oldWord = sc.nextLine();
                        System.out.print("Enter new word: ");
                        String newWord = sc.nextLine();
                        modifyFile(filename, oldWord, newWord);
                        break;
                    case '7':
                        break;
                    default:
                        System.out.println(" Invalid Choice ");
                }
            }
            catch (IOException e) 
            {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != '7');
        System.out.println("Program Exited ..");
        sc.close();
    }

    //take file name from user
    static String getFlieName(Scanner s) {

        String fileName;
        while(true)
        {
            System.out.println("enter file name (wih .txt):");
            fileName=s.nextLine();
            fileName=fileName.trim();
            if (!fileName.endsWith(".txt"))
            {
                System.out.println("Invalid format! Please enter a file ending with .txt");
            }
            else
            {
                if(fileName.length()<=4)
                {
                    System.out.println("File name must have at least one character with .txt");
                }
                else
                {
                    break;
                }
            }
        }
        return fileName;
    }

    //create new file
    static void createFile(String fileName) throws IOException
    {
        File file=new File(fileName);
        if(file.exists())
        {
            System.out.println("File already exists.");
        }
        else
        {
            file.createNewFile();
            System.out.println("File created Successfully.");
        }

    }

    //write file
    static void writeFile(String fileName,String data) throws IOException
    {
        FileWriter fw= new FileWriter(fileName);
        fw.write(data);
        fw.close();
        System.out.println("File written Successfully.");
    }

    // read file
    static void readFile(String fileName) throws IOException
    {
        System.out.print("File Content : \n");
        BufferedReader br= new BufferedReader(new FileReader(fileName));
        String l;
        while((l=br.readLine()) != null)
        {
            System.out.print(l);
        }
        br.close();
    }

    //append
    static void appendFile(String fileName,String data) throws IOException
    {
        FileWriter fw = new FileWriter(fileName,true);
        fw.write("\n"+data);
        fw.close();
        System.out.println("appended successfully.");
    }

    // modify
    static void modifyFile(String fileName,String oldtext,String newtext) throws IOException
    {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder data = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            data.append(line.replace(oldtext, newtext)).append("\n");
        }
        br.close();

        FileWriter fw = new FileWriter(file);
        fw.write(data.toString());
        fw.close();

        System.out.println("File modified successfully.");
    }
}