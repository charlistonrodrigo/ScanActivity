package dominando.android.scanactivitycopia;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

//@RuntimePermissions

@SuppressWarnings("deprecation")
public class ScanActivityCopia extends Activity implements Serializable {

    private  int STORAGE_PERMISSION_CODE = 1;
    private View btnScan;
    private View btnwriter;
    private TextView content;
    private TextView format;
    private TextView fileContents;
    private  int cont = 0;
    private static String contador;
    private String espaco = ") ";
    private  String  vetor[] = new String[4];
    private  AlertDialog alerta;

    final Set<String> Codigo = new TreeSet<>();
    final Set<String> list = new TreeSet<>();

     String conteudoArquivo, conteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanncopia);


        btnScan = (View) findViewById(R.id.btn_scan);
        content = (TextView) findViewById(R.id.content);
        format = (TextView) findViewById(R.id.format);
        btnwriter = (Button) findViewById(R.id.btn_writer);

        btnScan.setOnClickListener(onClickListener());




        btnwriter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                File internalStorageDir = getExternalFilesDir(null);

                if(!internalStorageDir.exists()){
                    internalStorageDir.mkdir();
                }
                File path1 = new File(internalStorageDir,"correcao.txt");

                File Root = Environment.getExternalStorageDirectory();
                File Dir = new File(Root.getAbsolutePath()+"/MyAppFile");
                if(!Dir.exists()){
                    Dir.mkdir();
                }
                File filename = new File(Dir,"Scanning.txt");

                try {
                    FileOutputStream fos = new FileOutputStream(filename);
                    FileOutputStream fout = new FileOutputStream(path1);

                    final EditText n = (EditText) findViewById(R.id.n1);

                   // Scanner ler = new Scanner(System.in);
                    conteudoArquivo = n.getText().toString();

                    if( n.getText().toString().trim().isEmpty() ) {
                   /*
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScanActivityCopia.this);
                    builder.setMessage(" Cancelled! Subtrair uma unidade no valor final da lista de objetos.")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    Toast.makeText(ScanActivityCopia.this, "Ok! Digite novamente. " , Toast.LENGTH_SHORT).show();
                                }
                            });
                    alerta = builder.create();
                    alerta.show();
*/
                        Toast.makeText(ScanActivityCopia.this, "Erro! Digite o codigo novamente. ", Toast.LENGTH_SHORT).show();
                        cont = cont - 1;
                    }

                    if( n.getText().toString().trim().length() != 13) {
                        conteudoArquivo = " ";
                        Toast.makeText(ScanActivityCopia.this, "Erro! Digite o codigo novamente. ", Toast.LENGTH_SHORT).show();
                        cont = cont - 1;
                    }
                    Codigo.add(conteudoArquivo);
                        cont = cont + 1;

                      //  fos.write(Codigo.toString().getBytes());
                        fos.write("\n".getBytes());


                    for (String item: Codigo
                    ) {

                        fos.write(item.toString().getBytes());
                        fos.write("\n".getBytes());
                        n.setText(" ");
                        // Arrays.sort(vetor);
                    }
                    //comparatorArrayList.add(Codigo);


                    //    }

            //        for (String letra:Codigo
            //        ) {

             //          fos.write(letra.getBytes());
             //       }
     /*
                    String conteudoArquivo2 = n1.getText().toString();
                    conteudoArquivo = conteudoArquivo2;

                    int i = 0;

                    // final Set<String> Codigo = new TreeSet<>();

                    if(Codigo != null) {
                        Codigo.add( conteudoArquivo);
                    }else{
                        conteudoArquivo2 = n1.getText().toString();
                        conteudoArquivo = conteudoArquivo2;
                        Codigo.add(conteudoArquivo );
                    }

                    for (String letra: Codigo
                    ) {
                        fos.write(letra.toString().getBytes());
                        // Arrays.sort(vetor);
                    }

                    try(BufferedReader br = new BufferedReader(new FileReader(filename))){

                        String nome = br.readLine();
                        while (nome != null){
                            list.add(nome);
                            nome = br.readLine();
                        }
                        //Collections.sort(list);
                        for (String palavra: list
                        ) {
                            fout.write(palavra.toString().getBytes());
                            fout.write("\n".getBytes());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
*/

                  //  contador = String.valueOf(cont);
                    contador = String.valueOf(cont);
                    fos.write("\n".getBytes());
                    // fos.flush();
                    fos.write("Total de Objetos : ".getBytes());
                    fos.write(contador.getBytes());


                    fos.flush();
                    fos.close();
                    //Toast.makeText(this, Dir.getAbsolutePath(), Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        Button buttonRequest = findViewById(R.id.button);
        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ScanActivityCopia.this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ScanActivityCopia.this, "You have already granted this permission!", Toast.LENGTH_LONG).show();

                } else {
                    requestStoragePermission();
                }
            }
        });
        //  lerArquivo();
    }

    private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    startActivityForResult(intent, 0);
                } catch (ActivityNotFoundException ex) {
                    ex.printStackTrace();

                    //if you haven't install barcodeScanner app, download it from Google Play
                    downloadScanBarcode();

                }

            }
        };
    }


    private void downloadScanBarcode() {
        Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                    format.setText(data.getStringExtra("SCAN_RESULT_FORMAT"));
                    content.setText(data.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
                format.setText("Cancelled.");
                format.setText("Falha no processo." +
                        "   Ultimo objeto lançado : "+
                        "");
                cont = cont - 1;

            }

        }

       // String  vetor[] = new String[4];


       // final Set<String> Codigo = new TreeSet<>();
       // final Set<String> list = new TreeSet<>();
        //String path1 = "data\\data\\dominando.android.scanactivitycopia\\files\\Scann.txt";
        File internalStorageDir = getExternalFilesDir(null);

        if(!internalStorageDir.exists()){
            internalStorageDir.mkdir();
        }
        File path1 = new File(internalStorageDir,"correcao.txt");

        File Root = Environment.getExternalStorageDirectory();
        File Dir = new File(Root.getAbsolutePath()+"/MyAppFile");
            if(!Dir.exists()){
                Dir.mkdir();
            }
            File filename = new File(Dir,"Scanning.txt");



        try {
            final FileOutputStream fos = new FileOutputStream(filename);
            FileOutputStream fout = new FileOutputStream(path1);

/*
            final EditText n = (EditText) findViewById(R.id.n1);

            // Scanner ler = new Scanner(System.in);
            conteudoArquivo = n.getText().toString();
            Codigo.add(conteudoArquivo);
            // fos.write(Codigo.toString().getBytes());

*/

           // Scanner ler = new Scanner(System.in);

            conteudoArquivo = content.getText().toString();
            //String conteudoArquivo2 = content.getText().toString();
            if( content.getText().toString().length() != 13) {
                conteudoArquivo = " ";
                Toast.makeText(ScanActivityCopia.this, "Erro! Digite o codigo novamente. ", Toast.LENGTH_SHORT).show();
                cont = cont - 1;

            }
           // conteudoArquivo = content.getText().toString();
            int i = 0;

           // final Set<String> Codigo = new TreeSet<>();

           // final EditText n1 = (EditText) findViewById(R.id.n1);

            if(Codigo != null) {
               Codigo.add( conteudoArquivo);
                cont = cont + 1;
            }
            // Limpar o arquivo antes de gravar.  ////////   For de baixo e o que funciona com tudo.

           // Codigo.removeIf(x -> x.length() < 13);
            //Codigo.removeIf(conteudoArquivo ->conteudoArquivo.length() <= 12 );

           // Codigo.remove( content.getText().toString());


            for (String letra: Codigo
            ) {

                fos.write(letra.toString().getBytes());
                fos.write("\n".getBytes());

                // Arrays.sort(vetor);
            }



            try(BufferedReader br = new BufferedReader(new FileReader(filename))){

                String nome = br.readLine();
                while (nome != null){
                    list.add(nome);
                    nome = br.readLine();
                }
                //Collections.sort(list);
                for (String palavra: list
                ) {
                    fout.write(palavra.toString().getBytes());

                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            contador = String.valueOf(cont);
            fos.write("\n".getBytes());
           // fos.flush();
            fos.write("Total de Objetos : ".getBytes());
            fos.write(contador.getBytes());
            fos.close();
            //Toast.makeText(this, Dir.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    //    cont = cont + 1;    ***********************


    }


    private void requestStoragePermission() {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed because of this and that")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(ScanActivityCopia.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                        STORAGE_PERMISSION_CODE);
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);
            }
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if(requestCode == STORAGE_PERMISSION_CODE){
           if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
               Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(this, "permission DENIED", Toast.LENGTH_SHORT).show();
           }
    }





        }

}
