package com.cadastraprofessores.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import com.cadastraprofessores.R;
import com.cadastraprofessores.Util.Admin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
/*
* Activity principal
* */
public class MainActivity extends AppCompatActivity {
    //Componentes visuais
    private MenuItem cresce, decresce;
    private FloatingActionButton plus;
    private EditText pesquisa;
    private ListView listView;
    //Classes Proprias
    private Admin admin;
    /*
    * OnCreate
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    /*
     * Inicializa Componentes visuais
     * Initialize visual components
     * */
    private void initView(){
        pesquisa = (EditText) findViewById(R.id.pesquisa);
        listView = (ListView) findViewById(R.id.professores);
        plus = (FloatingActionButton) findViewById(R.id.plus);
        admin = new Admin(this,listView);
        plus.setOnClickListener(admin.addProfessor());
        admin.acaoListView();
        pesquisa.addTextChangedListener(admin.pesquisar());

    }
    /*
     * Cria menu na barra do aplicativo
     * Creates bar menu
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        cresce = (MenuItem)menu.findItem(R.id.down);
        decresce = (MenuItem)menu.findItem(R.id.up);
        cresce.setOnMenuItemClickListener(admin.ordenarProfessores(menu, cresce));
        decresce.setOnMenuItemClickListener(admin.ordenarProfessores(menu, decresce));
        return true;
    }
}