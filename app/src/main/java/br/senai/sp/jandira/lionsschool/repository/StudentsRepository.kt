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
                                    Discipline("Hardware e Redes", 1200, 80, "Aprovado"),
                                    Discipline("Programacao front end", 1200, 80, "Aprovado"),
                                    Discipline("Programacao Back end", 1200, 80, "Aprovado"),)
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
                            Discipline("Hardware e Redes", 1200, 80, "Aprovado"),
                            Discipline("Programacao front end", 1200, 80, "Aprovado"),
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
                        Discipline("Hardware e Redes", 1200, 80, "Aprovado"),
                        Discipline("Programacao front end", 1200, 80, "Aprovado"),
                        Discipline("Programacao Back end", 1200, 80, "Aprovado"),)
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
    }
}