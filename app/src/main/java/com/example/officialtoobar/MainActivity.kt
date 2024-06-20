package com.example.officialtoobar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.officialtoobar.activity.CadAlunoActivity
import com.example.officialtoobar.activity.CadTurmaActivity
import com.example.officialtoobar.activity.ListagemAlunoActivity
import com.example.officialtoobar.activity.ListagemProfessorActivity
import com.example.officialtoobar.activity.ListagemTurmaActivity
import com.example.officialtoobar.activity.professorActivity
import com.example.officialtoobar.activity.turmaActivity
import com.example.officialtoobar.databinding.ActivityCadProfessorBinding
import com.example.officialtoobar.databinding.ActivityMainBinding
import com.example.officialtoobar.model.Aluno
import com.example.officialtoobar.model.Professor
import com.example.officialtoobar.model.Turma

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btnAluno.setOnClickListener {
            val intent = Intent(this, ListagemAlunoActivity::class.java)
            startActivity(intent)
        }

        binding.btnProfessor.setOnClickListener {
            val intent = Intent(this, ListagemProfessorActivity::class.java)
            startActivity(intent)
        }

        binding.btnTurma.setOnClickListener {
            val intent = Intent(this, ListagemTurmaActivity::class.java)
            startActivity(intent)
        }

    }


}


