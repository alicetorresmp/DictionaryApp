package com.alice.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alice.dictionaryapp.databinding.ActivityDefinitionBinding
import com.alice.dictionaryapp.databinding.ActivityMainBinding

class DefinitionActivity : AppCompatActivity() {

    lateinit var binding: ActivityDefinitionBinding
    private val KEY = "WORD_DEFINITION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefinitionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.definitionTextView.text = intent.getStringExtra(KEY)

        binding.backImageView.setOnClickListener{finish()}
    }
}