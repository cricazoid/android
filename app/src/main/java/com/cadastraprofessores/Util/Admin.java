package com.cadastraprofessores.Util;

import android.text.TextWatcher;
import android.content.Context;
import android.widget.ListView;
import android.content.Intent;
import android.text.Editable;

import android.view.MenuItem;
import android.view.Menu;

import com.cadastraprofessores.Activities.AddProfessorActivity;
import com.cadastraprofessores.Activities.EdicaoActivity;
import com.cadastraprofessores.Activities.MainActivity;
import com.cadastraprofessores.Dao.DataBaseHelper;
import com.cadastraprofessores.Beans.Professor;
import com.cadastraprofessores.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
/*
 * Classe que controla as ações do usuário pelos botões
 * */
public class Admin {

    private Context contexto;
    private ListView listViewAdmin;
    private ArrayList<Professor> professoresAdmin, profresult;
    //Classes próprias
    private ProfessorAdapter adapter;
    private DataBaseHelper databaseHelperAd;
    /*
     * Construtor
     * */
    public Admin(MainActivity mainActivity, ListView listView) {

        contexto = mainActivity;
        listViewAdmin = listView;
        databaseHelperAd = new DataBaseHelper(contexto);
        professoresAdmin = databaseHelperAd.getProfessores();
        adapter = new ProfessorAdapter(contexto, professoresAdmin);
        listViewAdmin.setAdapter(adapter);
    }
    /*
     * Determine a ação do click no item da ListView
     * */
    public void acaoListView(){
            listViewAdmin.setOnItemClickListener((parent, view, position, id) -> {
            Intent editarProfessor = new Intent(contexto, EdicaoActivity.class);
            editarProfessor.putExtra("professor",(Professor)
                    listViewAdmin.getItemAtPosition(position));
            contexto.startActivity(editarProfessor);
        });
    }
    /*
     * Ordena a Lista alfabeticamente e inverte a ordem
     * */
    public MenuItem.OnMenuItemClickListener ordenarProfessores(Menu menu, MenuItem item){
        return menuItem -> {
            ArrayList<Professor> listaOrdem = definirLita();
            Collections.sort(listaOrdem, (professor1, t1) -> {
                listViewAdmin.invalidateViews();
                if (item.getItemId() == menu.findItem(R.id.up).getItemId()) // Ascendente
                    return (t1.getNome().compareToIgnoreCase(professor1.getNome()));
                else                                                        // Descendente
                    return (professor1.getNome().compareToIgnoreCase(t1.getNome()));
            });
            return false;
        };
    }
    /*
     * Ação do botão '+', adiciona professor
     * */
    public FloatingActionButton.OnClickListener addProfessor(){
        return (v -> {
            Intent addProfessor = new Intent(contexto, AddProfessorActivity.class);
            contexto.startActivity(addProfessor);
        });
    }
    /*
     * Pequisa na lista em tempo real
     * */
    public TextWatcher pesquisar() {
        return new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                profresult = new ArrayList<>();
                popularPesquisa(charSequence);
                listViewAdmin.setAdapter(definirAdapter(charSequence));
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
        };
    }
    /*
     * Propula lista com os caracteres digitados
     * */
    private void popularPesquisa(CharSequence charSequence){
        for (int aux = 0;aux<adapter.getCount();aux++){
            if(professoresAdmin.get(aux).getNome().contains(charSequence))
                profresult.add(professoresAdmin.get(aux));
        }
    }
    /*
     * Define se a lista ordenada será a completa ou a filtrada pela pesquisa
     * */
    private ArrayList<Professor> definirLita(){
        if (profresult == null)
            return professoresAdmin;
        else
            return profresult;
    }
    /*
     * Define qual adapter estará na ListView
     * */
    private ProfessorAdapter definirAdapter(CharSequence charSequence){
        if(charSequence.length() == 0) {
            profresult = null;
            return adapter;
        }
        else
            return new ProfessorAdapter(contexto,profresult);
    }
}
