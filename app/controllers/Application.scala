package controllers

import play.api.mvc._

trait Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready. Ciao"))
  }

}

object Application extends Application {

}
