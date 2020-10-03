package com.example.studentcrudapp

import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.awt.GradientPaint
import java.util.*
import javax.persistence.*

@SpringBootApplication
class StudentcrudappApplication

fun main(args: Array<String>) {
    runApplication<StudentcrudappApplication>(*args)
}

@Entity
@Table(name = "STUDENT")
data class Student(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int,
                   @NotNull @Column(name = "FIRST_NAME") val firstName: String,
                   @NotNull @Column(name = "LAST_NAME") val lastName: String,
                   @NotNull @Column(name = "GRADE") val grade: Int)

@Repository
interface StudentRepository : JpaRepository<Student, Int> {
    override fun findById(id: Int): Optional<Student>
    override fun findAll(): List<Student>
}

@Service
@Transactional
class StudentService(@Autowired val studentRepository: StudentRepository) {
    fun save(newStudent: Student) = studentRepository.save(newStudent)
    fun findAll(): List<Student> = studentRepository.findAll();
    fun findById(id: Int): Optional<Student> = studentRepository.findById(id)
    fun deleteById(id: Int) = studentRepository.deleteById(id)
}

@RestController
@RequestMapping("/student")
class StudentRestController(@Autowired val studentService: StudentService) {
    @RequestMapping("/all")
    fun findAll() = studentService.findAll()

    @RequestMapping("/{id}")
    fun findById(@PathVariable id: Int) = studentService.findById(id)

    @PostMapping("/add")
    fun save(@RequestBody newStudent: Student) = studentService.save(newStudent)

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable id: Int) = studentService.deleteById(id)
}
