package io.github.asepsaep

import spray.json.DefaultJsonProtocol

trait JsonProtocol extends DefaultJsonProtocol {
  protected implicit val userFormat = jsonFormat2(User)
}