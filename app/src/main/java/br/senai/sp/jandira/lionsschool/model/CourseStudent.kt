package br.senai.sp.jandira.lionsschool.model

data class CourseStudent (
    var nome: String,
    val sigla : String,
    val icone: String,
    val carga : Int,
    val conclusao : Int?,
    val disciplinas : List<Discipline>
    
)