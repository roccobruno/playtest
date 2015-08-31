package controllers

import com.manibreak.repository.BookingRepository
import model.Booking
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

trait BookingController {
  this:Controller =>

  import scala.concurrent.ExecutionContext.Implicits.global

  def bookingRepository: BookingRepository

  def saveBooking() = Action.async(parse.json) {
    implicit request => {
      val booking = request.body.as[Booking]
      bookingRepository.saveBooking(booking)
      Future.successful(Ok)
    }
  }

  def getBookings(customerId:String) =Action.async {
    bookingRepository.findByCustomerId(customerId) map {
      records =>
        Ok(Json.toJson(records))
    }
  }

  def deleteBooking(customerId:String) = Action.async {
    implicit request => {
      bookingRepository.deleteByCustomerId(customerId)
      Future.successful(Ok)
    }
  }

}

object BookingController extends Controller with BookingController {
  override def bookingRepository = BookingRepository()
}
