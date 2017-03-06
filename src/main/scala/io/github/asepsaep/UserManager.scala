package io.github.asepsaep

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.io.StdIn

case class User(username: String, password: String)

object UserManager extends App with JsonProtocol with Config{

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val dispatcher = system.dispatcher

  val repository = new Repository
  val service = new Service(repository)

  val route = pathPrefix("user") {
    (post & pathEndOrSingleSlash & entity(as[User])) { user =>
      complete(service.add(user).map(user => Created -> user))
    } ~ (path(Segment) & pathEndOrSingleSlash) { username =>
      get {
        complete {
          service.find(username).map[ToResponseMarshallable] {
            case None => NotFound -> "User not found"
            case Some(user) => OK -> user
          }
        }
      }
    }
  }

  // val bindingFuture = Http().bindAndHandle(route, interface, port)
  Http().bindAndHandle(route, interface, port)

  /* 
  println("Press Enter to shutdown HTTP server")
  StdIn.readLine()
  bindingFuture.flatMap(_.unbind()).onComplete { _ =>
    service.close()
    materializer.shutdown()
    system.terminate().onComplete(_ => {})
  }
  */

}
