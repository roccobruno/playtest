package model

import java.util.Date

import play.api.libs.json.Json

case class Booking(customerId:String, date: Date, timeOfTheDay:String,treatments: Seq[String])

object Booking {
  implicit val formats = Json.format[Booking]
}
