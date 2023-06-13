package br.senai.sp.jandira.lionsschool.model

data class CourseStudent (
    val id : Long,
    var nome: String,
    val sigla : String,
    val icone: String,
    val carga : Int,
    val disciplina : List<Discipline>
)