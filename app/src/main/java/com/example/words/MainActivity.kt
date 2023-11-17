package com.example.words

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.words.ui.User
import com.example.words.ui.fromJson
import com.example.words.ui.theme.WordsTheme
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okio.IOException


class MainActivity : ComponentActivity() {

    val Base_url = "https://dog.ceo/api/breeds/image/random"
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
    val context = this
        super.onCreate(savedInstanceState)
        setContent {
            var result: String? = null
            WordsTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colorScheme.background
                ) {
                    Button(onClick = {
                        val usersToSave = listOf(
                            User("Jim", true),
                            User("Susan", true), User("Harry", false),
                            User("Bob", false), User("Kate", true))
                        saveUsersToPreferences(usersToSave, context)
                    /*TODO*/
                    }) {
                  Text(text = "SAVE")
                        //Toast.makeText(this, "Saved ${usersToSave.size} users to preferences.",
                           // Toast.LENGTH_SHORT).show()
                      //  val repo = Repository()
                       // repo.makeRequest()
                    }
                    var text = "LOAD"
                    Button(onClick = {
                        val users = getUsers(context)
                        val usersLikingOranges = users.filter {
                            it.likesOranges
                        }
                        text  = usersLikingOranges.joinToString()
                    }) {
                        Text(text = text)
                        //Toast.makeText(this, "Saved ${usersToSave.size} users to preferences.",
                        // Toast.LENGTH_SHORT).show()
                        //  val repo = Repository()
                        // repo.makeRequest()
                    }

                    try {

                       // Log.d("MainActivity", result.toString())
                            /*
                            val url = URL(Base_url)
                            val urlConnection = url.openConnection()
                            val inputStream = urlConnection.getInputStream()
                            val inputStreamReader = InputStreamReader(inputStream)
                            val bufferedReader = BufferedReader(inputStreamReader)
                            val res = bufferedReader.readLine()
                            Log.d("MainActivity", res)
                            */
                            /*
                            val client = OkHttpClient()
                            val BASE_URL = "https://exercisedb.p.rapidapi.com/exercises/bodyPart/back?limit=100"
                            val request = Request.Builder()
                                .url(BASE_URL)
                                .get()
                                .addHeader("X-RapidAPI-Key", "6929bc99b9mshdb1a50796fb5502p111639jsn62b0b1df3969")
                                .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
                                .build()

                            //    post(body).build()

                            client.newCall(request).enqueue(object : Callback {
                                override fun onFailure(call: Call, e: IOException) {
                                    callback.onFailure(e.toString());
                                }
                                */

                           // viewModel.getResponseFromApi()
                           // result = viewModel.response.value




                    }
                    catch (e: IOException) {
                        println("Ошибка подключения: $e");
                    }

                    Greeting(
                        text = viewModel.response.value.toString(),
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

private fun saveUsersToPreferences(users: List<User>, context: Context) {
    val prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit()
    val jsonString = Gson().toJson(users)
    prefEditor.putString("users", jsonString).apply()
}

private fun getUsers(context: Context): List<User> {
    val preferences = PreferenceManager.getDefaultSharedPreferences(context)
    val jsonString = preferences.getString("users", null)

    return if (jsonString != null)
        Gson().fromJson(jsonString)
    else
        listOf()
}

@Composable
fun Greeting( modifier: Modifier = Modifier, text: String) {
    val client = OkHttpClient()


    Text(
        text = text,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WordsTheme {
        //Greeting("Android")
    }
}