package br.senai.sp.jandira.lionsschool.service

import br.senai.sp.jandira.lionsschool.model.CourseList
import br.senai.sp.jandira.lionsschool.model.Student
import br.senai.sp.jandira.lionsschool.model.StudentList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CourseService{
    @GET("cursos")
    fun getCourses(): Call<CourseList>

    @GET("alunos/{id}")
    fun getStudent(@Path("id") id: Int)  : Call<Student>

    @GET("character/")
    fun getCharactersByPage(@Query("page") page: Int): Call<StudentList>

}