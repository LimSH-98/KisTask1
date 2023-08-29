package com.example.kistask1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kistask1.R
import com.example.kistask1.model.Board

class CustomAdapter(val context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    val TAG = "CustomAdapter"

    var data = mutableListOf<Board>()

    private lateinit var itemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener{
        fun onClick(view: View?, pos: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val index : TextView = itemView.findViewById(R.id.index)
        private val title : TextView = itemView.findViewById(R.id.title)
        private val birth : TextView = itemView.findViewById(R.id.birth)
        private val image : ImageView = itemView.findViewById(R.id.img)
        fun bind(board: Board){
            index.text = board.id
            var url = board.imgUrl + board.imgName
            var name = board.imgName
            // 1. stream으로 읽기 외부저장소
            // 2. glide로 사진 저장된 위치, ip주소 + 사진 위치
//            val uri = Uri.parse("url")

            title.text = board.title
            birth.text = board.birthday

            if(name == "haha.jpeg"){
                Glide.with(context)
                    .load(R.drawable.haha)
                    .into(image)
            }else if(name == "jo.png"){
                Glide.with(context)
                    .load(R.drawable.jo)
                    .into(image)
            }else if(name == "jun.jpg"){
                Glide.with(context)
                    .load(R.drawable.jun)
                    .into(image)
            }else if(name == "no.jpg"){
                Glide.with(context)
                    .load(R.drawable.no)
                    .into(image)
            }else if(name == "park.jpg"){
                Glide.with(context)
                    .load(R.drawable.park)
                    .into(image)
            }else if(name == "yang.jpg"){
                Glide.with(context)
                    .load(R.drawable.yang)
                    .into(image)
            }else if(name == "you.jpg"){
                Glide.with(context)
                    .load(R.drawable.you)
                    .into(image)
            }else if(name == "tayeon.jpg"){
                Glide.with(context)
                    .load(R.drawable.tayeon)
                    .into(image)
            }else if(name == "nilo.jpg"){
                Glide.with(context)
                    .load(R.drawable.nilo)
                    .into(image)
            }else if(name == "yoon.jpg"){
                Glide.with(context)
                    .load(R.drawable.yoon)
                    .into(image)
            }else if(name == "dail.png"){
                Glide.with(context)
                    .load(R.drawable.dail)
                    .into(image)
            }else if(name == "chan.jpg"){
                Glide.with(context)
                    .load(R.drawable.chan)
                    .into(image)
            }
        }
    }
    fun updateBoard(obj: Board) {

    }
}


