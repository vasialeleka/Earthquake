package com.e.vasialeleka.kotlintabview

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_first_fragmnet.*
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class FirstFragmnet : Fragment() {

    var resultSend: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_first_fragmnet, container, false)
        val url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&maxmagnitude=0"
AsyncTaskRequest().execute(url)

        return v
    }


    inner class AsyncTaskRequest  : AsyncTask<String, Void, String>() {


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            textForInternet.text = result
        }

        override fun doInBackground(vararg url: String?): String {
            var text: String
            var textReturn:String=""
            var connection = URL(url[0]).openConnection() as HttpURLConnection
try{
            connection.connect()
            text = connection.inputStream.use {
                it.reader().use { reader -> reader.readText() }


            }} finally {
                connection.disconnect()
            }

try {


    val jsonObject = JSONObject(text)
    val jsonArray = jsonObject.getJSONArray("features")
    for (i in 0..jsonArray.length()) {
        var current = jsonArray.getJSONObject(i)
        var properties = current.getJSONObject("properties")
        var place = properties.getString("place")
        textReturn += "\n $place"
    }
} catch (e :JSONException ){}
            return textReturn
        }
    }
}

