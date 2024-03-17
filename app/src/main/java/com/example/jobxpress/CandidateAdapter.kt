package com.example.jobxpress

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage

class CandidateAdapter(options:FirebaseRecyclerOptions<candidate_class>)
    :FirebaseRecyclerAdapter<candidate_class,CandidateAdapter.MyViewHolder>(options){


 override fun onCreateViewHolder(parent:ViewGroup,viewType: Int):MyViewHolder{
            val inflater=LayoutInflater.from(parent.context)
            return MyViewHolder(inflater,parent)
        }
override fun onBindViewHolder(holder:MyViewHolder,parent:Int,model:candidate_class){
    holder.textViewName.text=model.name
    holder.textViewTitle.text=model.title
    val theImage:String=model.imageURL
    if(theImage.indexOf("gs://")>-1){
        val storageRef=FirebaseStorage.getInstance().getReferenceFromUrl(theImage)
        Glide.with(holder.candidatePhoto.context)
            .load(storageRef)
            .into(holder.candidatePhoto)
    }
    holder.itemView.setOnClickListener{view->
        val intent=Intent(view.context,DetailActivity::class.java)
        intent.putExtra("c_id",model.id)
        intent.putExtra("c_name",model.name)
        intent.putExtra("c_imageURL",model.imageURL)
        intent.putExtra("c_title",model.title)
        view.context.startActivity(intent)
    }
}
class MyViewHolder(inflater: LayoutInflater,parent:ViewGroup)
    :RecyclerView.ViewHolder(inflater.inflate(R.layout.candidate_activity_row_layout,parent,false)){
        val textViewName:TextView=itemView.findViewById(R.id.content)
val textViewTitle:TextView=itemView.findViewById(R.id.detail_name)
    val candidatePhoto:ImageView=itemView.findViewById(R.id.imageView)
    }

    }