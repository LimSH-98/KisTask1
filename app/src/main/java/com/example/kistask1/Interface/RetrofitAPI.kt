package com.examle.kistask1.Interface

import com.example.kistask1.model.Board
import com.example.kistask1.model.SendBoardModel
import com.example.kistask1.model.User
import retrofit2.Call
import retrofit2.http.Body

import retrofit2.http.POST
import retrofit2.http.Query


interface RetrofitAPI {
    // 어떤 파라미터와 함께 어떤 메서드로 요청을 보내고, 어떤 형태로 응답 받을 것인지 정의.
    @POST("board")
    fun getUserResponse(@Body user: User) : Call<String>

//    @POST("boardList")
//    fun getBoardListResponse(@Body user : User) : Call<List<Board>>

    @POST("boardList")
    fun getBoardListResponse(@Body user : User) : Call<List<Board>>

    @POST("updateBoard")
    fun updateBoardResponse(@Body board: Board) : Call<String>
}