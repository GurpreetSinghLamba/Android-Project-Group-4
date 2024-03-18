package com.example.jobxpress

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CandidateDetailsActivity : AppCompatActivity() {
    private lateinit var connectionsRef: DatabaseReference
    private lateinit var candidateId: String
    private lateinit var candidateName: String
    private lateinit var candidateTitle: String
    private lateinit var candidateImageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val database = FirebaseDatabase.getInstance()
        connectionsRef = database.getReference("connections")

        candidateId = intent.getStringExtra("c_id") ?: ""
        candidateName = intent.getStringExtra("c_name") ?: ""
        candidateTitle = intent.getStringExtra("c_title") ?: ""
        candidateImageUrl = intent.getStringExtra("c_imageURL") ?: ""

        val textViewName = findViewById<TextView>(R.id.detail_name)
        textViewName.text = candidateName

        val textViewTitle = findViewById<TextView>(R.id.detail_title)
        textViewTitle.text = candidateTitle

        val imageViewPhoto = findViewById<ImageView>(R.id.detail_photo)
        Glide.with(this).load(candidateImageUrl).into(imageViewPhoto)

        val connectButton = findViewById<Button>(R.id.detail_connect_btn)
        connectButton.setOnClickListener {
            updateConnectionToTrue(candidateId)
        }
    }

    private fun updateConnectionToTrue(candidateId: String) {
        connectionsRef.child(candidateId).setValue(true)
        Toast.makeText(this, "Connected with candidate $candidateId", Toast.LENGTH_SHORT).show()
    }
}