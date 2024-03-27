package com.cadastraprofessores.Activities;

import android.widget.EditText;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cadastraprofessores.Dao.DataBaseHelper;
import com.cadastraprofessores.Beans.Professor;

import com.cadastraprofessores.R;

public class EdicaoActivity extends AppCompatActivity {

    private EditText editnome, editmateria, editemail, edittelefone;
    private Button btneditar, btndeletar;
    //Classes próprias
    private Professor professor;
    private DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicaoactivity);

        Intent intent = getIntent();
        professor = (Professor) intent.getSerializableExtra("professor");
        databaseHelper = new DataBaseHelper(this);

        editnome = (EditText) findViewById(R.id.etname);
        editmateria = (EditText) findViewById(R.id.etcourse);
        editemail = (EditText) findViewById(R.id.etemail);
        edittelefone = (EditText) findViewById(R.id.etphone);
        btndeletar = (Button) findViewById(R.id.btndelete);
        btneditar = (Button) findViewById(R.id.btnupdate);

        editnome.setText(professor.getNome());
        editmateria.setText(professor.getMateria());
        editemail.setText(professor.getEmail());
        edittelefone.setText(professor.getTelefone());
        btneditar.setOnClickListener(editarAcoes(btneditar));
        btndeletar.setOnClickListener(editarAcoes(btndeletar));
    }
    public View.OnClickListener editarAcoes(Button button){
        return v -> {
            if (button.getId() == btndeletar.getId() )
                databaseHelper.deletarProfessor(professor.getId());
            else
                editarProf();
            finalizarAcao();

        };
    }
    public void editarProf(){
        databaseHelper.editarProfessor(professor.getId(), editnome.getText().toString(),
                editmateria.getText().toString(), editemail.getText().toString(), edittelefone.getText().toString());
    }
    public void finalizarAcao(){
        Toast.makeText(EdicaoActivity.this, "Suuuuuuuucesso!", Toast.LENGTH_SHORT).show();
        Intent intent12 = new Intent(EdicaoActivity.this,MainActivity.class);
        intent12.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent12);
    }
}
