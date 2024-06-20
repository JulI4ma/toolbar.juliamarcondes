package com.example.officialtoobar.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.officialtoobar.MainActivity
import com.example.officialtoobar.R
import com.example.officialtoobar.api.EnderecoApi
import com.example.officialtoobar.api.RetrofitHelper
import com.example.officialtoobar.databinding.ActivityCadProfessorBinding
import com.example.officialtoobar.model.Aluno
import com.example.officialtoobar.model.Professor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadProfessorActivity : AppCompatActivity() {
 private lateinit var  binding: ActivityCadProfessorBinding
 private var professorId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadProfessorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //criando o menu_principal da tela d app
        fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_principal, menu)
            return true
        }

        binding.btnSalvar1.setOnClickListener {
            val nome = binding.nomeTextView.text.toString()
            val cpf = binding.cpfTextView.text.toString()
            val email = binding.emailTextView.text.toString()
            val matricula = binding.edtCurso1.text.toString()


            if (nome.isNotEmpty() && cpf.isNotEmpty() && email.isNotEmpty() && matricula.isNotEmpty()) {
                val professor = Professor(0, nome, cpf, email) // id is 0 for new records
                salvarProfessor(professor)
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun salvarProfessor(professor: Professor) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoApi::class.java)
        val call = service.inserirProfessor(professor)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setResult(Activity.RESULT_OK)
                    finish() // Return to MainActivity and trigger an update
                } else {
                    Toast.makeText(this@CadProfessorActivity, "Erro ao salvar professor.", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadProfessorActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun alterarProfessor(professor: Professor) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoApi::class.java)
        val call = service.alterarProfessor(professor)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setResult(Activity.RESULT_OK, Intent().putExtra("PROFESSOR_ALTERADO", true))
                    finish()
                } else {
                    Toast.makeText(this@CadProfessorActivity, "Erro ao alterar professor.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadProfessorActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

}

    //Ação de clique nos itens do menu
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            //Testando a ação de clique
            when (item.itemId) {
                R.id.menu_home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_alunos -> {
                    Toast.makeText(this, "Alunos", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Aluno::class.java)
                    startActivity(intent)
                }

                R.id.btnProfessor -> {
                    Toast.makeText(this, "Professores", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, professorActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_turmas -> {
                    Toast.makeText(this, "Turmas", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, turmaActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_cadalunos -> {
                    Toast.makeText(this, "Cadastrar Alunos", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, CadAlunoActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_cadprofessor -> {
                    Toast.makeText(this, "Cadastrar Professor", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ActivityCadProfessorBinding::class.java)
                    startActivity(intent)
                }

                R.id.menu_cadturmas -> {
                    Toast.makeText(this, "Cadastrar Turmas", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, CadTurmaActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_sair -> {
                    Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

            }

            return true
        }
    }
