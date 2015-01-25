package com.markglh.blob.grid

import com.markglh.blob.grid.Grid.GridCells

/**
 * Loads the grid from a file.
 */
trait FileGridLoader extends GridLoader {

  def loadGrid(): Grid = {
    val indexedLines: Iterator[(String, Int)] = scala.io.Source
      .fromFile("grid.txt", "utf-8")
      .getLines() //split into lines
      .zipWithIndex //pair each line with it's index (y axis)

    val gridCells: GridCells = indexedLines.flatMap {
      case (line, yIndex) => line.zipWithIndex //index number in the string with it's (x)index
        .map { case (value, xIndex) => (xIndex, yIndex) -> ('1' == value) //pairs each cell with the boolean blob status
      }
    }.toMap

    Grid(gridCells, gridCells.keySet.map(_._1).max, gridCells.keySet.map(_._2).max)
  }
}
