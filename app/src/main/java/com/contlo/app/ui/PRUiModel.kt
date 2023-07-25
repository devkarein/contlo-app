package com.contlo.app.ui

import java.util.Date

data class PRUiModel(
    val id: String,
    val title: String,
    val imageUrl: String,
    val userName: String,
    val dateCreated: Date,
    val dateClosed: Date
)
