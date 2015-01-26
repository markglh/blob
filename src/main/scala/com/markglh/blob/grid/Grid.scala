package com.markglh.blob.grid

import com.example.State.SearchState
import com.markglh.blob.grid.Grid._

import scala.util.Random

/**
 * Represents the grid and it's properties.
 */
case class Grid(gridCells: GridCells, width: Int, height: Int) {

  /**
   * Returns a [[Set]] of unvisited Cells
   */
  def getUnvisitedCells(searchState: SearchState): List[(Int, Int)] = {
    gridCells.keys.filterNot((searchState.zeroCells ++ searchState.blobCells).contains).toList
  }

  /**
   * Pick a random [[Cell]] from the unvisited cells in the [[Grid]], or [[None]] if none were found.
   */
  def pickRandomCell(searchState: SearchState): Option[Cell] = {
    Random.shuffle(getUnvisitedCells(searchState)).headOption
  }
}

object Grid {
  type Cell = (Int, Int)
  type GridCells = Map[Cell, Boolean]
}
