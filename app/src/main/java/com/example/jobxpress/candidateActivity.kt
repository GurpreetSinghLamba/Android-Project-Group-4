package com.example.jobxpress

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class candidateActivity : AppCompatActivity() {
    private var adapter: CandidateAdapter? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate)

        auth = FirebaseAuth.getInstance()
        var query = FirebaseDatabase.getInstance().reference.child("candidates")
        val options = FirebaseRecyclerOptions.Builder<candidate_class>()
            .setQuery(query, candidate_class::class.java).build()

        adapter = CandidateAdapter(options)
        val rView: RecyclerView = findViewById(R.id.candidateRecyclerView)
            rView.layoutManager = GridLayoutManager(this, 2)
            rView.adapter = adapter
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter?.stopListening()
    }
}
