package dev.atedeg.ecscalademo.systems

import scala.language.implicitConversions
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import dev.atedeg.ecscala.util.types.given
import dev.atedeg.ecscalademo.fixtures.CollisionsFixture
import dev.atedeg.ecscalademo.util.WritableSpacePartitionContainer
import dev.atedeg.ecscalademo.given
import dev.atedeg.ecscalademo.{ Circle, Color, Mass, PlayState, Position, Vector, Velocity }

class CollisionSystemTest extends AnyWordSpec with Matchers {

  "The CollisionSystem" should {
    "keep entities separated" in new CollisionsFixture {
      val black = Color(0, 0, 0)
      val stuckEntity1 = world
        .createEntity()
        .addComponent(Position(0, 0))
        .addComponent(Velocity(0, 0))
        .addComponent(Circle(10, black))
        .addComponent(Mass(1))
      val stuckEntity2 = world
        .createEntity()
        .addComponent(Position(5, 0))
        .addComponent(Velocity(0, 0))
        .addComponent(Circle(10, black))
        .addComponent(Mass(1))

      PlayState.playing = true
      world.update(1)
      val stuckPosition1 = stuckEntity1.getComponent[Position].get
      val stuckPosition2 = stuckEntity2.getComponent[Position].get
      val stuckRadius1 = stuckEntity1.getComponent[Circle].get.radius
      val stuckRadius2 = stuckEntity2.getComponent[Circle].get.radius

      val distanceBetweenStuckBalls = (stuckPosition1.position - stuckPosition2.position).norm
      distanceBetweenStuckBalls shouldBe (stuckRadius1 + stuckRadius2) +- 0.001
    }
    "compute the new velocities" in new CollisionsFixture {
      val black = Color(0, 0, 0)

      val collidingEntity1 = world
        .createEntity()
        .addComponent(Position(20, 20))
        .addComponent(Velocity(100, 0))
        .addComponent(Circle(10, black))
        .addComponent(Mass(1))
      val collidingEntity2 = world
        .createEntity()
        .addComponent(Position(40, 20))
        .addComponent(Velocity(0, 0))
        .addComponent(Circle(10, black))
        .addComponent(Mass(1))

      PlayState.playing = true
      world.update(1)
      val velocity1 = collidingEntity1.getComponent[Velocity].get
      val velocity2 = collidingEntity2.getComponent[Velocity].get

      velocity1.velocity shouldBe Vector(0, 0)
      velocity2.velocity shouldBe Vector(100, 0)
    }
  }
}
