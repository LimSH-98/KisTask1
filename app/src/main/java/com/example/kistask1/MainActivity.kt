package com.example.kistask1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ScrollView
import android.widget.Toast
import com.example.kistask1.adapter.CustomAdapter
import com.example.kistask1.auth.FirebaseAuthUtils
import com.example.kistask1.databinding.ActivityMainBinding
import com.example.kistask1.model.Board
import com.example.kistask1.model.User
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.GsonBuilder
import org.json.JSONArray
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(){

    private val TAG = "Main Activity"

    private lateinit var customAdapter : CustomAdapter

    private var data = mutableListOf<Board>()

    private val uid = FirebaseAuthUtils.getUid()

    private val detailFragment = DetailFragment()

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
                    Log.e(TAG, "11" + response.body().toString())
                    showViewList(response, user)
                }
                else{
                    Log.e(TAG, response.body().toString())
                }
            }
            override fun onFailure(call: Call<List<Board>>, t: Throwable) {
                Log.e(TAG, t.toString())
            }
        })
        moveHome()
        moveBack() // 로그인 화면으로 이동.
        switchScreen()
    }

    private fun switchScreen(){
        viewBinding.switchBtn.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked){
                
            }else{

            }
        }
    }
    private fun moveHome(){

        detailFragment.setBtnClickListener(object: DetailFragment.OnBtnClickListener{
            override fun onClick(view: View?, board: Board) {

                val call = RetrofitBuilder.api.updateBoardResponse(board)

                call.enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if(response.isSuccessful){
                            Log.d(TAG, response.body().toString())
                            startActivity(intent)
                        } else{
                            Log.d(TAG, response.toString())
                        }
                    }
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d(TAG, t.toString())
                    }
                })
            }
        })
    }

    private fun showViewList(response: Response<List<Board>>, user : User){
        // response로 온 json 데이터를 list형식으로 변환
        customAdapter = CustomAdapter(this)
        viewBinding.recyclerView.adapter = customAdapter

        var gson = GsonBuilder().create()
        var jsonData = gson.toJson(response.body())

        var jsonArray = JSONTokener(jsonData).nextValue() as JSONArray

        for(i in 0 until jsonArray.length()){
            var id = jsonArray.getJSONObject(i).getString("id")
            var title = jsonArray.getJSONObject(i).getString("title")
            var birthday = jsonArray.getJSONObject(i).getString("birthday")
            var imgName = jsonArray.getJSONObject(i).getString("img_name")
            var imgUrl = jsonArray.getJSONObject(i).getString("img_url")

            var board = Board(id, title, birthday, imgName, imgUrl)
            data.add(board)
        }

        customAdapter.setItemClickListener(object: CustomAdapter.OnItemClickListener{
            override fun onClick(view: View?, pos: Int) {
                // 클릭 이벤트 처리 완료, 액티비티에서 프레그먼트로 이동 및 데이터 전달만 하면됨.

                val bundle = Bundle()
                val url = data[pos].imgUrl + data[pos].imgName

                bundle.putString("index", data[pos].id)
                bundle.putString("title", data[pos].title)
                bundle.putString("url", url)
                bundle.putString("name", data[pos].imgName)
                bundle.putString("birthday", data[pos].birthday)

                Log.e(TAG, data[pos].title)

                val transaction = supportFragmentManager.beginTransaction()

                detailFragment.arguments = bundle
                transaction.replace(R.id.detail_layout, detailFragment)
                    .commit()
            }
        })

        data.apply {
            customAdapter.data = data
            customAdapter.notifyDataSetChanged()
        }

    }

    private fun moveBack(){
        with(viewBinding){
            this.backBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

