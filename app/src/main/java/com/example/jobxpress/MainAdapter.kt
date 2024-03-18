package com.example.jobxpress

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainAdapter(options: FirebaseRecyclerOptions<MainActivityClass>) :
    FirebaseRecyclerAdapter<MainActivityClass, MainAdapter.MyViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.main_activity_row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: MainActivityClass) {
        holder.textViewDetailName.text = model.detail_name
        holder.textViewContent.text = model.content
        holder.textViewtimestamp.text = convertTimestampToString(model.timestamp)
        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context,MainActivityClass::class.java).apply {
                putExtra("user_id", model.userid)
                putExtra("p_detail", model.detail_name)
                putExtra("p_content", model.content)
                putExtra("p_timeStamp", model.timestamp)
            }
            view.context.startActivity(intent)
        }
    }
    private fun convertTimestampToString(timestamp: Long): String {
        val date = Date(timestamp)

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return sdf.format(date)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDetailName: TextView = itemView.findViewById(R.id.detail_name)
        val textViewContent: TextView = itemView.findViewById(R.id.content)
        val textViewtimestamp: TextView = itemView.findViewById(R.id.timestamp)
    }
}
