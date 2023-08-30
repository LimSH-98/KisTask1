package com.example.kistask1

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.kistask1.adapter.CustomAdapter
import com.example.kistask1.databinding.FragmentDetailBinding
import com.example.kistask1.model.Board
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar
import java.util.Objects
import kotlin.concurrent.fixedRateTimer


class DetailFragment : Fragment(){

    private val TAG = "Detail Fragment"
    private lateinit var viewBinding: FragmentDetailBinding
    private lateinit var index : String
    private lateinit var url : String
    private lateinit var name : String
    private lateinit var btnClickListener: OnBtnClickListener
    private var dataFormat = SimpleDateFormat("yyyy-MM-dd")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentDetailBinding.inflate(inflater, container, false)

        index = arguments?.getString("index").toString()
        url = arguments?.getString("url").toString()
        name = arguments?.getString("name").toString()
        var title = arguments?.getString("title")
        var birthday = arguments?.getString("birthday")

        Log.e(TAG, index) // 3
        Log.e(TAG, url) // C:\study\image\haha.jpeg
        Log.e(TAG, title.toString()) // 하하
        Log.e(TAG, name.toString()) // haha.jpeg
        Log.e(TAG, birthday.toString()) // birthday

        var board = Board(index, title!!, birthday!!, name!!, url)

        viewBinding.title.setText(title)
        viewBinding.birthday.setText(birthday)

        if(name == "haha.jpeg"){
            Glide.with(container!!.context)
                .load(R.drawable.haha)
                .into(viewBinding.image)
        }else if(name == "jo.png"){
            Glide.with(container!!.context)
                .load(R.drawable.jo)
                .into(viewBinding.image)
        }else if(name == "jun.jpg"){
            Glide.with(container!!.context)
                .load(R.drawable.jun)
                .into(viewBinding.image)
        }else if(name == "no.jpg"){
            Glide.with(container!!.context)
                .load(R.drawable.no)
                .into(viewBinding.image)
        }else if(name == "park.jpg"){
            Glide.with(container!!.context)
                .load(R.drawable.park)
                .into(viewBinding.image)
        }else if(name == "yang.jpg"){
            Glide.with(container!!.context)
                .load(R.drawable.yang)
                .into(viewBinding.image)
        }else if(name == "you.jpg"){
            Glide.with(container!!.context)
                .load(R.drawable.you)
                .into(viewBinding.image)
        }

        viewBinding.reWriteBtn.setOnClickListener {
            var title = viewBinding.title.text
            var birthday = viewBinding.birthday.text

            val board = Board(index, title.toString(), birthday.toString(), name, title.toString())

            btnClickListener.onClick(it, board)
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }

        return viewBinding.root
    }
    private fun checkBtn(){}
    interface OnBtnClickListener{
        fun onClick(view: View?, board: Board)
    }

    fun setBtnClickListener(btnClickListener: OnBtnClickListener){
        this.btnClickListener = btnClickListener
    }
}

