package com.javiersc.kotlin.docker

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

fun main() {
    val connection: Connection = DriverManager.getConnection(URL, USER, PASSWORD)
    connection.createStatement().use {
        it.executeUpdate(createTable)
    }
    connection.createStatement().use {
        val deleteQuery = "DELETE FROM $tableName"
        it.executeUpdate(deleteQuery)
    }
    connection.prepareStatement(insert).use {
        it.setString(1, "Javi")
        it.executeUpdate()
    }
    connection.createStatement().use {
        val resultSet: ResultSet = it.executeQuery(select)

        println("Persons:")
        while (resultSet.next()) {
            val id: String = resultSet.getString("id")
            val name: String = resultSet.getString("name")
            println("Id: $id, Name: $name")
        }
    }
}

private const val URL = "jdbc:postgresql://postgres:5432/app_database"
private const val USER = "user"
private const val PASSWORD = "password"

const val tableName = "person"

// language=sql
val createTable =
    """
        CREATE TABLE IF NOT EXISTS $tableName (
            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
            name VARCHAR(50) NOT NULL
        )
    """.trimIndent()

// language=sql
val insert =
    """
        INSERT INTO $tableName (name) VALUES (?)
    """.trimIndent()

// language=sql
val select =
    """
        SELECT id, name FROM $tableName
    """.trimIndent()
