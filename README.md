# ECScala
An [Entity Component System](https://en.wikipedia.org/wiki/Entity_component_system) Scala framework  

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://img.shields.io/maven-central/v/dev.atedeg/ecscala_3)](https://search.maven.org/artifact/dev.atedeg/ecscala_3)
[![GitHub release](https://img.shields.io/github/release/nicolasfara/ecscala.svg)](https://gitHub.com/nicolasfara/ecscala/releases/)
![example workflow](https://github.com/nicolasfara/ecscala/workflows/CI/badge.svg)
[![codecov](https://codecov.io/gh/nicolasfara/ecscala/branch/develop/graph/badge.svg?token=0XZ4XF71AY)](https://codecov.io/gh/nicolasfara/ecscala)
[![javadoc](https://javadoc.io/badge2/dev.atedeg/ecscala_3/javadoc.svg)](https://javadoc.io/doc/dev.atedeg/ecscala_3)

## Getting Started

```scala
libraryDependencies += "dev.atedeg" %% "ecscala" % "0.2.1"
```

## Usage
ECScala allows you to use the ECS architechtural pattern with ease:
```scala
import dev.atedeg.ecscala.given

case class Position(x: Float, y: Float) extends Component
case class Velocity(vx: Float, vy: Float) extends Component

object Example extends ECScalaDSL {
  val world = World()
  world hasAn entity withComponents { Position(1, 1) &: Velocity(2, 2) }
  val movementSystem = System[Position &: Velocity &: CNil]
    .withUpdate { (_, components, deltaTime) =>
      val Position(x, y) &: Velocity(vx, vy) &: CNil = components
      Position(x + vx*deltaTime, y + vy*deltaTime) &: Velocity(vx, vy) &: CNil
    }
  world hasA system(movementSystem)
  world.update(10)
}
```
To learn how to use ECScala you can start by reading [its wiki](https://github.com/nicolasfara/ecscala/wiki)!

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Authors

- **Giacomo Cavalieri** - [giacomocavalieri](https://github.com/giacomocavalieri)
- **Nicolò Di Domenico** - [ndido98](https://github.com/ndido98)
- **Nicolas Farabegoli** - [nicolasfara](https://github.com/nicolasfara)
- **Linda Vitali** - [vitlinda](https://github.com/vitlinda)

## License

Distributed under the MIT license. See [LICESE](LICENSE) for more information.

