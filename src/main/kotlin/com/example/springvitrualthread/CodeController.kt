package com.example.springvitrualthread

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CodeController {
    @GetMapping("/io-bound")
    fun ioBound() {
        Thread.sleep(500) // db select
        Thread.sleep(100) // get api call
        Thread.sleep(300) // post api call
        println(Thread.currentThread())
    }

    @GetMapping("/io-bound-coroutine")
    suspend fun ioBoundCoroutine() {
        CoroutineScope(Dispatchers.IO).async {
            delay(500) // db select
            delay(100) // get api call
            delay(300) // post api call
        }.await()
    }
}