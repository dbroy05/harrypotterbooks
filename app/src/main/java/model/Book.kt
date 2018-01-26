package model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Main model that embodies the item of the list of book items returned.
 * It maps the fields from JSON with model property
 */

data class Book (

    var title: String,

    var author: String,

    var imageURL: String
)
