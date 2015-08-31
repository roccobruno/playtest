package com.manibreak.controllers

import model.Booking
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{FakeRequest, WithApplication}



@RunWith(classOf[JUnitRunner])
class BookingControllerSpec extends Specification {

  "BookingController" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "return list of bookings for given customer" in new WithApplication{
      val response = route(FakeRequest(DELETE,"/booking/customer/2323223")).get
      status(response) must equalTo(OK)

      val bookingJason =
        """
          |{
          | "customerId":"2323223",
          | "date":"2015-03-03",
          |  "timeOfTheDay":"12:00",
          |  "treatments": ["1","2"]
          |}
          |
        """.stripMargin

      route(FakeRequest(POST,"/booking").withJsonBody(Json.parse(bookingJason)))


      val home = route(FakeRequest(GET, "/booking/customer/2323223")).get

      status(home) must equalTo(OK)
      contentAsJson(home).as[Seq[Booking]] should be size(1)
    }
  }

}
