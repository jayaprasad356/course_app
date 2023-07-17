package com.app.courseapp.Model

class HomeCourseList {
    var id: String? = null
    var course_tittle: String? = null
    var author: String? = null
    var price: String? = null
    var image: String? = null


    constructor() {}
    constructor(
        id: String?,
        tittle: String?,
        author: String?,
        price: String?,
        image: String?

        ) {
        this.id = id
        this.course_tittle = tittle
        this.author = author
        this.price = price

    }


}