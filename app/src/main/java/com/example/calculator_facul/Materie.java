package com.example.calculator_facul;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Materie extends AppCompatActivity {
    EditText nota1,credit1;
    String nota,credit;
    int numar;

    public Materie(int numar, EditText  idNota, EditText idCredit){
        nota1=idNota;
        credit1=idCredit;
        nota="";
        credit="";
        this.numar=numar;
    }


}
