package com.alice.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alice.dictionaryapp.databinding.ActivityMainBinding
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val queue = Volley.newRequestQueue(this)

        binding.findButton.setOnClickListener{
            val word = binding.wordEditText.text
            val apiKey = "75908353-c571-4e31-bd82-bc2552e570df"
            val url =
                "https://dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"

            val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener{response ->
                    response
                },
                Response.ErrorListener {error ->
                    error
                }
            )
            queue.add(stringRequest)
        }
    }

    private fun extractDefinitionFromJson(response : String){

    }
}