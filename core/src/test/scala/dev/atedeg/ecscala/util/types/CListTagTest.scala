package dev.atedeg.ecscala.util.types

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import dev.atedeg.ecscala.{ &:, CList, CNil }
import dev.atedeg.ecscala.fixtures.{ ComponentsFixture, Position, Velocity }

class CListTagTest extends AnyWordSpec with Matchers {

  "A CListTag" should {
    "have the correct components" in {
      summon[CListTag[Position &: CNil]].tags shouldBe Seq(summon[ComponentTag[Position]])
      summon[CListTag[Position &: Velocity &: CNil]].tags shouldBe
        Seq(summon[ComponentTag[Position]], summon[ComponentTag[Velocity]])
    }
  }

  "The compiler" should {
    "not derive a CListTag[Nothing]" in assertCListTagIsNotDerived("Nothing")
    "not derive a CListTag[CNil]" in assertCListTagIsNotDerived("CNil")
    "not derive a CListTag[CList]" in assertCListTagIsNotDerived("CList")
    "not derive a CListTag with duplicates" in assertCListTagIsNotDerived("Velocity &: Position &: Position &: CNil")
    "derive a correct CListTag" in assertCListTagIsDerived("Velocity &: Position &: CNil")
  }

  inline def assertCListTagIsNotDerived(inline tagType: String): ComponentsFixture =
    new ComponentsFixture { "summon[CListTag[" + tagType + "]]" shouldNot typeCheck }

  inline def assertCListTagIsDerived(inline tagType: String): ComponentsFixture =
    new ComponentsFixture { "summon[CListTag[" + tagType + "]]" should compile }
}
