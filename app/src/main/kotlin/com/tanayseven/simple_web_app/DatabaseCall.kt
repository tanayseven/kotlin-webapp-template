package com.tanayseven.simple_web_app

import org.ktorm.database.*
import org.ktorm.schema.*
import kotlin.concurrent.thread

object DatabaseCall {
    private val connection = Database.connect("jdbc:postgres://localhost:5432/sample", user = "admin", password = "password")

    init {
        Runtime.getRuntime().addShutdownHook(
            thread(start = false) {
                // Close the connection when the process exits.
            }
        )
    }
}

object Departments : Table<Nothing>("t_department") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val location = varchar("location")
}

object Employees : Table<Nothing>("t_employee") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val job = varchar("job")
    val managerId = int("manager_id")
    val hireDate = date("hire_date")
    val salary = long("salary")
    val departmentId = int("department_id")
}


