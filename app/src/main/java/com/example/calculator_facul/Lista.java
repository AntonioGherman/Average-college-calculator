package com.example.calculator_facul;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Lista extends RecyclerView.Adapter<ListaVH> {

    EditText nota, credit;
    TextView media;
    View rootView;
    Context context;

    ArrayList<EditText> TextMaterie=new ArrayList<>();
    private ArrayList<EditText> TextNote =new ArrayList<>();
    private ArrayList<EditText> TextCredite = new ArrayList<>();

    ArrayList<Integer> noteleInt=new ArrayList<>();
    ArrayList<Integer> crediteleInt=new ArrayList<>();
    ArrayList<Materie> items;
    static  int counter=-1;
    ArrayList<String> stringNota = new ArrayList<String>();
    ArrayList<String> stringCredit = new ArrayList<String>();
    boolean isOnTextChangedNote = false, isOnTextChangedCredit = false;
    boolean textAdaugat;


    public Lista(ArrayList<Materie> items) {
        this.items = items;

    }

    @NonNull
    @Override
    public ListaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        context = parent.getContext();
        rootView=((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);

        media=rootView.findViewById(R.id.media);



        return new ListaVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaVH holder, @SuppressLint("RecyclerView") final int position) {
        //    holder.textView.setText(String.valueOf(items.get(position).numar));
int position2;
        position2=holder.getAdapterPosition();

Log.e("POZZZZIIIITIIIIIEEEEE",""+position2+"     "+position);
        textAdaugat=false;
        TextView numar = holder.textView;
        TextMaterie.add(holder.materie);
        TextNote.add(items.get(position).numar-1,holder.nota);
        TextCredite.add(items.get(position).numar-1,holder.credit);

        TextNote.get(items.get(position).numar-1).setText("");
        TextCredite.get(items.get(position).numar-1).setText("");

        nota = holder.nota;
        credit = holder.credit;
        numar.setText(String.valueOf(items.get(position).numar));

        Log.e("test  1:",""+counter);
     //   Log.e("test   2 :",""+counter+"    "+TextNote.get(counter).getText()+"    "+TextCredite.get(counter).getText());
     //   Log.e("test   2 :",""+counter+"    "+nota.getText()+"    "+credit.getText());

        nota.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.e("verific pozitia aici",""+position2);
                isOnTextChangedNote = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.e("test 2",""+position2);
                if (isOnTextChangedNote) {
                    isOnTextChangedNote = false;
                    textAdaugat=true;

                    try {
                        for (int i = 0; i <= position; i++) {
                            int position1 =position2;
                            if (i != position1) {
                                stringNota.add("0");
                            } else {
                                stringNota.add("0");
                                Log.e("positie inainte",""+counter);
                                    stringNota.set(counter, s.toString());
                                if(stringNota.get(stringNota.size()-1).compareTo("0")==0){
                                    stringNota.remove(stringNota.size()-1);
                                }
                                break;
                            }
                            if(stringNota.get(stringNota.size()-1).compareTo("0")==0){
                                stringNota.remove(stringNota.size()-1);
                            }
                        }
                        float suma = 0, sumaCredit = 0;
                        for(int i=1;i< stringNota.size() && i<stringCredit.size();i++){
                            if(stringNota.get(i).compareTo("10")==0 && stringNota.get(i-1).compareTo("1")==0){
                                stringNota.remove(i-1);
                            }
                            if(stringCredit.get(i).compareTo("10")==0 && stringCredit.get(i-1).compareTo("1")==0){
                                stringCredit.remove(i-1);
                            }
                        }

                        for (int i = 0; i < stringNota.size() && i < stringCredit.size(); i++) {
                            if(stringCredit.get(i).compareTo("")!=0 && stringNota.get(i).compareTo("")!=0){
                                if(stringNota.get(i).compareTo("")!=0 && stringCredit.get(i).compareTo("")!=0) {
                                    suma = suma + Float.parseFloat(stringNota.get(i)) * Float.parseFloat(stringCredit.get(i));
                                    sumaCredit = sumaCredit + Integer.parseInt(stringCredit.get(i));
                                }
                            }
                        }
                        media.setText("Media: "+String.format("%.02f",Math.floor((suma/sumaCredit)*100)/100));



                    } catch (NumberFormatException e) {

                        for (int i = 0; i <= stringNota.size() - 1; i++) {
                            int newposition = position;
                            if (i == newposition) {
                                stringNota.set(newposition, "0");
                            }
                        }
                    }

                    float suma = 0, sumaCredit = 0;

                    for(int i=1;i< stringNota.size() && i<stringCredit.size();i++){
                        if(stringNota.get(i).compareTo("10")==0 && stringNota.get(i-1).compareTo("1")==0){
                            stringNota.remove(i-1);
                        }
                        if(stringCredit.get(i).compareTo("10")==0 && stringCredit.get(i-1).compareTo("1")==0){
                            stringCredit.remove(i-1);
                        }
                    }

                    for (int i = 0; i < stringNota.size() && i < stringCredit.size(); i++) {
                        if(stringCredit.get(i).compareTo("")!=0 && stringNota.get(i).compareTo("")!=0){
                            if(stringNota.get(i).compareTo("")!=0 && stringCredit.get(i).compareTo("")!=0) {
                                suma = suma + Float.parseFloat(stringNota.get(i)) * Float.parseFloat(stringCredit.get(i));
                                sumaCredit = sumaCredit + Integer.parseInt(stringCredit.get(i));
                            }
                        }
                    }
                    media.setText("Media: "+String.format("%.02f",Math.floor((suma/sumaCredit)*100)/100));

                }


            }
        });


        credit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                isOnTextChangedCredit = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (isOnTextChangedCredit) {
                    isOnTextChangedCredit = false;

                    try {
                        for (int i = 0; i <= position; i++) {
                            int position1 = position2;
                            if (i != position1) {
                                stringCredit.add("0");
                            } else {
                                stringCredit.add("0");

                                   stringCredit.set(counter, s.toString());

                                if(stringCredit.get(stringCredit.size()-1).compareTo("0")==0){
                                    stringCredit.remove(stringCredit.size()-1);
                                }
                                break;
                            }
                            if(stringCredit.get(stringCredit.size()-1).compareTo("0")==0){
                                stringCredit.remove(stringCredit.size()-1);
                            }
                        }


                        float suma = 0, sumaCredit = 0;

                        for(int i=1;i< stringNota.size() && i<stringCredit.size();i++){
                            if(stringNota.get(i).compareTo("10")==0 && stringNota.get(i-1).compareTo("1")==0){
                                stringNota.remove(i-1);
                            }
                            if(stringCredit.get(i).compareTo("10")==0 && stringCredit.get(i-1).compareTo("1")==0){
                                stringCredit.remove(i-1);
                            }
                        }

                        for (int i = 0; i < stringNota.size() && i < stringCredit.size(); i++) {
                            if(stringCredit.get(i).compareTo("")!=0 && stringNota.get(i).compareTo("")!=0){
                                suma = suma + Float.parseFloat(stringNota.get(i)) * Float.parseFloat(stringCredit.get(i));
                                sumaCredit = sumaCredit + Integer.parseInt(stringCredit.get(i));
                            }
                        }
                        media.setText("Media: "+String.format("%.02f",Math.floor((suma/sumaCredit)*100)/100));



                    } catch (NumberFormatException e) {

                        for (int i = 0; i <= stringCredit.size() - 1; i++) {
                            int newposition = position;
                            if (i == newposition) {
                                stringCredit.set(newposition, "0");
                            }
                        }


                        float suma = 0, sumaCredit = 0;

                        for(int i=1;i< stringNota.size() && i<stringCredit.size();i++){
                            if(stringNota.get(i).compareTo("10")==0 && stringNota.get(i-1).compareTo("1")==0){
                                stringNota.remove(i-1);
                            }
                            if(stringCredit.get(i).compareTo("10")==0 && stringCredit.get(i-1).compareTo("1")==0){
                                stringCredit.remove(i-1);
                            }
                        }

                        for (int i = 0; i < stringNota.size() && i < stringCredit.size(); i++) {
                            if(stringCredit.get(i).compareTo("")!=0 && stringNota.get(i).compareTo("")!=0){


                                suma = suma + Float.parseFloat(stringNota.get(i)) * Float.parseFloat(stringCredit.get(i));
                                sumaCredit = sumaCredit + Integer.parseInt(stringCredit.get(i));
                            }
                        }
                        media.setText("Media: "+String.format("%.02f",Math.floor((suma/sumaCredit)*100)/100));
                    }

                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

//    public float media() {
//        float media;
//        float suma = 0, sumaCredit = 0;
//        for (int i = 0; i < stringNota.size() && i < stringCredit.size(); i++) {
//
//            suma=suma+noteleInt.get(i)*crediteleInt.get(i);
//            sumaCredit=sumaCredit+crediteleInt.get(i);
//        }
//            if (suma == 0 && sumaCredit == 0) {
//                return 0;
//            } else {
//                media = suma / sumaCredit;
//                return media;
//            }
//        }

    public void crestereCounter() {
        counter++;
        Log.e("test: 2", "" + counter);

    }
    public void stergere() {

        if(TextNote.size()!=1) {
            TextNote.get(TextNote.size()-1).setText("");
            TextNote.remove(TextNote.size()-1);
        }
        else{
            TextNote.get(0).setText("");
        }
      if(stringNota.size()!=0) {
          stringNota.remove(stringNota.size() - 1);
      }

      if(TextCredite.size()!=1) {
          TextCredite.get(TextCredite.size() - 1).setText("");
          TextCredite.remove(TextCredite.size() - 1);
      }
      else{
          TextCredite.get(0).setText("");
      }
      if (stringCredit.size()!=0){
          stringCredit.remove(stringCredit.size()- 1);
      }

      if(TextMaterie.size()!=0){
        TextMaterie.get(TextMaterie.size()-1).setText("");
        TextMaterie.remove(TextMaterie.size()-1);
      }
        Log.e("counter=",""+counter);
        counter--;
        Log.e("counter=",""+counter);
        if(counter<=0){
            stringCredit.clear();
           stringNota.clear();
            textAdaugat = false;
        }
        if(counter<0){
            counter=0;
        }



    }

}

class ListaVH extends RecyclerView.ViewHolder {

    TextView textView;
    EditText nota, credit, materie;


    public ListaVH(@NonNull View itemView) {
        super(itemView);

        materie=itemView.findViewById(R.id.materie);
        textView = itemView.findViewById(R.id.numar);
        nota = itemView.findViewById(R.id.nota);
        credit = itemView.findViewById(R.id.credite);
    }

}