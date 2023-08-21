package com.example.kistask1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kistask1.auth.FirebaseAuthUtils
import com.example.kistask1.databinding.ActivityMainBinding
import com.example.kistask1.model.Board
import com.example.kistask1.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = "메인 액티비티"

    private val uid = FirebaseAuthUtils.getUid()

    private lateinit var viewBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val user = User(uid)

        val call = RetrofitBuilder.api.getBoardListResponse(user)

        call.enqueue(object : Callback<List<Board>> {
            override fun onResponse(call: Call<List<Board>>, response: Response<List<Board>>) {
                if(response.isSuccessful){
                    Log.d(TAG, response.body().toString())
                }
                else{
                    Log.d(TAG, response.toString())
                }
            }

            override fun onFailure(call: Call<List<Board>>, t: Throwable) {
                Log.d(TAG, t.toString())
            }
        })

        getAllBoard()
        moveBack() // 로그인 화면으로 이동.
        moveWritePage()

    }

    private fun getAllBoard(){

    }
    private fun moveBack(){
        with(viewBinding){
            this.backBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun moveWritePage(){
        with(viewBinding){
            this.writeBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, WritePageActivity::class.java)
                startActivity(intent)
            }
        }
    }
}