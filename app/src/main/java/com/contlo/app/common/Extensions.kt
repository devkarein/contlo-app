package com.contlo.app.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.format() = SimpleDateFormat("dd MMM, yy", Locale.getDefault()).format(this)