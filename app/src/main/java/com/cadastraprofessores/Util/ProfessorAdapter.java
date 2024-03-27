package com.cadastraprofessores.Util;

import android.view.LayoutInflater;
import android.content.Context;
import android.graphics.Color;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.cadastraprofessores.Beans.Professor;
import com.cadastraprofessores.R;

import java.util.ArrayList;

/*
 * Adaptador da entidade Professor
 * */
public class ProfessorAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Professor> professores;
    private int cor1 = Color.parseColor("#ffffff");
    private int cor2 = Color.parseColor("#f1f2f3");

    /*
     * Construtor
     * */
    public ProfessorAdapter(Context context, ArrayList<Professor> professores) {

        this.context = context;
        this.professores = professores;
    }
    /*
     * Getters
     * */
    @Override
    public int getCount() {
        return professores.size();
    }

    @Override
    public Object getItem(int position) {
        return professores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    /*
     * Retorna a View do item
     * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
         //   initHolder(holder, position, convertView);
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.professores, null, true);
            convertView.setBackgroundColor(CheckColor(position));//Muda a cor dos itens
            initView(holder, convertView);
//            holder.tvnome = (TextView) convertView.findViewById(R.id.professores_nome);
//            holder.tvemail = (TextView) convertView.findViewById(R.id.professores_email);
//            holder.tvmataria = (TextView) convertView.findViewById(R.id.professores_materia);
//            holder.tvtelefone = (TextView) convertView.findViewById(R.id.professores_telefone);

            convertView.setTag(holder);
        }else
            holder = (ViewHolder)convertView.getTag();// retorna o viewHolder existente
        preencerTextos(holder, position);
//        holder.tvnome.setText("Nome: "+ professores.get(position).getNome());
//        holder.tvemail.setText("Email: "+ professores.get(position).getEmail());
//        holder.tvmataria.setText("Matéria: "+ professores.get(position).getMateria());
//        holder.tvtelefone.setText("Telefone:"+ professores.get(position).getTelefone());
        return convertView;
    }
    /*
     * Inicializa holder e
     * */
    private void initHolder(ViewHolder holder, int position, View convertView){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.professores, null, true);
        convertView.setBackgroundColor(CheckColor(position));//Muda a cor dos itens
     //   initView(holder, convertView);
    }
    /*
     * Inicializa TextViewa
     * */
    private void initView(ViewHolder holder, View convertView){
        holder.tvnome = (TextView) convertView.findViewById(R.id.professores_nome);
        holder.tvemail = (TextView) convertView.findViewById(R.id.professores_email);
        holder.tvmataria = (TextView) convertView.findViewById(R.id.professores_materia);
        holder.tvtelefone = (TextView) convertView.findViewById(R.id.professores_telefone);
    }
    /*
     * Preenche os texto da lista
     * */
    private void preencerTextos(ViewHolder holder, int position){
        holder.tvnome.setText("Nome: "+ professores.get(position).getNome());
        holder.tvemail.setText("Email: "+ professores.get(position).getEmail());
        holder.tvmataria.setText("Matéria: "+ professores.get(position).getMateria());
        holder.tvtelefone.setText("Telefone:"+ professores.get(position).getTelefone());
    }
    /*
     * ViewHolder
     * */
    private class ViewHolder {
        protected TextView tvnome, tvmataria, tvemail, tvtelefone;
    }
    /*
     * Define a cor do item da lista
     * */
    private int CheckColor(int position){
        if ((position % 2) == 0)
            return cor1;
        else
            return cor2;
    }
}
