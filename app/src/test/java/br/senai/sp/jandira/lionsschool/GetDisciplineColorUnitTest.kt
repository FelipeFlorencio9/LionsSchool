package br.senai.sp.jandira.lionsschool

import br.senai.sp.jandira.lionsschool.ui.theme.Approved
import br.senai.sp.jandira.lionsschool.ui.theme.Disapproved
import br.senai.sp.jandira.lionsschool.ui.theme.InExam
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GetDisciplineColorUnitTest {
    @Test
    fun GetDisciplineColorApproved(){
        val result = getDisciplineItemColor(80)
        assertEquals(Approved,result)
    }
    @Test
    fun GetDisciplineColorDisapproved(){
        val result = getDisciplineItemColor(59)
        assertEquals(Disapproved,result)
    }
    @Test
    fun GetDisciplineColorInExam(){
        val result = getDisciplineItemColor(60)
        assertEquals(InExam,result)
    }


}