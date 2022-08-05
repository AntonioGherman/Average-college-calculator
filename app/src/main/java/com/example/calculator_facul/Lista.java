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
    ArrayList<EditText> TextNote =new ArrayList<>();
    ArrayList<EditText> TextCredite = new ArrayList<>();

    ArrayList<Integer> noteleInt=new ArrayList<>();
    ArrayList<Integer> crediteleInt=new ArrayList<>();
    ArrayList<Materie> items;
    static  int counter=0;
   static ArrayList<String> stringNota = new ArrayList<String>();
    static ArrayList<String> stringCredit = new ArrayList<String>();
    boolean isOnTextChangedNote = false, isOnTextChangedCredit = false;


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

   // static int inceput=0;
    @Override
    public void onBindViewHolder(@NonNull ListaVH holder, @SuppressLint("RecyclerView") int position) {
        //    holder.textView.setText(String.valueOf(items.get(position).numar));

        TextView numar = holder.textView;
        TextMaterie.add(holder.materie);
        TextNote.add(holder.nota);
        TextCredite.add(holder.credit);
        nota = holder.nota;
        credit = holder.credit;
        numar.setText(String.valueOf(items.get(position).numar));
        nota.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                isOnTextChangedNote = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (isOnTextChangedNote) {
                    isOnTextChangedNote = false;

                    try {
                        for (int i = 0; i <= position; i++) {
                            int position1 = position;
                            if (i != position1) {
                                stringNota.add("0");
                            } else {
                                stringNota.add("0");
//                                if(stringNota.size()==1){
//                                    position1=0;
//                                }
                                Log.d("pozitie eroare",""+position1);
                                Log.d("marime vector",""+stringNota.size());
                                stringNota.set(position1, s.toString());
                                Log.d("marime vector dupa",""+stringNota.size());
                                Log.d("pozitie",":"+position1);
                                Log.d("verificare",":"+stringNota+"  "+stringCredit);
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
                        for (int i = 0; i < stringNota.size() && i < stringCredit.size(); i++) {
                            if(stringCredit.get(i).compareTo("")!=0 && stringNota.get(i).compareTo("")!=0){
                                Log.d("test1",":"+stringNota+"  "+stringCredit);
                                if(stringNota.get(i).compareTo("")!=0 && stringCredit.get(i).compareTo("")!=0) {
                                    suma = suma + Float.parseFloat(stringNota.get(i)) * Float.parseFloat(stringCredit.get(i));
                                    sumaCredit = sumaCredit + Integer.parseInt(stringCredit.get(i));
                                }
                            }
                        }
                      //  Log.d("notele ",""+noteleInt);
                        media.setText("Media: "+(suma/sumaCredit));



                    } catch (NumberFormatException e) {

                        for (int i = 0; i <= stringNota.size() - 1; i++) {
                            int newposition = position;
                            if (i == newposition) {
                                stringNota.set(newposition, "0");
                            }
                        }
                    }

                    float suma = 0, sumaCredit = 0;
                    for (int i = 0; i < stringNota.size() && i < stringCredit.size(); i++) {
                        if(stringCredit.get(i).compareTo("")!=0 && stringNota.get(i).compareTo("")!=0){
                            Log.d("test2",":"+stringNota+"  "+stringCredit);
                            if(stringNota.get(i).compareTo("")!=0 && stringCredit.get(i).compareTo("")!=0) {
                                suma = suma + Float.parseFloat(stringNota.get(i)) * Float.parseFloat(stringCredit.get(i));
                                sumaCredit = sumaCredit + Integer.parseInt(stringCredit.get(i));
                            }
                        }
                    }
                //    Log.d("notele ",""+noteleInt);
                    media.setText("Media: "+(suma/sumaCredit));

                }


            }
        });
//        noteleInt.add(Integer.parseInt(stringNota.get(stringNota.size()-1)));



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
                            int position1 = position;
                            if (i != position1) {
                                stringCredit.add("0");
                            } else {
                                stringCredit.add("0");
                                if(stringCredit.size()==1){
                                    position1=0;
                                }
                                stringCredit.set(position1, s.toString());
                                Log.d("verificare2",":"+stringNota+"  "+stringCredit);
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
                        for (int i = 0; i < stringNota.size() && i < stringCredit.size(); i++) {
                            if(stringCredit.get(i).compareTo("")!=0 && stringNota.get(i).compareTo("")!=0){
                                Log.d("test3",":"+stringNota+"  "+stringCredit);
                                suma = suma + Float.parseFloat(stringNota.get(i)) * Float.parseFloat(stringCredit.get(i));
                                sumaCredit = sumaCredit + Integer.parseInt(stringCredit.get(i));
                            }
                        }
                       // Log.d("creditele",""+crediteleInt);
                        media.setText("Media: "+(suma/sumaCredit));



                    } catch (NumberFormatException e) {

                        for (int i = 0; i <= stringCredit.size() - 1; i++) {
                            int newposition = position;
                            if (i == newposition) {
                                stringCredit.set(newposition, "0");
                            }
                        }


                        float suma = 0, sumaCredit = 0;
                        for (int i = 0; i < stringNota.size() && i < stringCredit.size(); i++) {
                            if(stringCredit.get(i).compareTo("")!=0 && stringNota.get(i).compareTo("")!=0){

                                Log.d("test4",":"+stringNota+"  "+stringCredit);

                                suma = suma + Float.parseFloat(stringNota.get(i)) * Float.parseFloat(stringCredit.get(i));
                                sumaCredit = sumaCredit + Integer.parseInt(stringCredit.get(i));
                            }
                        }
                        Log.d("creditele",""+crediteleInt);
                        media.setText("Media: "+(suma/sumaCredit));
                    }

                }


            }
        });
//        crediteleInt.add(Integer.parseInt(stringCredit.get(stringCredit.size()-1)));
//        counter++;


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


    public void stergere() {
       TextNote.get(TextNote.size()-1).setText("");
        TextNote.remove(TextNote.size()-1);
        stringNota.remove(stringNota.size() - 1);
        TextCredite.get(TextCredite.size()-1).setText("");
        TextCredite.remove(TextCredite.size()-1);
        stringCredit.remove(stringCredit.size()- 1);
        TextMaterie.get(TextMaterie.size()-1).setText("");
        TextMaterie.remove(TextMaterie.size()-1);
//        for(int i=0;i<stringNota.size();i++){
//            if(stringNota.get(i).compareTo("")==0){
//                stringNota.remove(i);
//            }
//        }
//
//        for(int i=0;i<stringCredit.size();i++){
//            if(stringCredit.get(i).compareTo("")==0){
//                stringCredit.remove(i);
//            }
//        }

        Log.d("delete",":"+stringNota+"  "+stringCredit);

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