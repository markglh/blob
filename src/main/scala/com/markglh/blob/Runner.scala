package com.example

import com.markglh.blob.BlobFinder
import com.markglh.blob.grid.FileGridLoader
import com.markglh.blob.pickers.{NeighboursPicker, RandomPicker}

object Runner extends RandomPicker {
  def main(args: Array[String]): Unit = {


    //implicit object picker extends RandomPicker with FileGridLoader
    implicit object picker extends NeighboursPicker with FileGridLoader
    val solution = new BlobFinder().findBlob()


    //TODO change this to include multiple tries and averages
    println(
      s"""Left: ${solution.solutionState.get.left}
         |Right: ${solution.solutionState.get.right}
         |Top: ${solution.solutionState.get.top}
         |Bottom: ${solution.solutionState.get.bottom}
         |Reads: ${solution.searchState.reads}
         |Blob Size: ${solution.searchState.blobCells.size}
       """.stripMargin)
  }

  //2,6,1,7

}
