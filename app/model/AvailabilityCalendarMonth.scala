package model

import model.AvailabilityDayStatus.AvailabilityDayStatus
import play.api.libs.json._

object AvailabilityDayStatus extends Enumeration {
  type AvailabilityDayStatus = Value
  val FULLY_AVAILABLE,PARTIAL_AVAILABLE,NOT_AVAILABLE = Value
}

case class AvailabilityCalendarMonth(month: Int,year: Int, days:Seq[AvailabilityCalendarDay])
case class AvailabilityCalendarDay(day:Int, slots:Seq[AvailabilityCalendarHourSlot], status: AvailabilityDayStatus = AvailabilityDayStatus.FULLY_AVAILABLE)
case class AvailabilityCalendarHourSlot(startHour: Int, availableBeautyExperts: Seq[String])


object AvailabilityCalendarMonth {
  implicit val hourFormat = Json.format[AvailabilityCalendarHourSlot]
  implicit val enumFormat = new Format[AvailabilityDayStatus] {
    def reads(json: JsValue) = JsSuccess(AvailabilityDayStatus.withName(json.as[String]))
    def writes(enum: AvailabilityDayStatus) = JsString(enum.toString)
  }
  implicit val dayFormat = Json.format[AvailabilityCalendarDay]
  implicit val formats = Json.format[AvailabilityCalendarMonth]
}

object AvailabilityCalendarDay {
  implicit val enumFormat = new Format[AvailabilityDayStatus] {
    def reads(json: JsValue) = JsSuccess(AvailabilityDayStatus.withName(json.as[String]))
    def writes(enum: AvailabilityDayStatus) = JsString(enum.toString)
  }
  implicit val hourFormat = Json.format[AvailabilityCalendarHourSlot]
  implicit val formats = Json.format[AvailabilityCalendarDay]
}


object AvailabilityCalendarHourSlot {
  implicit val formats = Json.format[AvailabilityCalendarHourSlot]
}