package dev.atedeg.ecscalademo

import dev.atedeg.ecscala.Entity

object MouseState {
  var coordinates = Point(0, 0)
  var clicked = false
  var down = false
  var up = false
}

object PlayState {
  var playing = false
  var selectedBall: Option[Entity] = Option.empty
}

object StartingState {
  val startingRadius: Double = 20.0
  val startingColor: Color = Color(255, 255, 0)
  val startingMass: Double = 1
}
