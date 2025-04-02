package com.tei.harrypottercharacter.util

import android.content.Context
import coil.network.HttpException
import com.tei.harrypottercharacter.R
import okio.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T,
    context: Context
): Result<T> {
    return try {
        Result.success(apiCall())
    } catch (e: IOException) {
        Result.failure(Exception(context.getString(R.string.network_error_message)))
    } catch (e: HttpException) {
        Result.failure(Exception(context.getString(R.string.http_404_error_message)))
    }
    catch (e: Exception) {
        Result.failure(Exception(context.getString(R.string.error_generic)))
    }
}
