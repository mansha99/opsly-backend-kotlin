package com.opsly.backend.webfluxkotlin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.http.MediaType.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import reactor.core.scheduler.Schedulers
import reactor.core.publisher.Flux

class FinalResponse(var twitter : TwitterResponseObjectList?, var facebook : FacebookResponseObjectList?,var instagram : InstaResponseObjectList?)


@RestController
@RequestMapping(path = ["/"], produces = [ APPLICATION_JSON_UTF8_VALUE ])
class HelloController(
        private val apiService: MsWebClient
) {

        @RequestMapping(method = [RequestMethod.GET])
        fun getData(): FinalResponse?{

        var resultFound:Boolean = false  
        while(resultFound == false){      
                try{        
                var objTwitter:Mono<TwitterResponseObjectList> = apiService.listTwitter()
                var objFacebook:Mono<FacebookResponseObjectList> = apiService.listFacebook()
                var objInsta:Mono<InstaResponseObjectList> = apiService.listInsta()
                resultFound = true 
                return FinalResponse(objTwitter.block(),objFacebook.block(),objInsta.block())
                }catch (e: Exception) {
                resultFound = false 
                        println("-------------------------------")
                }
        }

        return null
        }
}
