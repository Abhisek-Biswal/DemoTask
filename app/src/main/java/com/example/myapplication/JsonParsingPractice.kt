package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import org.json.JSONObject
import java.util.Arrays

class JsonParsingPractice : AppCompatActivity() {




    val storeJson = "{\"id\":6,\"title\":\"Dave wasn't exactly sure how he had ended up\",\"body\":\"Dave wasn't exactly sure how he had ended up in this predicament. He ran through all the events that had lead to this current situation and it still didn't make sense. He wanted to spend some time to try and make sense of it all, but he had higher priorities at the moment. The first was how to get out of his current situation of being naked in a tree with snow falling all around and no way for him to get down.\",\"userId\":47,\"tags\":[\"english\",\"classic\",\"american\"],\"reactions\":3}"

    val storeJson2 = "{\"id\":6,\"title\":\"Dave wasn't exactly sure how he had ended up\",\"body\":\"Dave wasn't exactly sure how he had ended up in this predicament. He ran through all the events that had lead to this current situation and it still didn't make sense. He wanted to spend some time to try and make sense of it all, but he had higher priorities at the moment. The first was how to get out of his current situation of being naked in a tree with snow falling all around and no way for him to get down.\",\"userId\":47,\"tags\":[\"english\",\"classic\",\"american\"],\"reactions\":3}"

    var jsonObject: JSONObject = JSONObject(storeJson)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_parsing_practice)



        val id= jsonObject.getInt("id")
        println("id:" +id)

        val title= jsonObject.getString("title")
        println("title:" +title)

        println("Body:" +jsonObject.getString("body"))

        println( "userId:" +jsonObject.getInt("userId"))

        println("Tags:" +jsonObject.getJSONArray("tags"))

        println("reactions:" +jsonObject.getString("reactions"))


        val fromJson = Gson().fromJson(storeJson2,StoreJson::class.java)
        println(fromJson)

        val toJson = Gson().toJson(storeJson)
        println(toJson)






    }
}