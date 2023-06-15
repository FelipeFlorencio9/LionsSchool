package br.senai.sp.jandira.lionsschool.repository

import androidx.compose.runtime.Composable
import br.senai.sp.jandira.lionsschool.model.Course
import br.senai.sp.jandira.lionsschool.model.CourseStudent
import br.senai.sp.jandira.lionsschool.model.Discipline
import br.senai.sp.jandira.lionsschool.model.Student

class StudentsRepository {

    companion object{
        @Composable
        fun getStudents(): List<Student> {
            return listOf(
                Student(
                    id = 1234,
                    foto = "teste",
                    "Giovana",
                    1234,
                    "f",
                    curso = listOf<CourseStudent>(
                            CourseStudent(
                                nome = "Desenvolvimento",
                                sigla = "DS",
                                icone = "url",
                                1200,
                                2023,
                                listOf<Discipline>(
                                    Discipline("Hardware e Redes", 1200, 50, "Aprovado"),
                                    Discipline("Programacao front end", 1200, 90, "Aprovado"),
                                    Discipline("Programacao Back end", 1200, 70, "Aprovado"),)
                            )
                    ),
                    status = "Cursando"),
                Student(
                    id = 123,
                    foto = "teste",
                    "Andressa",
                    1234,
                    "f",
                    curso = listOf<CourseStudent>(
                        CourseStudent(nome = "Desenvolvimento", sigla = "DS", icone = "url", 1200, 2023, listOf<Discipline>(
                            Discipline("Hardware e Redes", 1200, 60, "Aprovado"),
                            Discipline("Programacao front end", 1200, 70, "Aprovado"),
                            Discipline("Programacao Back end", 1200, 80, "Aprovado"),)
                        )
                    )
                    , status = "Cursando"),

            Student(
                id = 123,
                foto = "teste",
                "Mateus",
                1234,
                "f",
                curso = listOf<CourseStudent>(
                    CourseStudent(nome = "Desenvolvimento", sigla = "DS", icone = "url", 1200, 2023, listOf<Discipline>(
                        Discipline("Hardware e Redes", 1200, 50, "Aprovado"),
                        Discipline("Programacao front end", 1200, 60, "Aprovado"),
                        Discipline("Programacao Back end", 1200, 40, "Aprovado"),)
                    )
                )
                , status = "Cursando"),

            Student(
                id = 123,
                foto = "teste",
                "Diego",
                1234,
                "f",
                curso = listOf<CourseStudent>(
                    CourseStudent(nome = "Desenvolvimento", sigla = "DS", icone = "url", 1200, 2023, listOf<Discipline>(
                        Discipline("Hardware e Redes", 1200, 80, "Aprovado"),
                        Discipline("Programacao front end", 1200, 80, "Aprovado"),
                        Discipline("Programacao Back end", 1200, 80, "Aprovado"),)
                    )
                ),
                status = "Cursando")

            )

        }
        fun getStudent() : Student {
            return  Student(
                id = 1234,
                foto = "teste",
                "Giovana",
                2001234561,
                "F",
                curso = listOf(
                    CourseStudent(
                        nome = "Desenvolvimento de Sistemas",
                        sigla = "DS",
                        icone = "url",
                        1200,
                        2023,
                        listOf<Discipline>(
                            Discipline("Hardware e Redes", 1200, 50, "Aprovado"),
                            Discipline("Programacao front end", 1200, 90, "Aprovado"),
                            Discipline("Programacao Back end", 1200, 70, "Exame"),
                            Discipline("Banco De Dados", 1200, 70, "Reprovado")
                        )
                    )
                ),
                status = "Cursando")
        }
    }
}