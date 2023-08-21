package com.example.kistask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.kistask1.databinding.ActivityWritePageBinding
import com.example.kistask1.model.Board
import retrofit2.http.POST

class WritePageActivity : AppCompatActivity() {

    private lateinit var viewBinding : ActivityWritePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityWritePageBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var image = viewBinding.image

        val getAction = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback { uri ->
                image.setImageURI(uri)
            }
        )

        image.setOnClickListener {
            getAction.launch("image/*")
        }

        writePage()
    }
    private fun writePage(){
        with(viewBinding){

            val title : String = this.title.text.toString()
            val birthday : String = this.birthday.text.toString()

        }
    }
}