package com.barengific.charmhome.formats

import org.http4k.core.Body
import org.http4k.format.Gson.auto
import java.lang.String.format

data class GsonMessage(val subject: String, val message: String)

val gsonMessageLens = Body.auto<GsonMessage>().toLens()
