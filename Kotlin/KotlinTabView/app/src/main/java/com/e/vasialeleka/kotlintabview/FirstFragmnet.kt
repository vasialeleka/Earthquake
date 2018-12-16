package com.e.vasialeleka.kotlintabview

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first_fragmnet.*
import kotlinx.android.synthetic.main.fragment_first_fragmnet.view.*
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class FirstFragmnet  : Fragment() {
/*val context:?Context = null
    fun getContext(contextd: Context){
context = contextd
    }
*/
    var resultSend: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_first_fragmnet, container, false)
        val url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&maxmagnitude=0"
        AsyncTaskRequest(context,v.recyclerView).execute(url)
 var list = mutableListOf<EarthQuake>()
        for ( i in 0..4 ){
            list.add(EarthQuake(i.toDouble(),"place $i"))

 }

       /* v.recyclerView.layoutManager  = LinearLayoutManager(context)
       v.recyclerView.adapter = EarthquakeAdapter(list)*/
        return v
    }


    inner class AsyncTaskRequest (var context: Context?,var recyclerView: RecyclerView): AsyncTask<String, Void, MutableList<EarthQuake>>() {


        override fun onPostExecute(result: MutableList<EarthQuake>) {
            super.onPostExecute(result)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = EarthquakeAdapter(result)
            //textForInternet.text = result

        }

        override fun doInBackground(vararg url: String?): MutableList<EarthQuake> {
            var text: String
            var textReturn: String = ""
            var list = mutableListOf<EarthQuake>()
            var connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use {
                    it.reader().use { reader -> reader.readText() }


                }
            } finally {
                connection.disconnect()
            }

            try {


                val jsonObject = JSONObject(text)
                val jsonArray = jsonObject.getJSONArray("features")
                for (i in 0..jsonArray.length()) {
                    var current = jsonArray.getJSONObject(i)
                    var properties = current.getJSONObject("properties")
                    var mag = properties.getDouble("mag")
                    var place = properties.getString("place")
                    textReturn += "\n $place"
                    list.add(EarthQuake(mag, place))
                }
            } catch (e: JSONException) {
            }
            return list
        }
    }
}

