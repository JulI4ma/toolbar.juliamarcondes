package com.example.officialtoobar.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.officialtoobar.R
import com.example.officialtoobar.databinding.ActivityItemTurmaBinding

class ItemTurmaActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityItemTurmaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_turma)
    }
}