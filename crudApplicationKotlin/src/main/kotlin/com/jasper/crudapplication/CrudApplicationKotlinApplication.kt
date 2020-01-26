package com.jasper.crudapplication

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CrudApplicationKotlinApplication

fun main(args: Array<String>) {
	runApplication<CrudApplicationKotlinApplication>(*args){
		setBannerMode(Banner.Mode.OFF) //remove banner on startup.
	}
}
