package dev.atedeg.ecscalademo.systems

import dev.atedeg.ecscala.util.types.given
import dev.atedeg.ecscalademo.fixtures.WorldFixture
import dev.atedeg.ecscalademo.{ Circle, MouseState, PlayState, Point, Position, StartingState }
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BallSelectionSystemTest extends AnyWordSpec with Matchers {

  trait BallSelectionSystemFixture extends WorldFixture {
    lazy val system = BallSelectionSystem()
  }

  "A BallSelectionSystem" when {
    "the game is paused and the mouse is clicked" should {
      "be enabled" in new BallSelectionSystemFixture {
        enableSystemCondition()
        system.shouldRun shouldBe true
      }
    }
    "the game is paused and the mouse is not clicked" should {
      "not be enabled" in new BallSelectionSystemFixture {
        disableSystemCondition()
        system.shouldRun shouldBe false
      }
    }
    "the game is running" should {
      "not be enabled" in new BallSelectionSystemFixture {
        PlayState.playing = true
        system.shouldRun shouldBe false
      }
    }
    "a ball is selected" should {
      "set the ball as current selected" in new BallSelectionSystemFixture {
        enableSystemCondition()
        PlayState.selectedBall = None

        val entity1 = world.createEntity()
        val entity2 = world.createEntity()
        entity1.addComponent(Position(Point(10, 10)))
        entity1.addComponent(Circle(20, StartingState.startingColor))

        entity2.addComponent(Position(Point(70, 70)))
        entity2.addComponent(Circle(20, StartingState.startingColor))

        world.addSystem(system)
        PlayState.selectedBall shouldBe None
        MouseState.coordinates = Point(10, 10)
        world.update(10)

        PlayState.selectedBall shouldBe Some(entity1)

        MouseState.coordinates = Point(65, 65)
        world.update(10)

        PlayState.selectedBall shouldBe Some(entity2)
      }
    }
  }

  private def enableSystemCondition(): Unit = {
    PlayState.playing = false
    MouseState.down = true
  }

  private def disableSystemCondition(): Unit = {
    PlayState.playing = false
    MouseState.down = false
  }
}
