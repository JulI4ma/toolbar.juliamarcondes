package com.example.officialtoobar.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.officialtoobar.R
import com.example.officialtoobar.adapter.AlunoAdapter
import com.example.officialtoobar.api.EnderecoApi
import com.example.officialtoobar.api.RetrofitHelper
import com.example.officialtoobar.databinding.ActivityListagemAlunoBinding
import com.example.officialtoobar.model.Aluno
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListagemAlunoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListagemAlunoBinding
    private lateinit var alunoAdapter: AlunoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListagemAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadAlunos()

        binding.btnCadastroAluno.setOnClickListener {
            val intent = Intent(this, CadAlunoActivity::class.java)
            cadastroAlunoActivityResultLauncher.launch(intent)
        }
    }

    private val cadastroAlunoActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Atualizar a lista de alunos após cadastro/edição
                loadAlunos()
            }
        }

    private fun setupRecyclerView() {
        alunoAdapter = AlunoAdapter(this) { alunoId ->
            deleteAluno(alunoId)
        }
        binding.alunosRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.alunosRecyclerView.adapter = alunoAdapter
    }

    private fun loadAlunos() {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoApi::class.java)
        val call = service.getAlunos()

        call.enqueue(object : Callback<List<Aluno>> {
            override fun onResponse(call: Call<List<Aluno>>, response: Response<List<Aluno>>) {
                if (response.isSuccessful) {
                    response.body()?.let { alunos ->
                        alunoAdapter.setData(alunos)
                    }
                } else {
                    Toast.makeText(
                        this@ListagemAlunoActivity,
                        "Falha ao carregar alunos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Aluno>>, t: Throwable) {
                Toast.makeText(this@ListagemAlunoActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun deleteAluno(alunoId: Int) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoApi::class.java)
        val call = service.excluirAluno(alunoId)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@ListagemAlunoActivity,
                        "Aluno excluído com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    loadAlunos() // Atualizar a lista de alunos após exclusão
                } else {
                    Toast.makeText(
                        this@ListagemAlunoActivity,
                        "Falha ao excluir aluno",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ListagemAlunoActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}