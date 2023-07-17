package com.app.courseapp.Model

class Mylearning {
    var id: String? = null
    var course_tittle: String? = null
    var author: String? = null
    var price: String? = null
    var image: String? = null


    constructor() {}
    constructor(
        id: String?,
        course_tittle: String?,
        author: String?,
        price: String?,
        image: String?

        ) {
        this.id = id
        this.course_tittle = course_tittle
        this.author = author
        this.price = price
        this.image = image

    }


}