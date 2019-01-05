package sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*


actual class Sample {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Android"
}

class MainActivity : AppCompatActivity() {
    val languages: ArrayList<Language> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Loads animals into the ArrayList
        getCountries()

        // Creates a vertical Layout Manager
        rv_countries.layoutManager = LinearLayoutManager(this)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

        // Access the RecyclerView Adapter and load the data into it
        rv_countries.adapter = CountriesAdapter(languages, this)


    }


    fun getCountries() {
        val url = "https://vpered.su/v1/groups"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }
}