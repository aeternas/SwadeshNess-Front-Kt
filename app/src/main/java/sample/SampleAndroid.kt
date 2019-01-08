package sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


actual class Sample {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Android"
}

class MainActivity : AppCompatActivity() {
    val languages: ArrayList<Language> = ArrayList()

    var adapter = CountriesAdapter(languages, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_countries.layoutManager = LinearLayoutManager(this)
        rv_countries.adapter = adapter
        getCountries()
    }

    fun getCountries() {
        val url = "https://vpered.su/v1/groups"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val langGroups = gson.fromJson(body, Array<LanguageGroup>::class.java)
                val languages = langGroups.flatMap { it.languages.asList() }
                runOnUiThread {
                    adapter.updateData(languages)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}