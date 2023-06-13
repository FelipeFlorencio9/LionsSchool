package br.senai.sp.jandira.lionsschool.service

import br.senai.sp.jandira.lionsschool.model.Student
import br.senai.sp.jandira.lionsschool.model.StudentList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentService {

    //https://school-lions.onrender.com/v1/lions-school

    @GET("alunos")
    fun getStudents(): Call<StudentList>

    @GET("alunos/{id}")
    fun getStudent(@Path("id") id: Int)  : Call<Student>

    @GET("alunos")
    fun getStudentsFromCourse(@Query("curso") siglaDoCurso: String): Call<StudentList>


}