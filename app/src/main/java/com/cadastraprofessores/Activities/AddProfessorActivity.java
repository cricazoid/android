package com.cadastraprofessores.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cadastraprofessores.Beans.Professor;
import com.cadastraprofessores.Dao.DataBaseHelper;
import com.cadastraprofessores.R;

/*
 * Atividade resposável pela edição da tabela professor
 * Activity responsible for edit the table professor
 */
public class AddProfessorActivity extends AppCompatActivity {
    private Button btnsalvar;
    private EditText etmateria, ettelefone, etnome, etemail;

    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_professor_activity);
        Log.i("addpro Activity create",".............");

        dataBaseHelper = new DataBaseHelper(this);

        btnsalvar = (Button) findViewById(R.id.btnsalvar);
        etnome = (EditText) findViewById(R.id.et_nome);
        etmateria = (EditText) findViewById(R.id.et_materia);
        etemail = (EditText) findViewById(R.id.et_email);
        ettelefone = (EditText) findViewById(R.id.et_telefone);



        btnsalvar.setOnClickListener(v -> {
            Log.i("addpro salvar Act",".......");
            String nome = etnome.getText().toString();
            if (TextUtils.isEmpty(nome)){
                etnome.setError("Digite o nome");
                etnome.requestFocus();
                return;
            }
            Professor prof = new Professor();
            prof.setNome(etnome.getText().toString());
            prof.setMateria(etmateria.getText().toString());
            prof.setEmail(etemail.getText().toString());
            prof.setTelefone(ettelefone.getText().toString());
            Log.i("",prof.toString());
            dataBaseHelper.addProfessor(
                    etnome.getText().toString(),
                    etmateria.getText().toString(),
                    etemail.getText().toString(),
                    ettelefone.getText().toString());
            etnome.setText("");
            etmateria.setText("");
            etemail.setText("");
            ettelefone.setText("");

            Toast.makeText(AddProfessorActivity.this, "Salvo!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddProfessorActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

    }
}
