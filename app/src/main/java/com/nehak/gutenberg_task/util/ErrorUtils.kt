package com.nehak.gutenberg_task.util

import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import com.nehak.gutenberg_task.models.*

/**
 * Created by Neha Kushwah on 3/29/21.
 * parses error response body
 */
object ErrorUtils {

    fun parseError(response: Response<*>, retrofit: Retrofit): Error? {
        val converter = retrofit.responseBodyConverter<Error>(Error::class.java, arrayOfNulls(0))
        return try {
            converter.convert(response.errorBody()!!)
        } catch (e: IOException) {
            Error()
        }
    }
}