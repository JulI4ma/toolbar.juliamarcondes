package com.example.officialtoobar.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.officialtoobar.activity.CadTurmaActivity
import com.example.officialtoobar.databinding.ActivityItemTurmaBinding
import com.example.officialtoobar.model.Turma

class TurmaAdapter(
    private val context: Context,
    private val deleteCallback: (Int) -> Unit
) : RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder>() {

    private var turma: List<Turma> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TurmaViewHolder {
        val binding = ActivityItemTurmaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TurmaViewHolder(binding)
    }
    override fun onBindViewHolder(holder: TurmaViewHolder, position: Int) {
        val turma = turma[position]
        holder.bind(turma)

        holder.binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Excluir Turma")
                .setMessage("Deseja realmente excluir o aluno ${turma.curso}?")
                .setPositiveButton("Sim") { _, _ ->
                    deleteCallback(turma.id)
                }
                .setNegativeButton("Não", null)
                .show()
        }

        holder.binding.btnEditar.setOnClickListener {
            val intent = Intent(context, CadTurmaActivity::class.java)
            intent.putExtra("TURMA_ID", turma.id)
            intent.putExtra("TURMA_CURSO", turma.curso)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return turma.size
    }

    fun setData(turma: List<Turma>) {
        this.turma = turma
        notifyDataSetChanged()
    }

    inner class TurmaViewHolder(val binding: ActivityItemTurmaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(turma: Turma) {
            binding.apply {
                cursoTextView.text = turma.curso

            }
        }
    }
}
