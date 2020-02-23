package com.opsly.backend.webfluxkotlin

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.ui.set
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.http.HttpStatus
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@JsonIgnoreProperties(ignoreUnknown = true)
data class TwitterResponseObject(var username: String = "", var tweet: String = "")
@JsonIgnoreProperties(ignoreUnknown = true)
data class FacebookResponseObject(var name: String = "", var status: String = "")
@JsonIgnoreProperties(ignoreUnknown = true)
data class InstaResponseObject(var username: String = "", var picture: String = "")
class MyCustomException() : Exception(){
}
class TwitterResponseObjectList : ArrayList<TwitterResponseObject>(){}
class FacebookResponseObjectList : ArrayList<FacebookResponseObject>(){}
class InstaResponseObjectList : ArrayList<InstaResponseObject>(){}

@Service
class MsWebClient {
        
        fun listTwitter():Mono<TwitterResponseObjectList>  {
            val client = WebClient.create("https://takehome.io/")
        return client.get()
            .uri("twitter")
            .retrieve()
            .onStatus({ httpStatus -> HttpStatus.INTERNAL_SERVER_ERROR == httpStatus }, 
            { 
                clientResponse -> Mono.error(MyCustomException())
            })
            .bodyToMono(TwitterResponseObjectList::class.java);
        }
        fun listFacebook():Mono<FacebookResponseObjectList>  {
            val client = WebClient.create("https://takehome.io/")
        return client.get()
            .uri("facebook")
            .retrieve()
            .onStatus({ httpStatus -> HttpStatus.INTERNAL_SERVER_ERROR == httpStatus }, 
            { clientResponse -> Mono.error(MyCustomException()) })
            .bodyToMono(FacebookResponseObjectList::class.java);
        }
        fun listInsta():Mono<InstaResponseObjectList>  {
            val client = WebClient.create("https://takehome.io/")
        return client.get()
            .uri("instagram")
            .retrieve()
            .onStatus({ httpStatus -> HttpStatus.INTERNAL_SERVER_ERROR == httpStatus }, 
            { clientResponse -> Mono.error(MyCustomException()) })
            .bodyToMono(InstaResponseObjectList::class.java);
        }
        
}
