package com.example.tenantmanagementsystemgroupa

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// @Entity: every Tenant is stored as one ROW in the "tenants" table
@Entity(tableName = "tenants")
data class Tenant(
    val name: String,
    val phone: String,
    val rent: String,

    // Unique auto-incrementing ID for each row (0 means not saved yet)
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Serializable {
    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent: KSh $rent"
    }
}