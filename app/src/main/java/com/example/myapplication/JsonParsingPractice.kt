package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONObject

class JsonParsingPractice : AppCompatActivity() {

    var  id = 0
    var  title = ""
    var  body = ""
    var  userId = 0
    var  tags= listOf<String>()
    var read = 0


    val storeJson = "{\"id\":6,\"title\":\"Dave wasn't exactly sure how he had ended up\",\"body\":\"Dave wasn't exactly sure how he had ended up in this predicament. He ran through all the events that had lead to this current situation and it still didn't make sense. He wanted to spend some time to try and make sense of it all, but he had higher priorities at the moment. The first was how to get out of his current situation of being naked in a tree with snow falling all around and no way for him to get down.\",\"userId\":47,\"tags\":[\"english\",\"classic\",\"american\"],\"reactions\":3}"

    var jsonObject: JSONObject = JSONObject(storeJson)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_parsing_practice)



        id= jsonObject.getInt("id")
        println(id)

        title= jsonObject.getString("title")
        println(title)

        body= jsonObject.getString("body")
        println(body)
        userId = jsonObject.getInt("userId")
        println(userId)









    }
}