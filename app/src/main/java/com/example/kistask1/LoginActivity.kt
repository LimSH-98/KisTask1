package com.example.kistask1

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import com.example.kistask1.databinding.ActivityLoginBinding
import com.example.kistask1.model.User
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private val TAG = "로그인 액티비티"
    private lateinit var viewBinding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        auth = FirebaseAuth.getInstance()

        with(viewBinding){

            this.loginBtn.setOnClickListener() {
                val id = this.userIdArea.text.toString()
                val pw = this.userPwArea.text.toString()
                if(id == "" || pw == "") {
                    loginErrorDialog()
                }else{
                    login(id, pw)
                }
            }

        }
    }

    private fun createUser(){
        
    } // 사용자 계정 생성 부분
    private fun login(id : String, pw : String){
//        auth.createUserWithEmailAndPassword(id, pw)
//            .addOnCompleteListener{task ->
//                if(task.isSuccessful){
//                    Log.d(TAG, "성공2")
//                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                    startActivity(intent)
//                }else{
//                    Log.d(TAG, "성공3")
//                }
//            } // -> 사용자 계정 생성
        auth.signInWithEmailAndPassword(id, pw)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    // 이메일이 틀렸는지, 비밀번호가 틀렸는지
                    // this.findViewById<TextInputEditText>(R.id.userIdArea).isVisible = true
                }
            }
    }

    private fun loginErrorDialog(){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.login_error_dialog, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("로그인 오류")
        val alertDialog = builder.show()
        dialogView.findViewById<Button>(R.id.checkBtn).setOnClickListener {
            alertDialog.dismiss()
        }
    }
}

private fun <T> Call<T>.enqueue(any: Any) {

}
