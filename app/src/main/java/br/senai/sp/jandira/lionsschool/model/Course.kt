package br.senai.sp.jandira.lionsschool.model

data class Course (
    val id : Long,
    val nome: String,
    val sigla : String,
    val icone: String,
    val carga : Int,
    )