package com.example.chatapp

class Upload {
    var mName: String? = null
    var mImageUrl: String? = null

    constructor() {
        // Empty constructor needed
    }

    constructor(name: String, imageUrl: String) {
        mName = if (name.trim { it <= ' ' } == "") {
            "No Name"
        } else {
            name
        }
        mImageUrl = imageUrl
    }

    fun getName(): String? {
        return mName
    }

    fun setName(name: String) {
        mName = name
    }

    fun getImageUrl(): String? {
        return mImageUrl
    }

    fun setImageUrl(imageUrl: String) {
        mImageUrl = imageUrl
    }
}
