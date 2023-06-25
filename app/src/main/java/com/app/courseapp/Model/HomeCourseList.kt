package com.app.courseapp.Model

class HomeCourseList {
    var id: String? = null
    var course_title: String? = null
    var author: String? = null
    var price: String? = null
    var image: String? = null


    constructor() {}
    constructor(
        id: String?,
        title: String?,
        author: String?,
        price: String?,
        image: String?

        ) {
        this.id = id
        this.course_title = title
        this.author = author
        this.price = price

    }


}