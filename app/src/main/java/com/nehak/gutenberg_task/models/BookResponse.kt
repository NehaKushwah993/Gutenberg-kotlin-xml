package com.nehak.gutenberg_task.models

data class BookResponse(
    var count: Int,
    var next: String,
    var previous: Any,
    var results: List<Book>
)