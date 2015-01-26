import com.markglh.blob.BlobFinder
import com.markglh.blob.grid.FileGridLoader
import com.markglh.blob.pickers.{NeighboursPicker, RandomPicker}
import org.scalatest._

class BlobFinderSpec extends FlatSpec with Matchers {


  "Random Picker" should "find the blob by randomly picking cells" in {

    implicit object picker extends RandomPicker with FileGridLoader
    val blobFinder = new BlobFinder()

    val multipleResults = Iterator.continually(blobFinder.findBlob().searchState.reads).take(50).toList

    val solution = blobFinder.findBlob()

    println(
      s"""
         |-------------------------------
         |Random Picker:
         |Min reads: ${multipleResults.min}
         |Max Reads: ${multipleResults.max}
         |Average reads: ${multipleResults.sum / multipleResults.size}
         |
         |Top: ${solution.solutionState.get.top}
         |Left: ${solution.solutionState.get.left}
         |Bottom: ${solution.solutionState.get.bottom}
         |Right: ${solution.solutionState.get.right}
         |-------------------------------""".stripMargin)

    solution.solutionState should be('defined)
    solution.solutionState.get.top should equal(1)
    solution.solutionState.get.left should equal(2)
    solution.solutionState.get.bottom should equal(7)
    solution.solutionState.get.right should equal(6)
  }

  "Neighbour Picker" should "find the blob by visiting neighbour cells" in {

    implicit object picker extends NeighboursPicker with FileGridLoader
    val blobFinder = new BlobFinder()

    val multipleResults = Iterator.continually(blobFinder.findBlob().searchState.reads).take(50).toList

    val solution = blobFinder.findBlob()

    println(
      s"""
         |-------------------------------
         |Neighbours Picker:
         |Min reads: ${multipleResults.min}
         |Max Reads: ${multipleResults.max}
         |Average reads: ${multipleResults.sum / multipleResults.size}
         |
         |Top: ${solution.solutionState.get.top}
         |Left: ${solution.solutionState.get.left}
         |Bottom: ${solution.solutionState.get.bottom}
         |Right: ${solution.solutionState.get.right}
         |-------------------------------""".stripMargin)

    solution.solutionState should be('defined)
    solution.solutionState.get.top should equal(1)
    solution.solutionState.get.left should equal(2)
    solution.solutionState.get.bottom should equal(7)
    solution.solutionState.get.right should equal(6)
  }

}
