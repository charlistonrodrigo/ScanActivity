package dominando.android.scanactivitycopia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class StringComparator  {

/*
    public StringComparator codigo1;
    public StringComparator codigo2;


    public int Comparator() {
        return Comparator(codigo1,codigo2 );
    }

    public int Comparator (@NotNull StringComparator codigo1, @NotNull StringComparator codigo2){
        this.codigo1 = codigo1;
        this.codigo2 = codigo2;
        return codigo1.getCodigo() - codigo2.getCodigo();
    }
        public int codigo;

        public int getCodigo() {

        return codigo;
    }
*/

      public static void main(String[] args){
         /* final ArrayList<String> Codigo = new ArrayList<>();
          Scanner sc = new Scanner(System.in);

          for(int i = 1;i < 4;i ++) {
              Codigo.add(sc.nextLine());
          }
          //comparatorArrayList.add(Codigo);
          Codigo.add(2,"ju123br");
          //    }
          Collections.sort(Codigo);
          for (String letra:Codigo
          ) {

              System.out.print(letra+" ");
          }
          */

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



