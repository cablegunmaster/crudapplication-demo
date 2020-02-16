package com.jasper.crudapplication.httpUtil

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun httpSendGET(uri: String): HttpResponse<String>? {
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .build()
    return client.send(request, HttpResponse.BodyHandlers.ofString())
}

fun httpSendPost(uri: String, data: String): HttpResponse<String>? {
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofString(data))
            .uri(URI.create(uri))
            .header("Content-Type","application/json")
            .build()
    return client.send(request, HttpResponse.BodyHandlers.ofString())
}

fun httpSendPatch(uri: String): HttpResponse<String>? {
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
            .DELETE()
            .uri(URI.create(uri))
            .build()
    return client.send(request, HttpResponse.BodyHandlers.ofString())
}

fun httpSendDelete(uri: String): HttpResponse<String>? {
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
            .DELETE()
            .uri(URI.create(uri))
            .build()
    return client.send(request, HttpResponse.BodyHandlers.ofString())
}
