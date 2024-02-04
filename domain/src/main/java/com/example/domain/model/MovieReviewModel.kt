package com.example.domain.model

data class MovieReviewModel(
    val authorDetails: AuthorDetailsModel?,
    val updatedAt: String?,
    val author: String?,
    val createdAt: String?,
    val id: String?,
    val content: String?,
    val url: String?
)

data class AuthorDetailsModel(
    val avatarPath: String?,
    val name: String?,
    val rating: Double?,
    val username: String?
)