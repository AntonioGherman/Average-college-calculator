package com.example.calculator_facul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    int counter=1,i=0;
    Lista lista;
    TextView media;
    ArrayList<Float> note=new ArrayList<>();
    ArrayList<Float> credite=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        media=findViewById(R.id.media);
        ArrayList<Materie> items=new ArrayList<>();
       items.add(new Materie(counter,findViewById(R.id.nota),findViewById(R.id.credite)));

       counter++;

        RecyclerView recyclerView=findViewById(R.id.view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
         lista=new Lista(items);
        recyclerView.setAdapter(lista);
        lista.notifyItemInserted(items.size()-1);

        i++;

        findViewById(R.id.add).setOnClickListener(v->{

            items.add(new Materie(counter,findViewById(R.id.nota),findViewById(R.id.credite)));
            Log.e("nr item adaugat",""+(items.size()-1));
            lista.notifyItemInserted(items.size()-1);
            Log.e("nr item adaugat dupa",""+(items.size()-1));
         //   lista.stringNota.add("0");
          //  lista.stringCredit.add("0");
            i++;
            counter++;
            recyclerView.scrollToPosition(items.size()-1);
        });

        findViewById(R.id.delete).setOnClickListener(v->{
          if(items.size()!=1) {
              items.remove(items.size() - 1);
             counter--;
              lista.stergere();
              Log.e("numarul de item INAINTE",""+items.size());
              lista.notifyItemRemoved(items.size());
              Log.e("numarul de item DUPA",""+items.size());



          }
          else if(items.size()==1) {
              lista.stergere();
              Toast.makeText(MainActivity.this, "Nu exista elemente de sters", Toast.LENGTH_SHORT).show();
              media.setText("Media: 0,00");
          }
          });



    }

}
