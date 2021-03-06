package com.alice.dictionaryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alice.dictionaryapp.databinding.ActivityMainBinding
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val KEY = "WORD_DEFINITION"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val queue = Volley.newRequestQueue(this)

        val url = getUrl()
        binding.findButton.setOnClickListener {

            val stringRequest = StringRequest(Request.Method.GET, url,
                {response ->
                    try {
                        extractDefinitionFromJson(response)
                    }catch (exception : Exception){
                        exception.printStackTrace()
                    }
                },
                {error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                }
            )
            queue.add(stringRequest)
        }
    }

    private fun getUrl(): String {
        val word = binding.wordEditText.text
        val apiKey = "75908353-c571-4e31-bd82-bc2552e570df"
        return "https://dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"
    }

    private fun extractDefinitionFromJson(response: String) {
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDefinition = firstIndex.getJSONArray("shortdef")
        val firstShortDefinition = getShortDefinition.get(0)

        val intent = Intent(this, DefinitionActivity::class.java)
        intent.putExtra(KEY, firstShortDefinition.toString())
        startActivity(intent)
    }
    }