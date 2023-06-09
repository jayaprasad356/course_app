package com.app.courseapp.Model

class Mylearning {
    var id: String? = null
    var title: String? = null
    var author: String? = null
    var price: String? = null


    constructor() {}
    constructor(
        id: String?,
        title: String?,
        author: String?,
        price: String?,

        ) {
        this.id = id
        this.title = title
        this.author = author

    }


}