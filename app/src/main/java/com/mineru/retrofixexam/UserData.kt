package com.mineru.retrofixexam

data class UserData (
    val UserId: Int,
    val Name: String,
    val ClassId: Int,
    val Email: String,
    val Password: String,
    val token: String,
    val isGoogle: Boolean,
    val isVerified: Boolean,
    val uuid: String
)
