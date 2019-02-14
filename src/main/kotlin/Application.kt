package main.kotlin

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

fun main() {
    val mapper = ObjectMapper().registerModule(KotlinModule())

    println(mapper.writeValueAsString(NotWorking("txid", "123")))
    println(mapper.writeValueAsString(Working("txid", "123")))
}

open class MultichainRequest @JsonCreator constructor(
    @JsonProperty("method")
    val method: String,

    @JsonProperty("params")
    val params: List<Any>
)

class NotWorking(
    @JsonIgnore val txId: String,
    @JsonIgnore val vOut: String
) : MultichainRequest("gettxoutdata", listOf(txId, vOut))

class Working(
    @JsonIgnore val txId: String,
    @JsonIgnore val vout: String
) : MultichainRequest("gettxoutdata", listOf(txId, vout))


