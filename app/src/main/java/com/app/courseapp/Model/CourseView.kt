package com.app.courseapp.Model

class CourseView {
    var id: String? = null
    var tittle: String? = null
    var video_link: String? = null
    var video_duration: String? = null


    constructor() {}
    constructor(
        id: String?,
        title: String?,
        video_link: String?,
        video_duration: String?

        ) {
        this.id = id
        this.tittle = title
        this.video_link = video_link
        this.video_duration = video_duration

    }


}