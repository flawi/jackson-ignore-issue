package main.kotlin

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

fun main() {
	val mapper = ObjectMapper().registerModule(KotlinModule())

	println(mapper.writeValueAsString(NotWorking("value","shouldBeIgnored")))
	// {"property":"value","vout":"shouldBeIgnored"} !!!
	println(mapper.writeValueAsString(Working("value", "shouldBeIgnored")))
	// {"property":"value"} ✓
	println(mapper.writeValueAsString(AlsoWorking("value", "shouldBeIgnored")))
	// {"property":"value"} ✓
}

class NotWorking(
		@JsonProperty val property: String,
		@JsonIgnore val vOut: String
)

class Working(
		@JsonProperty val property: String,
		@JsonIgnore val vout: String
)

class AlsoWorking(
		@JsonProperty val property: String,
		@JsonIgnore val voUt: String
)




