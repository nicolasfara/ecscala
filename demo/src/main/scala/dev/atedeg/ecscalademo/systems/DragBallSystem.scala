package dev.atedeg.ecscalademo.systems

import dev.atedeg.ecscala.*
import dev.atedeg.ecscalademo.{
  given_Conversion_Position_Point,
  isOverlappedWith,
  Circle,
  MouseState,
  PlayState,
  Position,
  StartingState,
}
import dev.atedeg.ecscala.util.types.given

/**
 * This [[System]] is used to update the selected ball's [[Position]] according to the mouse pointer.
 */
class DragBallSystem extends EmptySystem {

  override def shouldRun: Boolean =
    !PlayState.playing && PlayState.selectedBall.isDefined && PlayState.isDragging && !PlayState.velocityEditingMode

  override def update(deltaTime: DeltaTime, world: World): Unit = {
    PlayState.selectedBall match {
      case Some(entity) => {
//        val position = entity.getComponent[Position].get.position
//        val radius = entity.getComponent[Circle].get.radius
//        if ((position - MouseState.coordinates).norm > radius) {
        entity.addComponent(Position(MouseState.coordinates))
      }
//      }
      case _ => ()
    }
  }
}
