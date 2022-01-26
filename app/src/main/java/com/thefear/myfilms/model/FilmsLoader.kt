package com.thefear.myfilms.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.thefear.myfilms.model.repository.entitiesDTO.PageDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors

object FilmsLoader {
    fun loadFilms(): PageDTO? {

        try {
            val uri =
                URL("https://api.kinopoisk.dev/movie?field=rating.kp&search=6-10&field=typeNumber&search=1&field=year&search=2020-2021&token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06")

            lateinit var urlConnection: HttpURLConnection
            try {
                urlConnection = uri.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 10000

                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val lines = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    getLinesForOld(bufferedReader)
                } else {
                    getLines(bufferedReader)
                }

                return Gson().fromJson(lines, PageDTO::class.java)

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        return null

    }

    private fun getLinesForOld(reader: BufferedReader): String {
        val rawData = StringBuilder(1024)
        var tempVariable: String?

        while (reader.readLine().also { tempVariable = it } != null) {
            rawData.append(tempVariable).append("\n")
        }
        reader.close()
        return rawData.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}