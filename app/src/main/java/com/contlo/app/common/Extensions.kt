package com.contlo.app.common

import com.contlo.app.common.Constants.DATE_FORMAT
import com.contlo.app.data.model.PrApiModel
import com.contlo.app.ui.PRUiModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date?.format() = SimpleDateFormat("dd MMM, yy", Locale.getDefault()).format(this)

fun PrApiModel.toUiModel(): PRUiModel {
    val created = createdAt?.let { SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(it) }
    val closed = closedAt?.let { SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(it) }
    return PRUiModel(
        id = this.id,
        title = this.title ?: "",
        imageUrl = user?.avatarUrl ?: "",
        userName = user?.login ?: "",
        dateCreated = created,
        dateClosed = closed
    )
}