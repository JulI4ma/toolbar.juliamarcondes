package com.example.officialtoobar.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.officialtoobar.MainActivity
import com.example.officialtoobar.R
import com.example.officialtoobar.databinding.ActivityProfessorBinding
import com.example.officialtoobar.databinding.ActivityTurmaBinding
import com.example.officialtoobar.model.Aluno

class turmaActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityTurmaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    //criando o menu_principal da tela d app
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    //Ação de clique nos itens do menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Testando a ação de clique
        when(item.itemId){
            R.id.menu_home -> {
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                val intent = Intent (this, MainActivity::class.java)
                startActivity(intent)
            }

                R.id.menu_alunos -> {
                    Toast.makeText(this, "Alunos", Toast.LENGTH_SHORT).show()
                    val intent = Intent (this, Aluno::class.java)
                    startActivity(intent)
                }

                R.id.menu_professores -> {
                    Toast.makeText(this, "Professores", Toast.LENGTH_SHORT).show()
                    val intent = Intent (this, professorActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_turmas -> {
                    Toast.makeText(this, "Turmas", Toast.LENGTH_SHORT).show()
                    val intent = Intent (this, turmaActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_cadalunos -> {
                    Toast.makeText(this, "Cadastrar Alunos", Toast.LENGTH_SHORT).show()
                    val intent = Intent (this, CadAlunoActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_cadprofessor -> {
                    Toast.makeText(this, "Cadastrar Professor", Toast.LENGTH_SHORT).show()
                    val intent = Intent (this, CadProfessorActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_cadturmas-> {
                    Toast.makeText(this, "Cadastrar Turmas", Toast.LENGTH_SHORT).show()
                    val intent = Intent (this, CadTurmaActivity::class.java)
                    startActivity(intent)
                }

                R.id.menu_sair -> {
                    Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show()
                    val intent = Intent (this, MainActivity::class.java)
                    startActivity(intent)
                }

            }

            return true
        }
    }
