package com.example.officialtoobar.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.officialtoobar.api.EnderecoApi
import com.example.officialtoobar.api.RetrofitHelper
import com.example.officialtoobar.databinding.ActivityCadAlunoBinding
import com.example.officialtoobar.model.Aluno
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadAlunoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadAlunoBinding
        private var alunoid : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val nome = binding.edtNome.text.toString()
            val cpf = binding.edtCpf.text.toString()
            val email = binding.edtEmail.text.toString()
            val matricula = binding.edtCurso.text.toString()

            if (nome.isNotEmpty() && cpf.isNotEmpty() && email.isNotEmpty() && matricula.isNotEmpty()) {
                val aluno = Aluno(0, nome, cpf, email, matricula) // id is 0 for new records
                salvarAluno(aluno)
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun salvarAluno(aluno: Aluno) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoApi::class.java)
        val call = service.inserirAluno(aluno)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setResult(Activity.RESULT_OK)
                    finish() // Return to MainActivity and trigger an update
                } else {
                    Toast.makeText(this@CadAlunoActivity, "Erro ao salvar aluno.", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadAlunoActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun alterarAluno(aluno: Aluno) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoApi::class.java)
        val call = service.alterarAluno(aluno)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    setResult(Activity.RESULT_OK, Intent().putExtra("ALUNO_ALTERADO", true))
                    finish()
                } else {
                    Toast.makeText(this@CadAlunoActivity, "Erro ao alterar aluno.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@CadAlunoActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}