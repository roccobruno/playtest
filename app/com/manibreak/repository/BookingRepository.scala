package com.manibreak.repository

import model.Booking
import play.api.libs.json.Json
import play.modules.reactivemongo.MongoDbConnection
import reactivemongo.api.DB
import reactivemongo.api.ReadPreference.Primary
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.BSONObjectID
import uk.gov.hmrc.mongo.ReactiveRepository
import uk.gov.hmrc.mongo.json.ReactiveMongoFormats
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait BookingRepository {

  def saveBooking(booking:Booking): Future[WriteResult]
  def findAllBooking: Future[Seq[Booking]]
  def findByCustomerId (customerId:String):Future[Seq[Booking]]
  def deleteByCustomerId (customerId:String):Future[WriteResult]
}

object BookingRepository extends MongoDbConnection {
  private lazy val bookingRepository = new BookingMongoDbRepository
  def apply(): BookingRepository = bookingRepository
}

class BookingMongoDbRepository(implicit mongo: () => DB)
  extends ReactiveRepository[Booking,BSONObjectID]("booking", mongo, Booking.formats, ReactiveMongoFormats.objectIdFormats)
  with BookingRepository {


  import scala.concurrent.ExecutionContext.Implicits.global


  def findAllBooking = findAll(Primary)
  def saveBooking(booking: Booking): Future[WriteResult] = insert(booking)
  def findByCustomerId (customerId:String): Future[Seq[Booking]] = collection.find(Json.obj("customerId" -> Json.toJson(customerId))).
  cursor[Booking](Primary).collect[Seq]()

  def deleteByCustomerId(customerId: String): Future[WriteResult] = collection.remove(Json.obj("customerId" -> Json.toJson(customerId)))
}
