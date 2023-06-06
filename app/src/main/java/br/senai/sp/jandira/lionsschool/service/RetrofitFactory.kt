package br.senai.sp.jandira.lionsschool.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL_BASE = "https://weak-gold-python-sari.cyclic.app//v1/lions-school/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getStudentService() : StudentService {
        return retrofitFactory.create(StudentService::class.java)
    }
    fun getCourseService() : CourseService {
        return retrofitFactory.create(CourseService::class.java)
    }
}