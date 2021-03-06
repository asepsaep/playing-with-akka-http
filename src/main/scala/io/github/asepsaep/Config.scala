package io.github.asepsaep

import com.typesafe.config.ConfigFactory

trait Config {
  protected val config = ConfigFactory.load()
  protected val interface = config.getString("http.interface")
  protected val port = config.getInt("http.port")
  protected val mongoHost = config.getString("mongo.host")
  protected val mongoDb = config.getString("mongo.db")
  protected val mongoUser = config.getString("mongo.user")
  protected val mongoPassword = config.getString("mongo.password")
}
