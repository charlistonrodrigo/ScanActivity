package dominando.android.scanactivitycopia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class StringComparator  {


      public static void main(String[] args){


          Set<String> list = new TreeSet<>();

          String path = "C:\\Documents\\NetBeansProjects\\MySyster.txt";


          String path1 = "C:\\Documents\\NetBeansProjects\\correcao.txt";

          try(BufferedReader br = new BufferedReader(new FileReader(path))){

          String nome = br.readLine();
          while (nome != null){
              list.add(nome);
              nome = br.readLine();
              }
          //Collections.sort(list);

          } catch (IOException e) {
              e.printStackTrace();
          }
          for (String s : list
               ) {
              System.out.println(s);
          };

          FileOutputStream f = null;
          try {
              f = new FileOutputStream(path1,true);
              for(String s : list){
                  f.write(s.getBytes());
              }

          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }

          StringBuffer result = new StringBuffer();
          for(int i = 0; i < list.size(); i++){
              result.append(list);

          }
          System.out.println(result);

      }

}



