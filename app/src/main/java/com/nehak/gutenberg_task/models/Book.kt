package com.nehak.gutenberg_task.models

data class Book(
    var authors: List<Author>,
    var bookshelves: List<String>,
    var download_count: Int,
    var id: Int,
    var languages: List<String>,
    var media_type: String,
    var subjects: List<String>,
    var title: String
)