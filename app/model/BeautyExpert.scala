package model

import model.DAY_OF_WEEK.DAY_OF_WEEK
import model.TIME_OF_DAY.TIME_OF_DAY

case class Location(lat:String,lon:String)
case class Treatment(id:Int, title:String)
case class Address( address: String, postcode: String, city: String, country : String, location: Option[Location])
case class BeautyExpert(name: String,surname:String, dob: String, treatments:Seq[Treatment],
                        address : Address, email: String, mobile : String,
                        availability: BeautyExpertAvailability = BeautyExpertAvailability())

object DAY_OF_WEEK extends Enumeration {
  type DAY_OF_WEEK = Value
  val MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY = Value
}

object TIME_OF_DAY extends Enumeration {
  type TIME_OF_DAY = Value
  val MORNING,AFTERNOON,EVENING = Value
}

case class BeautyExpertAvailability(days: Seq[(DAY_OF_WEEK,Seq[TIME_OF_DAY])])

object BeautyExpertAvailability {

  def apply() = BeautyExpertAvailability(Seq(
    (DAY_OF_WEEK.MONDAY,Seq(TIME_OF_DAY.MORNING,TIME_OF_DAY.AFTERNOON,TIME_OF_DAY.EVENING)),
    (DAY_OF_WEEK.TUESDAY,Seq(TIME_OF_DAY.MORNING,TIME_OF_DAY.AFTERNOON,TIME_OF_DAY.EVENING)),
    (DAY_OF_WEEK.WEDNESDAY,Seq(TIME_OF_DAY.MORNING,TIME_OF_DAY.AFTERNOON,TIME_OF_DAY.EVENING)),
    (DAY_OF_WEEK.THURSDAY,Seq(TIME_OF_DAY.MORNING,TIME_OF_DAY.AFTERNOON,TIME_OF_DAY.EVENING)),
    (DAY_OF_WEEK.FRIDAY,Seq(TIME_OF_DAY.MORNING,TIME_OF_DAY.AFTERNOON,TIME_OF_DAY.EVENING)),
    (DAY_OF_WEEK.SATURDAY,Seq(TIME_OF_DAY.MORNING,TIME_OF_DAY.AFTERNOON,TIME_OF_DAY.EVENING)),
    (DAY_OF_WEEK.SUNDAY,Seq(TIME_OF_DAY.MORNING,TIME_OF_DAY.AFTERNOON,TIME_OF_DAY.EVENING))))
}