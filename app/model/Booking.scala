package model

import java.util.Date

import model.BookingStatus.BookingStatus
import play.api.libs.json._

case class Booking(customerId:String, date: Date,
                   timeOfTheDay:String,
                   treatments: Seq[String],
                   creationTime:Option[Date] = Some(new Date()),
                   beautyExpertsId: Option[String] = None,
                   status: String = BookingStatus.REQUESTED.toString)

object Booking {
  implicit val enumFormat = new Format[BookingStatus] {
    def reads(json: JsValue) = JsSuccess(BookingStatus.withName(json.as[String]))
    def writes(enum: BookingStatus) = JsString(enum.toString)
  }
  implicit val formats = Json.format[Booking]
}

object BookingStatus extends Enumeration {
  type BookingStatus =  Value
  val REQUESTED, INFORMED, CONFIRMED, CANCELLED = Value
}
