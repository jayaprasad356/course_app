package com.app.courseapp.Model

class Mylearning {
    var id: String? = null
    var course_title: String? = null
    var author: String? = null
    var price: String? = null
    var image: String? = null


    constructor() {}
    constructor(
        id: String?,
        course_title: String?,
        author: String?,
        price: String?,
        image: String?

        ) {
        this.id = id
        this.course_title = course_title
        this.author = author
        this.price = price
        this.image = image

    }


}