package com.example.words.ui

data class User(val name: String,
                val likesOranges: Boolean) {
    override fun toString() = "$name - $likesOranges"
}