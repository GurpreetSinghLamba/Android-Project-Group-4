package com.example.jobxpress
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val query = FirebaseDatabase.getInstance().reference.child("posts")
        val options = FirebaseRecyclerOptions.Builder<MainActivityClass>()
            .setQuery(query, MainActivityClass::class.java)
            .build()
        adapter = MainAdapter(options)

        val recyclerView: RecyclerView = findViewById(R.id.posts_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val candidateButton: Button = findViewById(R.id.candidate_btn)
        candidateButton.setOnClickListener {
            val intent = Intent(this, candidateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }
}
