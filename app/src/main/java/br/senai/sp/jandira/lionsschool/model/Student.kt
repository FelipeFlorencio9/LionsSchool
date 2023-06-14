package br.senai.sp.jandira.lionsschool.model

data class Student (
    val id: Long,
    val foto: String,
    val nome: String,
    val matricula: Long,
    val sexo: String,
    val curso : List<CourseStudent>,
    val status : String
)
