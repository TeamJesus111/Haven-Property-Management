package com.example.mobileappproj

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform