package com.example.officialtoobar.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.officialtoobar.adapter.TurmaAdapter
import com.example.officialtoobar.api.EnderecoApi
import com.example.officialtoobar.api.RetrofitHelper
import com.example.officialtoobar.databinding.ActivityListagemTurmaBinding
import com.example.officialtoobar.model.Turma
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListagemTurmaActivity: AppCompatActivity() {
    private lateinit var binding: ActivityListagemTurmaBinding
    private lateinit var turmaAdapter: TurmaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListagemTurmaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadTurma()

        binding.btnCadTurma.setOnClickListener {
            val intent = Intent(this, CadTurmaActivity::class.java)
            cadastroTurmaActivityResultLauncher.launch(intent)
        }
    }

    private val cadastroTurmaActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Atualizar a lista de turma após cadastro/edição
                loadTurma()
            }
        }

    private fun setupRecyclerView() {
        turmaAdapter = TurmaAdapter( this) { turmaId ->
            deleteTurma(turmaId)
        }
        binding.turmaRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.turmaRecyclerView.adapter = turmaAdapter
    }

    private fun loadTurma() {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoApi::class.java)
        val call = service.getTurma()

        call.enqueue(object : Callback<List<Turma>> {
            override fun onResponse(call: Call<List<Turma>>, response: Response<List<Turma>>) {
                if (response.isSuccessful) {
                    response.body()?.let { turma ->
                        turmaAdapter.setData(turma)
                    }
                } else {
                    Toast.makeText(
                        this@ListagemTurmaActivity,
                        "Falha ao carregar turma",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Turma>>, t: Throwable) {
                Toast.makeText(this@ListagemTurmaActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun deleteTurma(turmaId: Int) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoApi::class.java)
        val call = service.excluirTurma(turmaId)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@ListagemTurmaActivity,
                        "Turma excluída com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    loadTurma() // Atualizar a lista de turmas após exclusão
                } else {
                    Toast.makeText(
                        this@ListagemTurmaActivity,
                        "Falha ao excluir turma",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ListagemTurmaActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}