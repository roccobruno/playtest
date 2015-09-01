package com.manibreak.repository

import java.util.Date

import model.Booking
import org.joda.time.DateTime
import org.scalatest.LoneElement
import org.scalatest.concurrent.{Eventually, ScalaFutures}
import reactivemongo.api.DB
import uk.gov.hmrc.time.DateTimeUtils

class BookingMongoRepositorySpec extends  MongoSpecSupport with Awaiting  with ScalaFutures with LoneElement with Eventually with
  org.scalatest.WordSpecLike with org.scalatest.Matchers {

  trait Setup {

    val reset =  await(bookingRepository.drop)

  }


  private implicit val now = DateTimeUtils.now

  def repository(time: DateTime)(implicit mongo: () => DB) = new BookingMongoDbRepository() {
    override def withCurrentTime[A](f: (DateTime) => A) = f(time)
  }

  private val bookingRepository = repository(now)


  "Booking repository" should {

    "initially be empty" in new Setup {
      await(bookingRepository.findAllBooking).size should be(0)
    }

    "save multiple authentications" in new Setup {

      val res1F = bookingRepository.saveBooking(Booking("customerId",new Date(),"12:00",Seq("1","2")))
      val res2F = bookingRepository.saveBooking(Booking("customerId",new Date(),"12:00",Seq("1","2")))

      await(for {
        res1 <- res1F
        res2 <- res2F
      } yield true)

      await(bookingRepository.findAllBooking).size should be(2)
    }

    "find 2 records for the given customer Id" in new Setup {
      val res1F = bookingRepository.saveBooking(Booking("customerId",new Date(),"12:00",Seq("1","2")))
      val res2F = bookingRepository.saveBooking(Booking("customerId2",new Date(),"12:00",Seq("1","2")))

      await(for {
        res1 <- res1F
        res2 <- res2F
      } yield true)

      private val bookings = await(bookingRepository.findByCustomerId("customerId"))
      bookings.size should be(1)
      bookings.seq(0).treatments.size should be(2)
      bookings.seq(0).treatments.contains("2") shouldBe true
      bookings.seq(0).treatments.contains("1") shouldBe true
      bookings.seq(0).status shouldBe("REQUESTED")
    }

    "delete all the records for the given customerId" in new Setup {
      val res1F = bookingRepository.saveBooking(Booking("customerId",new Date(),"12:00",Seq("1","2")))
      val res2F = bookingRepository.saveBooking(Booking("customerId2",new Date(),"12:00",Seq("1","2")))

      await(for {
        res1 <- res1F
        res2 <- res2F
      } yield true)

      var bookings = await(bookingRepository.findByCustomerId("customerId"))
      bookings.size should be(1)
      await(bookingRepository.deleteByCustomerId("customerId"))

       bookings = await(bookingRepository.findByCustomerId("customerId"))
      bookings.size should be(0)
    }

  }
}
